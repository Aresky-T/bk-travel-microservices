package com.aresky.bookingservice.service.booking;

import java.util.List;
import java.util.Map;

import com.aresky.bookingservice.dto.request.TouristRequest;
import com.aresky.bookingservice.dto.response.BookingDetails;
import com.aresky.bookingservice.model.Tourist;
import com.aresky.bookingservice.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.bookingservice.dto.request.BookingFilter;
import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.dto.request.PaymentRequest;
import com.aresky.bookingservice.dto.request.UpdateBookingForm;
import com.aresky.bookingservice.dto.request.VnPayRequest;
import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.repository.BookingRepository;
import com.aresky.bookingservice.service.account.AccountService;
import com.aresky.bookingservice.service.payment.PaymentService;
import com.aresky.bookingservice.service.tour.TourService;

import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;

@Service
public class BookingServiceImp implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TouristRepository touristRepository;

    @Autowired
    private TourService tourService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentService paymentService;

    @Transactional
    @Override
    public Mono<Void> handleBooking(CreateBookingForm form) {
        int accountId = form.getAccountId();
        int subTourId = form.getSubTourId();

        return bookingRepository.existsByAccountIdAndSubTourId(accountId, subTourId)
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(
                        new BookingException("Bạn đã đặt tour này rồi, vui lòng kiểm tra trong hồ sơ của bạn!")))
                .then(Mono.zip(findSubTour(subTourId), validateAccount(accountId))
                        .flatMap(tuple -> this.create(tuple.getT1(), accountId, form).then()));
    }

    @Transactional
    @Override
    public Mono<String> handleBookingWithPayment(CreateBookingForm form, EFormOfPayment formOfPayment) {
        int accountId = form.getAccountId();
        int subTourId = form.getSubTourId();

        return bookingRepository.existsByAccountIdAndSubTourId(accountId, subTourId)
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(
                        new BookingException("Bạn đã đặt tour này rồi, vui lòng kiểm tra trong hồ sơ của bạn!")))
                .then(paymentService.connect())
                .then(Mono.zip(findSubTour(subTourId), validateAccount(accountId))
                        .flatMap(tuple -> {
                            SubTourResponse subTour = tuple.getT1();

                            if (formOfPayment.equals(EFormOfPayment.VNPAY_ON_WEBSITE)) {
                                return this.create(subTour, accountId, form)
                                        .flatMap(booking -> paymentService
                                                .getVnPayPaymentURL(PaymentRequest.createDTO(booking, subTour))
                                                .onErrorResume(ex -> bookingRepository.delete(booking)
                                                        .then(Mono.error(ex))));
                            }

                            return Mono.error(new BookingException("Hiện chỉ hỗ trợ thanh toán trực tuyến với VNPAY!"));
                        }));
    }

    @Override
    public Mono<String> handleBookingAfterPaymentWithVnPay(VnPayRequest vnPayRequest) {
        String responseCode = vnPayRequest.getResponseCode();

        return bookingRepository.findById(vnPayRequest.getBookingId())
                .flatMap(booking -> {
                    if (responseCode.equals("00") || Integer.parseInt(responseCode) == 0) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        booking.setStatus(EBookingStatus.PAY_UP);
                        paymentService.requestCloseVnPayPaymentSession(vnPayRequest);
                        return bookingRepository.save(booking)
                                .thenReturn("SUCCESS");
                    }

                    if (responseCode.equals("24") || Integer.parseInt(responseCode) == 24) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        paymentService.requestCloseVnPayPaymentSession(vnPayRequest);
                        return bookingRepository.delete(booking)
                                .thenReturn("CANCELED");
                    }

                    booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                    booking.setStatus(EBookingStatus.PAY_FAILED);
                    paymentService.requestCloseVnPayPaymentSession(vnPayRequest);
                    return bookingRepository.save(booking)
                            .thenReturn("FAILED");
                });
    }

    @Override
    public Mono<String> handlePaymentAfterBooking(Integer bookingId, EFormOfPayment formOfPayment) {
        return bookingRepository.findById(bookingId)
                .switchIfEmpty(Mono.error(new BookingException("Invalid bookingId")))
                .flatMap(booking -> findSubTour(booking.getSubTourId())
                        .switchIfEmpty(Mono.error(new BookingException("SubTour was not found!")))
                        .flatMap(
                                subTour -> paymentService.getVnPayPaymentURL(PaymentRequest.createDTO(booking, subTour))
                                        .onErrorResume(Mono::error)));
    }

    @Override
    public Mono<Page<BookingResponse>> findAll(Pageable pageable) {
        return bookingRepository.findAllBy(pageable)
                .map(BookingResponse::toDTO)
                .collectList()
                .map(dtos -> new PageImpl<>(dtos, pageable, dtos.size()));
    }

    @Override
    public Mono<List<BookingResponse>> findAll(Integer accountId) {
        return bookingRepository.findAllByAccountId(accountId)
                .switchIfEmpty(Mono.empty())
                .map(BookingResponse::toDTO)
                .collectList();
    }

    @Override
    public Mono<Page<BookingResponse>> findAll(Pageable pageable, BookingFilter filter) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<BookingDetails> findOne(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .switchIfEmpty(Mono.error(new BookingException("Invalid bookingId")))
                .flatMap(booking -> Mono.zip(
                        findSubTour(booking.getSubTourId()),
                        findAllTourist(bookingId)).flatMap(tuple -> {
                            SubTourResponse subTour = tuple.getT1();
                            List<Tourist> touristList = tuple.getT2();
                            return Mono.just(BookingDetails.toDTO(booking, subTour, touristList));
                        }))
                .onErrorResume(ex -> {
                    return Mono.error(ex);
                });
    }

    @Override
    public Mono<BookingDetails> findOne(Integer accountId, Integer subTourId) {
        return Mono.zip(validateAccount(accountId), findSubTour(subTourId))
                .onErrorResume(Mono::error)
                .flatMap(tuple -> findByAccountIdAndSubTourId(accountId, subTourId)
                        .flatMap(booking -> findAllTourist(booking.getId())
                                .map(tourist -> BookingDetails.toDTO(booking, tuple.getT2(), tourist))));
    }

    @Override
    public Mono<BookingResponse> update(UpdateBookingForm form) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<BookingResponse> update(Integer bookingId, Map<String, Object> fields) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<Void> delete(Integer bookingId) {
        return bookingRepository.existsById(bookingId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new BookingException("Invalid bookingId!")))
                .flatMap(isExists -> bookingRepository.deleteById(bookingId)
                        .then());
    }

    private Mono<List<Tourist>> findAllTourist(Integer bookingId) {
        return touristRepository.findAllByBookingId(bookingId).switchIfEmpty(Mono.empty()).collectList();
    }

    private Mono<Booking> findByAccountIdAndSubTourId(Integer accountId, Integer subTourId) {
        return bookingRepository.findByAccountIdAndSubTourId(accountId, subTourId)
                .switchIfEmpty(Mono.error(new BookingException("This Booking info does not exist!")));
    }

    private Mono<Boolean> validateAccount(Integer accountId) {
        return accountService.validateAccount(accountId);
    }

    private Mono<SubTourResponse> findSubTour(Integer subTourId) {
        return tourService.findSubTour(subTourId);
    }

    @Transactional
    private Mono<Booking> create(SubTourResponse subTour, Integer accountId, CreateBookingForm form) {
        Booking booking = CreateBookingForm.buildBooking(form);
        booking.setTourId(subTour.getTourId());
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
}
