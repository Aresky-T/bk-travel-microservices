package com.aresky.bookingservice.controller;

import com.aresky.bookingservice.dto.request.BookingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public Mono<ResponseEntity<?>> getAllBookings(Pageable pageable){
        return bookingService.findAll(pageable).map(ResponseEntity::ok);
    }

    // GET - getAllBookings(Pageable pageable, BookingFilter filter)
    @GetMapping("/filter")
    public Mono<ResponseEntity<?>> getAllBookings(Pageable pageable, BookingFilter filter){
        return bookingService.findAll(pageable, filter).map(ResponseEntity::ok);
    }

    // GET - getAllBookingsByAccountId(int accountId)
    @GetMapping("/account")
    public Mono<ResponseEntity<?>> getDetailsBookingByAccountId(@RequestParam Integer accountId){
        return bookingService.findAll(accountId).map(ResponseEntity::ok);
    }

    // GET - getDetailsBooking(int bookingId)
    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<?>> getDetailsBooking(@PathVariable("id") Integer bookingId){
        return bookingService.findOne(bookingId).map(ResponseEntity::ok);
    }

    // GET - getDetailsBooking(int accountId, subTourId)
    @GetMapping("/account-and-tour")
    public Mono<ResponseEntity<?>> getDetailsBooking(
            @RequestParam Integer accountId,
            @RequestParam Integer subTourId
    ){
        return bookingService.findOne(accountId, subTourId).map(ResponseEntity::ok);
    }

    // PUT - updateBooking(int bookingId, UpdateBookingForm form)
    // PATCH - updateBooking(int bookingId, EFormOfPayment formOfPayment)
    // PATCH - updateBooking(int bookingId, EStatus status)
    // PATCH - updateRequestCancelBookedTour(int requestId, ERequestStatus status);

    // DELETE - deleteBooking(int bookingId)
    @DeleteMapping
    public Mono<ResponseEntity<?>> deleteBooking(@RequestParam("id") Integer bookingId){
        return bookingService.delete(bookingId).thenReturn(ResponseEntity.ok("success"));
    }
}
