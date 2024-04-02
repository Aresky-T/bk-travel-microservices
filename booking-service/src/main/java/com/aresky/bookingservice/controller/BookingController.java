package com.aresky.bookingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.bookingservice.dto.response.FormOfPaymentResponse;
import com.aresky.bookingservice.service.FOP.IFormOfPaymentService;

import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private IFormOfPaymentService formOfPaymentService;

    // POST - createBooking(CreateBookingForm form)
    // @PostMapping
    // public Mono<ResponseEntity<?>> createBooking(@RequestBody CreateBookingForm
    // form){

    // }

    // POST - createBookingWithPayment(CreateBookingForm form)
    // POST - createRequestCancelBookedTour(CreateCancelBookedTourForm)
    // GET - getAllBookings(Pageable pageable)
    // GET - getAllBookings(Pageable pageable, BookingFilter filter)
    // GET - getAllBookingsByAccountId(int accountId)
    // GET - getDetailsBooking(int bookingId)
    // GET - getDetailsBooking(int accountId, subTourId)

    // GET - getAllFormOfPayments();
    @GetMapping("/form-of-payment")
    public Mono<ResponseEntity<List<FormOfPaymentResponse>>> getAllFormOfPayments() {
        return formOfPaymentService.findAll().map(ResponseEntity::ok);
    }

    // GET - getFormOfPaymentById(Integer id);
    @GetMapping("/form-of-payment/id")
    public Mono<ResponseEntity<FormOfPaymentResponse>> getFormOfPayment(@RequestParam Integer id) {
        return formOfPaymentService.findById(id).map(ResponseEntity::ok);
    }

    // GET - getFormOfPaymentByName(String name);
    @GetMapping("/form-of-payment/name")
    public Mono<ResponseEntity<FormOfPaymentResponse>> getFormOfPayment(@RequestParam String name) {
        return formOfPaymentService.findByName(name).map(ResponseEntity::ok);
    }

    // PUT - updateBooking(int bookingId, UpdateBookingForm form)
    // PATCH - updateBooking(int bookingId, EFormOfPayment formOfPayment)
    // PATCH - updateBooking(int bookingId, EStatus status)
    // PATCH - updateRequestCancelBookedTour(int requestId, ERequestStatus status);
    // DELETE - deleteBooking(int bookingId)
}
