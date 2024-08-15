package com.aresky.bookingservice.service.booking;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aresky.bookingservice.dto.request.*;
import com.aresky.bookingservice.dto.response.*;
import com.aresky.bookingservice.kafka.KafkaMessageType;
import com.aresky.bookingservice.kafka.KafkaSenderEvent;
import com.aresky.bookingservice.kafka.KafkaTopic;
import com.aresky.bookingservice.model.*;
import com.aresky.bookingservice.repository.CancellationRequestedRepository;
import com.aresky.bookingservice.service.account.IAccountGrpcService;
import com.aresky.bookingservice.service.payment.IVnPayService;
import com.aresky.bookingservice.service.tour.ITourService;
import com.aresky.bookingservice.util.DateUtils;
import com.aresky.bookingservice.util.FieldUtils;
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
    private ITourService tourService;

    @Autowired
    private IVnPayService vnPayService;

    @Autowired
    private KafkaSenderEvent kafkaSenderEvent;

    @Autowired
    private CancellationRequestedRepository cancellationRequestedRepository;

    private final Logger log = LoggerFactory.getLogger(BookingDtoServiceImp.class);

    private final Gson GSON = new Gson();
    
    @Override
    public Mono<String> handleBooking(CreateBookingForm form, EPaymentType type) {
        int accountId = form.getAccountId();
        int subTourId = form.getSubTourId();
        boolean isPayLaterType = type.equals(EPaymentType.PAY_LATER);
        boolean isPayWithVNPAYType = type.equals(EPaymentType.VNPAY_ON_WEBSITE);

        return Mono.zip(existsBy(accountId, subTourId), validateAccount(accountId), findSubTour(subTourId))
                .flatMap(tuple -> {
                    Boolean existsBooking = tuple.getT1();
                    SubTour subTour = tuple.getT3();

                    if(subTour.getAvailableSeats() == 0){
                        throw new BookingException("Chúng tôi rất tiếc phải thông báo rằng tour mà Quý khách đã chọn hiện tại đã hết chỗ!");
                    }

                    if(existsBooking) {
                        return bookingService.findBookingBy(accountId, subTourId)
                                .flatMap(booking -> {
                                    EBookingStatus status = booking.getStatus();

                                    if(!status.equals(EBookingStatus.IS_PAYING)){
                                        throw BookingException.BOOKING_EXISTS;
                                    };

                                    if (isPayWithVNPAYType){
                                        return getVNPayUrl(booking, subTour);
                                    }

                                    return Mono.empty();
                                });
                    }

                    return bookingService.createBooking(subTour, form, type)
                            .flatMap(booking -> {
                                EBookingStatus status = booking.getStatus();
                                boolean isPaying = status.equals(EBookingStatus.IS_PAYING);
                                boolean isNotPay = status.equals(EBookingStatus.NOT_PAY);

                                if(isNotPay && isPayLaterType){
                                    sendNotification(KafkaTopic.BOOKING_SUCCESS, booking);
                                    return Mono.just("Đặt tour thành công, vui lòng kiểm tra thông tin chi tiết trong hồ sơ của bạn!");
                                }

                                if(isPaying && isPayWithVNPAYType){
                                    return getVNPayUrl(booking, subTour);
                                }

                                return Mono.empty();
                            });
                })
                .switchIfEmpty(Mono.error(BookingException.UNSUPPORTED_PAYMENT_TYPE));
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
    public Mono<VnPayTransactionInfoResponse> findVnPayTransactionInfo(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(booking -> {
                    if(!booking.getStatus().equals(EBookingStatus.PAY_UP)){
                        return Mono.error(new BookingException(BookingException.BOOKING_HAVE_NOT_PAID));
                    }

                    return vnPayService.getTransactionInfo(bookingId);
                })
                .switchIfEmpty(Mono.error(BookingException.VNPAY_TRANSACTION_INFO_NOT_FOUND))
                .map(VnPayTransactionInfoResponse::new);
    }

    @Override
    public Mono<BookingDetails> findOne(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(booking -> findAllTourist(bookingId).map(booking::touristList))
                .flatMap(booking -> vnPayService.getTransactionInfo(bookingId)
                        .map(booking::transactionInfo)
                        .thenReturn(booking))
                .flatMap(booking -> tourService.getSubTourById(booking.getSubTourId())
                        .map(booking::subTour)
                        .thenReturn(booking))
                .map(BookingDetails::toDTO)
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<BookingDetails> findOne(Integer accountId, Integer subTourId) {
        return Mono.zip(validateAccount(accountId), findSubTour(subTourId))
                .onErrorResume(Mono::error)
                .flatMap(tuple -> findByAccountIdAndSubTourId(accountId, subTourId)
                        .flatMap(booking -> findAllTourist(booking.getId())
                                .map(booking::touristList))
                        .flatMap(booking -> vnPayService.getTransactionInfo(booking.getId())
                                .map(booking::transactionInfo)
                                .thenReturn(booking))
                        .map(booking -> booking.subTour(tuple.getT2()))
                )
                .map(BookingDetails::toDTO)
                .onErrorResume(Mono::error);
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
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(BookingException.INVALID_BOOKING_ID_EX))
                .map(booking -> {
                    fields.forEach((key, value) -> {
                        Field field = FieldUtils.findField(booking, key);
                        FieldUtils.setFieldValue(booking, field, value);
                    });

                    return booking;
                })
                .flatMap(bookingService::saveBooking)
                .map(BookingResponse::toDTO);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Integer bookingId) {
        return bookingService.delete(bookingId);
    }

    @Override
    public Mono<CancellationRequestedResponse> sendCancellationBookingRequest(Integer accountId, CreateCancelBookedTourForm form) {
        Integer bookingId = form.getBookingId();

        return Mono.zip(
                        validateAccount(accountId),
                        bookingService.findBookingBy(bookingId),
                        cancellationRequestedRepository.existsByBookingId(bookingId)
                )
                .flatMap(tuple1 -> {
                    Booking booking = tuple1.getT2();

                    if(!accountId.equals(booking.getAccountId())){
                        return Mono.error(BookingException.PERMISSION_DENIED);
                    }

                    Boolean existsCancellationRequest = tuple1.getT3();

                    if(existsCancellationRequest){
                        return Mono.error(BookingException.CANCELLATION_REQUEST_ALREADY_EXIST);
                    }

                    CancellationRequested cancellationRequested = form.toCancellationRequested();
                    booking.setIsCancellationRequested(Boolean.TRUE);

                    return Mono.zip(
                            cancellationRequestedRepository.save(cancellationRequested),
                            bookingService.saveBooking(booking)
                    );
                })
                .map(tuple2 -> {
                    sendNotification(KafkaTopic.BOOKING_CANCEL_PENDING, tuple2.getT2());
                    return tuple2.getT1();
                })
                .map(CancellationRequestedResponse::fromCancellationRequested);
    }

    @Override
    public Mono<List<CancellationRequestedResponse>> findAllCancellationRequested(Integer page, Integer size) {
        return cancellationRequestedRepository.findAll()
                .map(CancellationRequestedResponse::fromCancellationRequested)
                .collectList();
    }

    @Override
    public Mono<Void> approveCancellationBookingRequestByBookingId(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(BookingException.INVALID_BOOKING_ID_EX))
                .filter(booking -> booking.getStatus().equals(EBookingStatus.REJECTED))
                .switchIfEmpty(Mono.error(BookingException.BOOKING_STATUS_MUST_BE_REJECTED))
                .flatMap(booking -> cancellationRequestedRepository.findByBookingId(bookingId)
                        .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUEST_DOES_NOT_EXIST))
                        .filter(request -> request.getStatus().equals(ERequestStatus.PENDING))
                        .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUESTED_CANNOT_APPROVE))
                        .flatMap(request -> {
                            booking.setStatus(EBookingStatus.REJECTED);
                            request.setStatus(ERequestStatus.APPROVED);

                            return Mono.zip(bookingService.saveBooking(booking), cancellationRequestedRepository.save(request))
                                    .then(Mono.fromRunnable(() -> sendNotification(KafkaTopic.BOOKING_CANCEL_APPROVED, booking)))
                                    .then();
                        }));
    }

    @Override
    public Mono<Void> rejectCancellationBookingRequestByBookingId(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(BookingException.INVALID_BOOKING_ID_EX))
                .flatMap(booking -> cancellationRequestedRepository.findByBookingId(bookingId)
                        .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUEST_DOES_NOT_EXIST))
                        .filter(request -> request.getStatus().equals(ERequestStatus.PENDING))
                        .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUESTED_CANNOT_REJECT))
                        .flatMap(request -> {
                            request.setStatus(ERequestStatus.REJECTED);

                            return cancellationRequestedRepository.save(request)
                                    .then(Mono.fromRunnable(() -> sendNotification(KafkaTopic.BOOKING_CANCEL_REJECTED, booking)));
                        }));
    }

    @Transactional
    @Override
    public Mono<Void> deleteCancellationBookingRequestByBookingId(Integer bookingId) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(BookingException.BOOKING_NOT_FOUND_EX))
                .flatMap(booking -> bookingService.findCancellationRequestByBookingId(bookingId)
                        .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUEST_DOES_NOT_EXIST))
                        .filter(request -> request.getStatus().equals(ERequestStatus.PENDING))
                        .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUESTED_CANNOT_DELETE))
                        .flatMap(request -> {
                                booking.setIsCancellationRequested(false);

                                return Mono.zip(
                                        bookingService.saveBooking(booking),
                                        cancellationRequestedRepository.delete(request)
                                ).then();
                            }));
    }

    @Override
    public Mono<BookingResponse> updateStatus(Integer bookingId, BookingStatusUpdateRequest request) {
        return bookingService.findBookingBy(bookingId)
                .switchIfEmpty(Mono.error(BookingException.INVALID_BOOKING_ID_EX))
                .map(booking -> {
                    booking.setStatus(EBookingStatus.valueOf(request.getStatus()));
                    booking.setFormOfPayment(Optional.ofNullable(request.getFormOfPayment()).map(EFormOfPayment::valueOf).orElse(null));
                    return booking;
                })
                .onErrorMap(IllegalArgumentException.class, err -> new BookingException(err.getMessage()))
                .flatMap(bookingService::saveBooking)
                .map(BookingResponse::toDTO);
    }

    @Override
    public Mono<CancellationRequestedResponse> findCancellationRequested(Integer bookingId) {
        return bookingService.existsBookingBy(bookingId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(BookingException.BOOKING_NOT_FOUND_EX))
                .then(bookingService.findCancellationRequestByBookingId(bookingId))
                .switchIfEmpty(Mono.error(BookingException.CANCELLATION_REQUEST_DOES_NOT_EXIST))
                .map(CancellationRequestedResponse::fromCancellationRequested);
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

    private Mono<SubTour> findSubTour(Integer subTourId) {
        return tourService.getSubTourById(subTourId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_SUB_TOUR_ID)));
    }

    @SuppressWarnings("unused")
    private void sendNotification(String topic, Booking booking){
        sendNotification(topic, booking.getAccountId(), booking);
    }

    private void sendNotification(String topic, Integer accountId, Booking booking){
        DateTimeFormatter formatter = DateUtils.getDateTimeFormatter(DateUtils.CommonLocales.VIETNAM, FormatStyle.SHORT);
        KafkaMessageType.NotificationRequest request = KafkaMessageType.NotificationRequest.builder()
                .userId(accountId)
                .entityId(booking.getId())
                .keyword("tourId", booking.getTourId())
                .keyword("subTourId", booking.getSubTourId())
                .keyword("status", booking.getStatus().name())
                .keyword("tourCode", booking.getTourCode())
                .keyword("bookingCode", booking.getBookingCode())
                .keyword("bookedTime", formatter.format(booking.getBookedTime()));

        kafkaSenderEvent.sendMessage(topic, "booking-service-key", GSON.toJson(request))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(value -> {
                    log.info("Message sent to Kafka successfully with topic {}", topic);
                }, error -> {
                    log.error("Error sending message to Notification service by Kafka: {}", error.getMessage());
                });
    }

    @SuppressWarnings("unused")
    private Mono<String> getVNPayUrl(Booking booking, SubTourResponse subTour){
        BookingInfoRequest request = BookingInfoRequest.newBuilder()
                .setBookingId(booking.getId())
                .setTourCode(subTour.getTourCode())
                .setAmount(booking.getAmount())
                .build();

        return vnPayService.getPaymentURL(request)
                .onErrorResume(ex -> Mono.error(new BookingException(ex.getMessage())));
    }

    @SuppressWarnings("unused")
    private Mono<String> getVNPayUrl(Booking booking, SubTour subTour){
        BookingInfoRequest request = BookingInfoRequest.newBuilder()
                .setBookingId(booking.getId())
                .setTourCode(subTour.getTourCode())
                .setAmount(booking.getAmount())
                .build();

        return vnPayService.getPaymentURL(request)
                .onErrorResume(ex -> Mono.error(new BookingException(ex.getMessage())));
    }
}
