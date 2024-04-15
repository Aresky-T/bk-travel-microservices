package com.aresky.bookingservice.service.booking;

import com.aresky.bookingservice.dto.request.BookingFilter;
import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.dto.request.UpdateBookingForm;
import com.aresky.bookingservice.dto.request.VnPayReturn;
import com.aresky.bookingservice.dto.response.BookingDetails;
import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.dto.response.VnPayTransactionInfo;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.model.EPaymentType;

import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface IBookingService {
    Mono<String> handleBooking(CreateBookingForm form, EPaymentType type);

    Mono<String> handleBookingAfterPaymentWithVnPay(VnPayReturn vnPayReturn);

    Mono<String> handlePaymentWithVnPayAfterBooking(Integer bookingId);

    Mono<String> handlePaymentAfterBooking(Integer bookingId, EFormOfPayment formOfPayment);

    Mono<Page<BookingResponse>> findAll(Pageable pageable);

    Mono<List<BookingResponse>> findAll(Integer accountId);

    Mono<Page<BookingResponse>> findAll(Pageable pageable, BookingFilter filter);

    Mono<VnPayTransactionInfo> findVnPayTransactionInfo(Integer bookingId);

    Mono<BookingDetails> findOne(Integer bookingId);

    Mono<BookingDetails> findOne(Integer accountId, Integer subTourId);

    Mono<BookingResponse> update(UpdateBookingForm form);

    Mono<BookingResponse> update(Integer bookingId, Map<String, Object> fields);

    Mono<Void> delete(Integer bookingId);
}
