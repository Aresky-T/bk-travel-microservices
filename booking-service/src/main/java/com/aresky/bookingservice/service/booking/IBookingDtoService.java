package com.aresky.bookingservice.service.booking;

import com.aresky.bookingservice.dto.request.*;
import com.aresky.bookingservice.dto.response.BookingDetails;
import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.dto.response.CancellationRequestedResponse;
import com.aresky.bookingservice.dto.response.VnPayTransactionInfoResponse;
import com.aresky.bookingservice.model.EPaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface IBookingDtoService {
    Mono<String> handleBooking(CreateBookingForm form, EPaymentType type);

    Mono<Page<BookingResponse>> findAll(Pageable pageable);

    Mono<List<BookingResponse>> findAll(Integer accountId);

    Mono<Page<BookingResponse>> findAll(Pageable pageable, BookingFilter filter);

    Mono<VnPayTransactionInfoResponse> findVnPayTransactionInfo(Integer bookingId);

    Mono<BookingDetails> findOne(Integer bookingId);

    Mono<BookingDetails> findOne(Integer accountId, Integer subTourId);

    Mono<Boolean> existsBy(Integer accountId, Integer subTourId);

    Mono<BookingResponse> update(UpdateBookingForm form);

    Mono<BookingResponse> update(Integer bookingId, Map<String, Object> fields);

    Mono<Void> delete(Integer bookingId);

    Mono<CancellationRequestedResponse> sendCancellationBookingRequest(Integer accountId, CreateCancelBookedTourForm form);

    Mono<List<CancellationRequestedResponse>> findAllCancellationRequested(Integer page, Integer size);

    Mono<CancellationRequestedResponse> findCancellationRequested(Integer bookingId);

    Mono<Void> approveCancellationBookingRequestByBookingId(Integer bookingId);

    Mono<Void> rejectCancellationBookingRequestByBookingId(Integer bookingId);

    Mono<Void> deleteCancellationBookingRequestByBookingId(Integer bookingId);

    Mono<BookingResponse> updateStatus(Integer bookingId, BookingStatusUpdateRequest request);
}
