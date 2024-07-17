package com.aresky.bookingservice.service.booking;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aresky.bookingservice.dto.request.*;
import com.aresky.bookingservice.dto.response.*;
import com.aresky.bookingservice.kafka.KafkaMessageType;
import com.aresky.bookingservice.kafka.KafkaSenderEvent;
import com.aresky.bookingservice.kafka.KafkaTopic;
import com.aresky.bookingservice.model.*;
import com.aresky.bookingservice.repository.CancellationRequestedRepository;
import com.aresky.bookingservice.service.account.IAccountGrpcService;
import com.aresky.bookingservice.service.payment.IVnPayService;
import com.aresky.bookingservice.util.DateUtils;
import com.google.gson.Gson;
import grpc.payment.vnpay.BookingInfoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.service.payment.PaymentService;
import com.aresky.bookingservice.service.tour.TourGrpcService;

import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class BookingDtoServiceImp implements IBookingDtoService {

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private IAccountGrpcService accountGrpcService;

    @Autowired
    private TourGrpcService tourGrpcService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private IVnPayService vnPayService;

    @Autowired
    private KafkaSenderEvent kafkaSenderEvent;

    @Autowired
    private CancellationRequestedRepository cancellationRequestedRepository;

    private final Logger log = LoggerFactory.getLogger(BookingDtoServiceImp.class);
    private final Gson GSON = new Gson();

    private final DateTimeFormatter VIETNAM_FORMATTER = DateUtils.getDateTimeFormatter(DateUtils.CommonLocales.VIETNAM, FormatStyle.SHORT);

    @Override
    public Mono<String> handleBooking(CreateBookingForm form, EPaymentType type) {
        int accountId = form.getAccountId();
        int subTourId = form.getSubTourId();

        return Mono.zip(existsBy(accountId, subTourId), validateAccount(accountId), findSubTour(subTourId))
                .flatMap(tuple -> {
                    boolean existBooking = tuple.getT1();

                    if(existBooking){
                        return Mono.error(new BookingException(BookingException.BOOKING_ALREADY_EXISTS));
                    }

                    SubTourResponse subTour = tuple.getT3();
                    Mono<Booking> bookingMono = bookingService.createBooking(subTour, form, type)
                            .switchIfEmpty(Mono.error(new BookingException(BookingException.BOOKING_FAILED)));

                    if(type.equals(EPaymentType.PAY_LATER)){
                        return bookingMono
                                .flatMap(booking -> {
                                    KafkaMessageType.NotificationRequest notificationRequest = KafkaMessageType.NotificationRequest
                                            .builder()
                                            .userId(accountId)
                                            .entityId(booking.getId())
                                            .keyword("tourCode", booking.getTourCode())
                                            .keyword("bookingCode", booking.getBookingCode())
                                            .keyword("bookedTime", VIETNAM_FORMATTER.format(booking.getBookedTime()));

                                    String message = GSON.toJson(notificationRequest);

                                    kafkaSenderEvent.sendMessage(KafkaTopic.BOOKING_SUCCESS, message)
                                            .subscribeOn(Schedulers.boundedElastic())
                                            .subscribe(value -> {
                                                log.info("Message sent to Kafka successfully");
                                            }, error -> {
                                                log.error("Error sending message to Notification service by Kafka!", error);
                                            });

                                    return Mono.just("Đặt tour thành công, vui lòng kiểm tra thông tin chi tiết trong hồ sơ của bạn!");
                                });
                    }

                    if (type.equals(EPaymentType.VNPAY_ON_WEBSITE)){
                        return bookingMono
                                .flatMap(booking -> {
                                    BookingInfoRequest request = BookingInfoRequest.newBuilder()
                                            .setBookingId(booking.getId())
                                            .setTourCode(subTour.getTourCode())
                                            .setAmount(booking.getAmount())
                                            .build();

                                    return vnPayService.getPaymentURL(request)
                                            .onErrorResume(ex -> bookingService.delete(booking)
                                                    .then(Mono.error(new BookingException(BookingException.CREATE_PAYMENT_SESSION_FAILED))));
                                });
                    }

                    return Mono.error(new BookingException(BookingException.UNSUPPORTED_PAYMENT_TYPE));
                });
    }

    @Override
    public Mono<String> handleBookingAfterPaymentWithVnPay(VnPayReturn vnPayReturn) {
        String responseCode = vnPayReturn.getResponseCode();

        return bookingService.findBookingBy(vnPayReturn.getBookingId())
                .flatMap(booking -> {
                    if (responseCode.equals("00") || Integer.parseInt(responseCode) == 0) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        booking.setStatus(EBookingStatus.PAY_UP);
                        return paymentService.requestCloseVnPayPaymentSession(vnPayReturn)
                                .flatMap(message -> bookingService.saveBooking(booking)
                                            .thenReturn("SUCCESS"));
                    }

                    if (responseCode.equals("24") || Integer.parseInt(responseCode) == 24) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        paymentService.requestCloseSession(vnPayReturn.getBookingId());
                        return bookingService.delete(booking)
                                .thenReturn("CANCELED");
                    }

                    booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                    booking.setStatus(EBookingStatus.PAY_FAILED);
                    paymentService.requestCloseSession(vnPayReturn.getBookingId());
                    return bookingService.saveBooking(booking)
                            .thenReturn("FAILED");
                });
    }

    @Override
    public Mono<String> handlePaymentWithVnPayAfterBooking(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(
                        new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(booking -> findSubTour(booking.getSubTourId())
                        .flatMap(subTour -> paymentService
                                .getVnPayPaymentURL(PaymentRequest.createDTO(booking,
                                        subTour))));
    }

    @Override
    public Mono<String> handlePaymentAfterBooking(Integer bookingId, EFormOfPayment formOfPayment) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(booking -> findSubTour(booking.getSubTourId())
                        .switchIfEmpty(Mono
                                .error(new BookingException(BookingException.SUB_TOUR_NOT_FOUND)))
                        .flatMap(
                                subTour -> paymentService.getVnPayPaymentURL(
                                                PaymentRequest.createDTO(booking,
                                                        subTour))
                                        .onErrorResume(Mono::error)));
    }

    @Override
    public Mono<Page<BookingResponse>> findAll(Pageable pageable) {
        return bookingService.findAllBookings(pageable)
                .flatMap(bookingPage -> Mono.just(bookingPage.getContent())
                        .flatMapMany(Flux::fromIterable)
                        .map(BookingResponse::toDTO)
                        .collectList()
                        .map(content -> new PageImpl<>(content, bookingPage.getPageable(), bookingPage.getTotalElements())));
    }

    @Override
    public Mono<List<BookingResponse>> findAll(Integer accountId) {
        return bookingService.findAllBookings(accountId)
                .flatMapMany(Flux::fromIterable)
                .map(BookingResponse::toDTO)
                .collectList();
    }

    @Override
    public Mono<Page<BookingResponse>> findAll(Pageable pageable, BookingFilter filter) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<VnPayTransactionInfo> findVnPayTransactionInfo(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(booking -> {
                    if(!booking.getStatus().equals(EBookingStatus.PAY_UP)){
                        return Mono.error(new BookingException(BookingException.BOOKING_HAVE_NOT_PAID));
                    }

                    return vnPayService.getTransactionInfo(bookingId);
                });
    }

    @Override
    public Mono<BookingDetails> findOne(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(booking -> Mono.zip(
                        findSubTour(booking.getSubTourId()),
                        findAllTourist(bookingId)).flatMap(tuple -> {
                    SubTourResponse subTour = tuple.getT1();
                    List<Tourist> touristList = tuple.getT2();
                    return Mono.just(BookingDetails.toDTO(booking, subTour, touristList));
                }))
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<BookingDetails> findOne(Integer accountId, Integer subTourId) {
        return Mono.zip(validateAccount(accountId), findSubTour(subTourId))
                .onErrorResume(Mono::error)
                .flatMap(tuple -> findByAccountIdAndSubTourId(accountId, subTourId)
                        .flatMap(booking -> findAllTourist(booking.getId())
                                .map(tourist -> BookingDetails.toDTO(booking,
                                        tuple.getT2(), tourist))));
    }

    @Override
    public Mono<Boolean> existsBy(Integer accountId, Integer subTourId) {
        return bookingService.existsBookingBy(accountId, subTourId);
    }

    @Transactional
    @Override
    public Mono<BookingResponse> update(UpdateBookingForm form) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Transactional
    @Override
    public Mono<BookingResponse> update(Integer bookingId, Map<String, Object> fields) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Transactional
    @Override
    public Mono<Void> delete(Integer bookingId) {
        return bookingService.delete(bookingId);
    }

    @Override
    public Mono<Void> sendCancellationBookingRequest(Integer accountId, CreateCancelBookedTourForm form) {
        return Mono.zip(validateAccount(accountId), bookingService.findBookingBy(form.getBookingId()))
                .flatMap(tuple -> {
                    CancellationRequested cancellationRequested = form.toCancellationRequested();

                    Booking booking = tuple.getT2();

                    if(!accountId.equals(booking.getAccountId())){
                        return Mono.error(BookingException.PERMISSION_DENIED);
                    }

                    booking.setIsCancellationRequested(Boolean.TRUE);

                    return cancellationRequestedRepository.save(cancellationRequested)
                                    .then(bookingService.saveBooking(booking));
                })
                .flatMap(booking -> {
                    KafkaMessageType.NotificationRequest notificationReq = KafkaMessageType.NotificationRequest.builder()
                            .userId(accountId)
                            .entityId(booking.getId())
                            .keyword("tourCode", booking.getTourCode())
                            .keyword("bookingCode", booking.getBookingCode());

                    return kafkaSenderEvent.sendMessage(KafkaTopic.BOOKING_CANCEL_PENDING, GSON.toJson(notificationReq)).then();
                });
    }

    @Override
    public Mono<List<CancellationRequestedResponse>> findAllCancellationRequested(Integer page, Integer size) {
        return cancellationRequestedRepository.findAll()
                .map(CancellationRequestedResponse::fromCancellationRequested)
                .collectList();
    }

    @Override
    public Mono<Void> approveCancellationBookingRequest(Integer requestId) {
        return cancellationRequestedRepository.findById(requestId)
                .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUEST_DOES_NOT_EXIST))
                .flatMap(request -> {
                    request.setStatus(ERequestStatus.APPROVED);
                    return cancellationRequestedRepository.save(request)
                            .then(bookingService.findBookingBy(request.getBookingId()));
                })
                .flatMap(booking -> {
                    booking.setStatus(EBookingStatus.REJECTED);
                    return bookingService.saveBooking(booking);
                })
                .flatMap(booking -> {
                    KafkaMessageType.NotificationRequest notificationReq = KafkaMessageType.NotificationRequest.builder()
                            .userId(booking.getAccountId())
                            .entityId(booking.getId())
                            .keyword("tourCode", booking.getTourCode())
                            .keyword("bookingCode", booking.getBookingCode());

                    return kafkaSenderEvent.sendMessage(KafkaTopic.BOOKING_CANCEL_APPROVED, GSON.toJson(notificationReq)).then();
                });
    }

    @Override
    public Mono<Void> rejectCancellationBookingRequest(Integer requestId) {
        return cancellationRequestedRepository.findById(requestId)
                .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUEST_DOES_NOT_EXIST))
                .flatMap(request -> {
                    request.setStatus(ERequestStatus.APPROVED);
                    return cancellationRequestedRepository.save(request)
                            .then(bookingService.findBookingBy(request.getBookingId()));
                })
                .flatMap(booking -> {
                    KafkaMessageType.NotificationRequest notificationReq = KafkaMessageType.NotificationRequest.builder()
                            .userId(booking.getAccountId())
                            .entityId(booking.getId())
                            .keyword("tourCode", booking.getTourCode())
                            .keyword("bookingCode", booking.getBookingCode());

                    return kafkaSenderEvent.sendMessage(KafkaTopic.BOOKING_CANCEL_REJECTED, GSON.toJson(notificationReq)).then();
                });
    }

    private Mono<List<Tourist>> findAllTourist(Integer bookingId) {
        return bookingService.findAllTourists(bookingId);
    }

    private Mono<Booking> findByAccountIdAndSubTourId(Integer accountId, Integer subTourId) {
        return bookingService.findBookingBy(accountId, subTourId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.BOOKING_DOES_NOT_EXISTS)));
    }

    private Mono<Boolean> validateAccount(Integer accountId) {
        return accountGrpcService.checkAccountById(accountId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_ACCOUNT_ID)));
    }

    private Mono<SubTourResponse> findSubTour(Integer subTourId) {
        return tourGrpcService.getSubTourById(subTourId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_SUB_TOUR_ID)));
    }

    @SuppressWarnings("unused")
    private Mono<Void> sendNotification(Integer accountId, Booking booking){
        DateTimeFormatter formatter = DateUtils.getDateTimeFormatter(DateUtils.CommonLocales.VIETNAM, FormatStyle.SHORT);

        Map<String, Object> keywords = new HashMap<>();
        keywords.put("tourCode", booking.getTourCode());
        keywords.put("bookingCode", booking.getBookingCode());
        keywords.put("bookedTime", formatter.format(booking.getBookedTime()));

        KafkaMessageType.NotificationRequest request = new KafkaMessageType.NotificationRequest(accountId);
        request.setEntityId(booking.getId());
        request.setKeywords(keywords);

        return kafkaSenderEvent.sendMessage(KafkaTopic.BOOKING_SUCCESS, "booking-service-key", GSON.toJson(request))
                .then();
    }
}
