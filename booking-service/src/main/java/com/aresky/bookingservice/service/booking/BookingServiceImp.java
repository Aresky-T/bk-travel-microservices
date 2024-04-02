package com.aresky.bookingservice.service.booking;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.bookingservice.dto.request.BookingFilter;
import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.dto.request.UpdateBookingForm;
import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.model.FormOfPayment;
import com.aresky.bookingservice.repository.BookingRepository;
import com.aresky.bookingservice.repository.FormOfPaymentRepository;
import com.aresky.bookingservice.service.account.AccountService;
import com.aresky.bookingservice.service.tour.TourService;

import reactor.core.publisher.Mono;

@Service
public class BookingServiceImp implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FormOfPaymentRepository formOfPaymentRepository;

    @Autowired
    private TourService tourService;

    @Autowired
    private AccountService accountService;

    @Override
    public Mono<Void> handleBooking(CreateBookingForm form) {
        int accountId = form.getAccountId();
        int subTourId = form.getSubTourId();
        Mono<SubTourResponse> tourMono = tourService.findSubTour(subTourId);
        Mono<Boolean> isValidAccountMono = accountService.validateAccount(accountId);

        return Mono.zip(tourMono, isValidAccountMono)
                .flatMap(tuple -> {
                    SubTourResponse subTour = tuple.getT1();
                    boolean isValidAccount = tuple.getT2();

                    if (!isValidAccount) {
                        throw new BookingException("Invalid accountId");
                    }

                    if (subTour == null || subTour.getTourId() != form.getTourId()) {
                        throw new BookingException("Invalid tour");
                    }

                    Booking booking = CreateBookingForm.buildBooking(form);
                    booking.setStatus(EBookingStatus.NOT_PAY);

                    return bookingRepository.save(booking).then();
                });
    }

    @Override
    public Mono<String> handleBookingWithPayment(CreateBookingForm form, String formOfPayment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleBookingWithPayment'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public Mono<Void> create(CreateBookingForm form) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Mono<BookingResponse> update(UpdateBookingForm form) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<BookingResponse> update(int bookingId, Map<String, Object> fields) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<Void> delete(int bookingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
