package com.aresky.bookingservice.service.booking;

import java.util.List;
import java.util.Map;

import com.aresky.bookingservice.dto.request.TouristRequest;
import com.aresky.bookingservice.model.Tourist;
import com.aresky.bookingservice.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        Mono<SubTourResponse> subTourMono = tourService.findSubTour(subTourId);
        Mono<Boolean> isValidAccountMono = accountService.validateAccount(accountId);

        return Mono.zip(subTourMono, isValidAccountMono)
                .flatMap(tuple -> {
                    SubTourResponse subTour = tuple.getT1();

                    return this.create(subTour, accountId, form)
                            .then();
                });
    }

    @Transactional
    @Override
    public Mono<String> handleBookingWithPayment(CreateBookingForm form, EFormOfPayment formOfPayment) {
        int accountId = form.getAccountId();
        int subTourId = form.getSubTourId();

        Mono<SubTourResponse> subTourMono = tourService.findSubTour(subTourId);
        Mono<Boolean> isValidAccountMono = accountService.validateAccount(accountId);
        Mono<Booking> bookingMono = bookingRepository.findByAccountIdAndSubTourId(form.getAccountId(),
                form.getSubTourId());

        return Mono.zip(subTourMono, isValidAccountMono, bookingMono)
                .flatMap(tuple -> {
                    SubTourResponse subTour = tuple.getT1();

                    if (formOfPayment.equals(EFormOfPayment.VNPAY_ON_WEBSITE)) {
                        return this.create(subTour, accountId, form)
                                .flatMap(booking -> {
                                    PaymentRequest paymentRequest = PaymentRequest.builder()
                                            .bookingId(booking.getId())
                                            .amount(form.getAmount())
                                            .tourCode(subTour.getTourCode())
                                            .build();

                                    return paymentService.getVnPayPaymentURL(paymentRequest)
                                            .onErrorResume(ex -> {
                                                return bookingRepository.delete(booking)
                                                        .then(Mono.error(ex));
                                            });
                                });
                    }

                    return Mono.error(new BookingException("Hiện chỉ hỗ trợ thanh toán trực tuyến với VNPAY!"));
                });
    }

    @Override
    public Mono<String> handleBookingAfterPaymentWithVnPay(VnPayRequest vnPayRequest) {
        String responseCode = vnPayRequest.getResponseCode();

        return bookingRepository.findById(vnPayRequest.getBookingId())
                .flatMap(booking -> {
                    if (responseCode.equals("00") || Integer.parseInt(responseCode) == 0) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        booking.setStatus(EBookingStatus.PAY_UP);
                        return bookingRepository.save(booking)
                                .then(paymentService.requestCloseVnPayPaymentSession(vnPayRequest))
                                .thenReturn("SUCCESS");
                    }

                    if (responseCode.equals("24") || Integer.parseInt(responseCode) == 24) {
                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        return bookingRepository.delete(booking)
                                .then(paymentService.requestCloseVnPayPaymentSession(vnPayRequest))
                                .thenReturn("CANCELED");
                    }

                    booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                    booking.setStatus(EBookingStatus.PAY_FAILED);
                    return bookingRepository.save(booking)
                            .then(paymentService.requestCloseVnPayPaymentSession(vnPayRequest))
                            .thenReturn("FAILED");
                });

    }

    @Override
    public Mono<List<BookingResponse>> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<List<BookingResponse>> findAll(Pageable pageable, BookingFilter filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<BookingResponse> findOne(int bookingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public Mono<BookingResponse> findOne(int accountId, int subTourId) {
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public Mono<BookingResponse> update(UpdateBookingForm form) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<BookingResponse> update(int bookingId, Map<String, Object> fields) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<Void> delete(int bookingId) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    // private Mono<Boolean> checkExistsAccountAndSubTour(Integer accountId, Integer
    // subTourId){
    //
    // }

    private Mono<Booking> findBy(Integer accountId, Integer subTourId) {
        return bookingRepository.findByAccountIdAndSubTourId(accountId, subTourId);
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
    // private Mono<List<Tourist>> convertToTouristList(int bookingId,
    // List<TouristRequest> touristRequests){

    // }

}
