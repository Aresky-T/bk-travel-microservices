package com.aresky.bookingservice.service.booking;

import com.aresky.bookingservice.dto.request.BookingFilter;
import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.dto.request.UpdateBookingForm;
import com.aresky.bookingservice.dto.request.VnPayRequest;
import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.model.EFormOfPayment;

import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface IBookingService {
    Mono<Void> handleBooking(CreateBookingForm form);

    Mono<String> handleBookingWithPayment(CreateBookingForm form, EFormOfPayment formOfPayment);

    Mono<String> handleBookingAfterPaymentWithVnPay(VnPayRequest vnPayRequest);

    Mono<List<BookingResponse>> findAll(Pageable pageable);

    Mono<List<BookingResponse>> findAll(Pageable pageable, BookingFilter filter);

    Mono<BookingResponse> findOne(int bookingId);

    Mono<BookingResponse> findOne(int accountId, int subTourId);

    Mono<BookingResponse> update(UpdateBookingForm form);

    Mono<BookingResponse> update(int bookingId, Map<String, Object> fields);

    Mono<Void> delete(int bookingId);
}
