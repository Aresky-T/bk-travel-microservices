package com.aresky.bookingservice.delivery.http;

import com.aresky.bookingservice.dto.request.BookingFilter;
import com.aresky.bookingservice.dto.request.BookingStatusUpdateRequest;
import com.aresky.bookingservice.dto.request.CreateCancelBookedTourForm;
import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.dto.response.CancellationRequestedResponse;
import com.aresky.bookingservice.service.booking.IBookingDtoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.model.EPaymentType;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private IBookingDtoService bookingService;

    // POST - createBooking(CreateBookingForm form)
    @PostMapping
    public Mono<ResponseEntity<?>> createBooking(
            @RequestParam EPaymentType type,
            @Valid @RequestBody CreateBookingForm form) {
        return bookingService.handleBooking(form, type).map(ResponseEntity::ok);
    }

    // GET - getVnPayTransactionInfo(Integer bookingId)
    @GetMapping("/payment/vnpay-transaction-info")
    public Mono<ResponseEntity<?>> getVnPayTransactionInfo(@RequestParam Integer bookingId) {
        return bookingService.findVnPayTransactionInfo(bookingId).map(ResponseEntity::ok);
    }

    // GET - getAllBookings(Pageable pageable)
    @GetMapping
    public Mono<ResponseEntity<?>> getAllBookings(Pageable pageable) {
        return bookingService.findAll(pageable).map(ResponseEntity::ok);
    }

    // GET - getAllBookings(Pageable pageable, BookingFilter filter)
    @GetMapping("/filter")
    public Mono<ResponseEntity<?>> getAllBookings(Pageable pageable, BookingFilter filter) {
        return bookingService.findAll(pageable, filter).map(ResponseEntity::ok);
    }

    // GET - getAllBookingsByAccountId(int accountId)
    @GetMapping("/account")
    public Mono<ResponseEntity<?>> getDetailsBookingByAccountId(@RequestParam Integer accountId) {
        return bookingService.findAll(accountId).map(ResponseEntity::ok);
    }

    // GET - getDetailsBooking(int bookingId)
    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<?>> getDetailsBooking(@PathVariable("id") Integer bookingId) {
        return bookingService.findOne(bookingId).map(ResponseEntity::ok);
    }

    // GET - getDetailsBooking(int accountId, subTourId)
    @GetMapping("/account-and-tour")
    public Mono<ResponseEntity<?>> getDetailsBooking(
            @RequestParam Integer accountId,
            @RequestParam Integer subTourId) {
        return bookingService.findOne(accountId, subTourId).map(ResponseEntity::ok);
    }

    @GetMapping("/check-booked-tour")
    public Mono<ResponseEntity<Boolean>> getResultOfCheckExistBookingByAccountAndTour(
            @RequestParam Integer accountId,
            @RequestParam Integer subTourId) {
        return bookingService.existsBy(accountId, subTourId).map(ResponseEntity::ok);
    }

    @PatchMapping("/status")
    public Mono<ResponseEntity<BookingResponse>> updateBookingStatus(
            @RequestParam Integer bookingId,
            @Validated @RequestBody BookingStatusUpdateRequest request
    ){
        return bookingService.updateStatus(bookingId, request).map(ResponseEntity::ok);
    }

    @GetMapping("/cancellation-requested")
    public Mono<ResponseEntity<CancellationRequestedResponse>> getCancellationRequested(@RequestParam Integer bookingId){
        return bookingService.findCancellationRequested(bookingId).map(ResponseEntity::ok);
    }

    @GetMapping("/cancellation-requested/all")
    public Mono<ResponseEntity<?>> getAllCancellationRequested(
            @RequestParam Integer page,
            @RequestParam Integer size
    ){
        return bookingService.findAllCancellationRequested(page, size).map(ResponseEntity::ok);
    }

    // PUT - updateBooking(int bookingId, UpdateBookingForm form)
    // PATCH - updateBooking(int bookingId, EFormOfPayment formOfPayment)
    // PATCH - updateBooking(int bookingId, EStatus status)
    // PATCH - updateRequestCancelBookedTour(int requestId, ERequestStatus status);

    @PostMapping("/cancellation-requested")
    public Mono<ResponseEntity<?>> sendRequestCancelBookedTour(
            @RequestParam("accountId") Integer accountId,
            @RequestBody CreateCancelBookedTourForm form
    ){
        return bookingService.sendCancellationBookingRequest(accountId, form).map(ResponseEntity::ok);
    }

    @PatchMapping("/cancellation-requested/approve")
    public Mono<ResponseEntity<?>> acceptRequestCancelBookedTour(@RequestParam Integer bookingId){
        return bookingService.approveCancellationBookingRequestByBookingId(bookingId).thenReturn(ResponseEntity.ok("success"));
    }

    @PatchMapping("/cancellation-requested/reject")
    public Mono<ResponseEntity<?>> rejectRequestCancelBookedTour(@RequestParam Integer bookingId){
        return bookingService.rejectCancellationBookingRequestByBookingId(bookingId).thenReturn( ResponseEntity.ok("success"));
    }

    // DELETE - deleteBooking(int bookingId)
    @DeleteMapping
    public Mono<ResponseEntity<?>> deleteBooking(@RequestParam("id") Integer bookingId) {
        return bookingService.delete(bookingId).thenReturn(ResponseEntity.ok("success"));
    }

    @DeleteMapping("/cancellation-requested")
    public Mono<ResponseEntity<?>> deleteCancellationRequested(@RequestParam Integer bookingId){
        return bookingService.deleteCancellationBookingRequestByBookingId(bookingId).thenReturn( ResponseEntity.ok("success"));
    }
}
