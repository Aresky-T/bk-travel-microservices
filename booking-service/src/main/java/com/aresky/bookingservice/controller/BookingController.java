package com.aresky.bookingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.dto.request.VnPayRequest;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.service.booking.IBookingService;

import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    // POST - createBooking(CreateBookingForm form)
    @PostMapping
    public Mono<ResponseEntity<?>> createBooking(
            @RequestBody CreateBookingForm form) {
        return bookingService.handleBooking(form).thenReturn(ResponseEntity.ok("success"));
    }

    // POST - createBookingWithPayment(CreateBookingForm form)
    @PostMapping("/payment")
    public Mono<ResponseEntity<?>> createBookingWithPayment(
            @RequestParam EFormOfPayment formOfPayment,
            @RequestBody CreateBookingForm form) {
        return bookingService.handleBookingWithPayment(form, formOfPayment)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/payment/vnpay-result")
    public Mono<ResponseEntity<?>> getBookingResult(@RequestBody VnPayRequest vnPayRequest) {
        return bookingService.handleBookingAfterPaymentWithVnPay(vnPayRequest).map(ResponseEntity::ok);
    }

    // POST - createRequestCancelBookedTour(CreateCancelBookedTourForm)
    // GET - getAllBookings(Pageable pageable)
    // GET - getAllBookings(Pageable pageable, BookingFilter filter)
    // GET - getAllBookingsByAccountId(int accountId)
    // GET - getDetailsBooking(int bookingId)
    // GET - getDetailsBooking(int accountId, subTourId)

    // PUT - updateBooking(int bookingId, UpdateBookingForm form)
    // PATCH - updateBooking(int bookingId, EFormOfPayment formOfPayment)
    // PATCH - updateBooking(int bookingId, EStatus status)
    // PATCH - updateRequestCancelBookedTour(int requestId, ERequestStatus status);
    // DELETE - deleteBooking(int bookingId)
}
