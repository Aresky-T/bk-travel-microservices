package com.aresky.bookingservice.delivery.grpc;

import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.service.booking.IBookingService;
import grpc.booking.*;
import grpc.booking.constants.PaymentMethod;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.Objects;

@GrpcService(interceptors = {BookingGrpcInterceptor.class})
public class ReactorBookingGrpcProvider extends ReactorBookingServiceGrpc.BookingServiceImplBase {
    @Autowired
    private IBookingService bookingService;

    @Override
    public Mono<ExistBookingResponse> checkExistBookingById(Mono<BookingIdRequest> request) {
        System.out.println("Request checking exist booking by id...");
        return request.map(BookingIdRequest::getId)
                .flatMap(bookingId -> bookingService.existsBookingBy(bookingId)
                        .map(exists -> ExistBookingResponse.newBuilder().setIsExists(exists).build()));
    }

    @Override
    public Mono<UpdateBookingResponse> updateBookingAfterPaymentSucceeded(Mono<UpdateBookingAfterPaymentRequest> request) {
        return request.flatMap(req -> {
            System.out.println("Request update booking after payment succeeded...");
            Integer bookingId = req.getBookingId();
            PaymentMethod method = req.getPaymentMethod();

            return bookingService.findBookingBy(bookingId)
                    .filter(Objects::nonNull)
                    .flatMap(booking -> {
                        booking.setStatus(EBookingStatus.PAY_UP);

                        if (method == PaymentMethod.VNPAY) {
                            booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        } else {
                            booking.setFormOfPayment(null);
                        }

                        return bookingService.saveBooking(booking)
                                .then()
                                .thenReturn(UpdateBookingResponse.newBuilder().setMessage("SUCCESS").build());
                    })
                    .switchIfEmpty(Mono.just(UpdateBookingResponse.newBuilder().setMessage(BookingException.INVALID_BOOKING_ID).build()));
        });
    }

    @Override
    public Mono<UpdateBookingResponse> updateBookingAfterPaymentFailed(Mono<UpdateBookingAfterPaymentRequest> request) {
        return request.flatMap(req -> {
            System.out.println("Request update booking after payment failed...");
            Integer bookingId = req.getBookingId();
            PaymentMethod method = req.getPaymentMethod();

            return bookingService.findBookingBy(bookingId)
                    .filter(Objects::nonNull)
                    .flatMap(booking -> {
                        booking.setStatus(EBookingStatus.PAY_FAILED);

                        if (method == PaymentMethod.VNPAY) {
                            booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                        } else {
                            booking.setFormOfPayment(null);
                        }

                        return bookingService.saveBooking(booking)
                                .then()
                                .thenReturn(UpdateBookingResponse.newBuilder().setMessage("SUCCESS").build());
                    })
                    .switchIfEmpty(Mono.just(UpdateBookingResponse.newBuilder().setMessage(BookingException.INVALID_BOOKING_ID).build()));
        });
    }

    @Override
    public Mono<UpdateBookingResponse> updateBookingAfterPaymentCanceled(Mono<BookingIdRequest> request) {
        System.out.println("Request update booking after payment canceled...");
        return request.map(BookingIdRequest::getId)
                .flatMap(bookingId -> bookingService.findBookingBy(bookingId)
                        .filter(Objects::nonNull)
                        .flatMap(booking -> bookingService.delete(booking)
                                .thenReturn(UpdateBookingResponse.newBuilder().setMessage("SUCCESS").build()))
                        .switchIfEmpty(Mono.just(UpdateBookingResponse.newBuilder().setMessage(BookingException.INVALID_BOOKING_ID).build())));
    }
}
