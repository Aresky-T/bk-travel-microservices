package com.aresky.bookingservice.service.booking;

import com.aresky.bookingservice.dto.request.*;
import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.model.*;

import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface IBookingService {
    Mono<List<Booking>> findAllBookings();
    Mono<Page<Booking>> findAllBookings(Pageable pageable);
    Mono<List<Booking>> findAllBookings(Integer accountId);
    Mono<Page<Booking>> findAllBookings(Pageable pageable, BookingFilter filter);
    Mono<List<Booking>> findAllBookings(EBookingStatus status);
    Mono<Booking> findBookingBy(Integer bookingId);
    Mono<Booking> findBookingBy(Integer accountId, Integer subTourId);
    Mono<List<Tourist>> findAllTourists(Integer bookingId);
    Mono<Booking> createBooking(SubTourResponse subTour, CreateBookingForm form, EPaymentType paymentType);
    Mono<Booking> saveBooking(Booking booking);
    Mono<Booking> update(UpdateBookingForm form);
    Mono<Booking> update(Integer bookingId, Map<String, Object> fields);
    Mono<Void> delete(Integer bookingId);
    Mono<Void> delete(Booking booking);
    Mono<List<Tourist>> setBookingForTouristList(List<Tourist> tourists, Integer bookingId);
    Mono<Boolean> existsBookingBy(Integer bookingId);
    Mono<Boolean> existsBookingBy(Integer accountId, Integer subTourId);
    Mono<Boolean> validateAmount(SubTourResponse subTour, CreateBookingForm form);
    Mono<Boolean> validateTouristList(CreateBookingForm form);
    Mono<Integer> calculateTouristNumberByTouristType(List<TouristRequest> tourists, ETouristType type);
}
