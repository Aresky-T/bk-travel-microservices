package com.aresky.bookingservice.service.booking;

import java.util.List;
import java.util.Map;

import com.aresky.bookingservice.dto.request.*;
import com.aresky.bookingservice.dto.response.BookingDetails;
import com.aresky.bookingservice.model.Tourist;
import com.aresky.bookingservice.repository.TouristRepository;
import com.aresky.bookingservice.service.account.AccountGrpcService;
import com.aresky.bookingservice.service.payment.IVnPayService;
import grpc.payment.vnpay.BookingInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.dto.response.VnPayTransactionInfo;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.model.EPaymentType;
import com.aresky.bookingservice.repository.BookingRepository;
import com.aresky.bookingservice.service.payment.PaymentService;
import com.aresky.bookingservice.service.tour.TourGrpcService;

import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingDtoServiceImp implements IBookingDtoService {

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TouristRepository touristRepository;

    // @Autowired
    // private TourService tourService;

    // @Autowired
    // private AccountService accountService;

    @Autowired
    private AccountGrpcService accountGrpcService;

    @Autowired
    private TourGrpcService tourGrpcService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private IVnPayService vnPayService;

    @Transactional
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

                    Mono<Booking> createBookingMono = bookingService.createBooking(tuple.getT3(), form)
                            .switchIfEmpty(Mono.error(new BookingException(BookingException.BOOKING_FAILED)));

                    if(type.equals(EPaymentType.PAY_LATER)){
                        return createBookingMono
                                .map(booking -> "Đặt tour thành công, vui lòng kiểm tra thông tin chi tiết trong hồ sơ của bạn!");
                    }

                    if (type.equals(EPaymentType.VNPAY_ON_WEBSITE)){
                        SubTourResponse subTour = tuple.getT3();
                        return createBookingMono
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
                })
                .doOnError(Mono::error);
    }

    @Override
    public Mono<String> handleBookingAfterPaymentWithVnPay(VnPayReturn vnPayReturn) {
        String responseCode = vnPayReturn.getResponseCode();

        return bookingRepository.findById(vnPayReturn.getBookingId())
                .flatMap(booking -> {
                    if (responseCode.equals("00") || Integer.parseInt(responseCode) == 0) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        booking.setStatus(EBookingStatus.PAY_UP);
                        return paymentService.requestCloseVnPayPaymentSession(vnPayReturn)
                                .flatMap(message -> {
                                    return bookingRepository.save(booking)
                                            .thenReturn("SUCCESS");
                                });
                    }

                    if (responseCode.equals("24") || Integer.parseInt(responseCode) == 24) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        paymentService.requestCloseSession(vnPayReturn.getBookingId());
                        return bookingRepository.delete(booking)
                                .thenReturn("CANCELED");
                    }

                    booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                    booking.setStatus(EBookingStatus.PAY_FAILED);
                    paymentService.requestCloseSession(vnPayReturn.getBookingId());
                    return bookingRepository.save(booking)
                            .thenReturn("FAILED");
                });
    }

    @Override
    public Mono<String> handlePaymentWithVnPayAfterBooking(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .switchIfEmpty(Mono.error(
                        new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(booking -> findSubTour(booking.getSubTourId())
                        .flatMap(subTour -> paymentService
                                .getVnPayPaymentURL(PaymentRequest.createDTO(booking,
                                        subTour))));
    }

    @Override
    public Mono<String> handlePaymentAfterBooking(Integer bookingId, EFormOfPayment formOfPayment) {
        return bookingRepository.findById(bookingId)
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
        return bookingService.existsBookingBy(bookingId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(isExists -> bookingRepository.deleteById(bookingId)
                        .then());
    }

    private Mono<List<Tourist>> findAllTourist(Integer bookingId) {
        return bookingService.findAllTourists(bookingId);
    }

    private Mono<Booking> findByAccountIdAndSubTourId(Integer accountId, Integer subTourId) {
        return bookingService.findBookingBy(accountId, subTourId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.BOOKING_DOES_NOT_EXISTS)));
    }

    private Mono<Boolean> validateAccount(Integer accountId) {
        return accountGrpcService.validateAccount(accountId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_ACCOUNT_ID)));
    }

    private Mono<SubTourResponse> findSubTour(Integer subTourId) {
        return tourGrpcService.getSubTourById(subTourId)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_SUB_TOUR_ID)));
    }

    @Transactional
    private Mono<Booking> create(SubTourResponse subTour, Integer accountId, CreateBookingForm form) {
        validateTouristList(form);
        validateAmount(subTour, form);

        Booking booking = CreateBookingForm.buildBooking(form);
        booking.setTourId(subTour.getTourId());
        booking.setTourCode(subTour.getTourCode());
        booking.setStatus(EBookingStatus.NOT_PAY);

        List<Tourist> tourists = form.getTouristList().stream()
                .map(dto -> {
                    Tourist tourist = TouristRequest.buildTourist(dto);
                    tourist.setBookingId(booking.getId());
                    return tourist;
                })
                .toList();

        return bookingRepository.save(booking)
                .flatMap(savedBooking -> {
                    tourists.forEach(tourist -> tourist.setBookingId(savedBooking.getId()));
                    return touristRepository.saveAll(tourists)
                            .collectList()
                            .thenReturn(savedBooking);
                });
    }

    private void validateAmount(SubTourResponse subTour, CreateBookingForm form) {
        Integer adultPrice = subTour.getAdultPrice();
        Integer childrenPrice = subTour.getAdultPrice();
        Integer babyPrice = subTour.getBabyPrice();

        Integer adultNumber = form.getAdultNumber();
        Integer childrenNumber = form.getChildrenNumber();
        Integer babyNumber = form.getBabyNumber();

        Integer amount = (adultNumber * adultPrice) + (childrenNumber * childrenPrice)
                + (babyNumber * babyPrice);
        System.out.println("amount: " + amount);

        if (!amount.equals(form.getAmount())) {
            throw new BookingException(BookingException.INVALID_AMOUNT);
        }
    }

    private void validateTouristList(CreateBookingForm form) {
        List<TouristRequest> touristRequests = form.getTouristList();

        int adultNumber = 0;
        int childrenNumber = 0;
        int babyNumber = 0;

        for (TouristRequest tourist : touristRequests) {
            switch (tourist.getType()) {
                case ADULT:
                    adultNumber += 1;
                    break;
                case CHILDREN:
                    childrenNumber += 1;
                    break;
                case BABY:
                    babyNumber += 1;
                    break;
                default:
                    break;
            }
        }

        if (adultNumber != form.getAdultNumber()) {
            throw new BookingException(BookingException.INVALID_ADULT_NUMBER);
        }

        if (childrenNumber != form.getChildrenNumber()) {
            throw new BookingException(BookingException.INVALID_CHILD_NUMBER);
        }

        if (babyNumber != form.getBabyNumber()) {
            throw new BookingException(BookingException.INVALID_BABY_NUMBER);
        }
    }
}
