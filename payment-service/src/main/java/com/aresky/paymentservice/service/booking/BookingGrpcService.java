package com.aresky.paymentservice.service.booking;

import com.aresky.paymentservice.exception.PaymentException;
import grpc.booking.*;
import grpc.booking.constants.PaymentMethod;
import org.springframework.stereotype.Service;

import grpc.booking.ReactorBookingServiceGrpc.ReactorBookingServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class BookingGrpcService implements IBookingService {
    private static final String HOST = "booking-service";
    private static final Integer POST = 50084;
    private static final long TIMEOUT = 5000;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        this.channel = ManagedChannelBuilder.forAddress(HOST, POST).usePlaintext().build();
    }

    @PreDestroy
    public void stop() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdownNow();
        }
    }

    @Override
    public boolean checkExistBookingBy(Integer bookingId) {
        ReactorBookingServiceStub stub = initStub();
        BookingIdRequest request = BookingIdRequest.newBuilder().setId(bookingId).build();
        return Objects.requireNonNull(stub.checkExistBookingById(request).block()).getIsExists();
    }

    @Override
    public void updateBookingAfterPaymentSucceeded(Integer bookingId, PaymentMethod method) {
        ReactorBookingServiceStub stub = initStub();
        UpdateBookingAfterPaymentRequest request = UpdateBookingAfterPaymentRequest.newBuilder()
                .setBookingId(bookingId)
                .setPaymentMethod(method)
                .build();
        try {
            UpdateBookingResponse response = stub.updateBookingAfterPaymentSucceeded(request).block();
            if (response != null && !response.getMessage().equalsIgnoreCase("SUCCESS")) {
                throw new PaymentException(response.getMessage());
            }

            System.out.println("Response of updateBookingAfterPaymentSucceeded: " + response.getMessage());
        } catch (Exception ex) {
            throw new PaymentException(ex.getMessage());
        }
    }

    @Override
    public void updateBookingAfterPaymentFailed(Integer bookingId, PaymentMethod method) {
        ReactorBookingServiceStub stub = initStub();
        UpdateBookingAfterPaymentRequest request = UpdateBookingAfterPaymentRequest.newBuilder()
                .setBookingId(bookingId)
                .setPaymentMethod(method)
                .build();
        try {
            UpdateBookingResponse response = stub.updateBookingAfterPaymentFailed(request).block();
            if (response != null && !response.getMessage().equalsIgnoreCase("SUCCESS")) {
                throw new PaymentException(response.getMessage());
            }

            System.out.println("Response of updateBookingAfterPaymentFailed: " + response.getMessage());
        } catch (Exception ex) {
            throw new PaymentException(ex.getMessage());
        }
    }

    @Override
    public void updateBookingAfterPaymentCanceled(Integer bookingId) {
        ReactorBookingServiceStub stub = initStub();
        BookingIdRequest request = BookingIdRequest.newBuilder().setId(bookingId).build();
        stub.updateBookingAfterPaymentCanceled(request)
                .subscribe(res -> {
                    System.out.println("Response update booking: " + res.getMessage());
                    if(!res.getMessage().equalsIgnoreCase("SUCCESS")){
                        throw new RuntimeException("Update booking failed");
                    }
                }, err -> {
                    throw new PaymentException(err.getMessage());
                });
    }

    @Override
    public BookingResponse getBookingById(Integer bookingId){
        ReactorBookingServiceStub stub = initStub();
        BookingIdRequest request = BookingIdRequest.newBuilder().setId(bookingId).build();
        try {
            GetBookingByIdResponse response = stub.getBookingById(request).block();
            if(response != null && response.hasBooking()){
                return response.getBooking();
            }
        } catch (Exception ex) {
            throw new PaymentException(ex.getMessage());
        }

        return null;
    }

    private ReactorBookingServiceStub initStub() {
        return ReactorBookingServiceGrpc.newReactorStub(this.channel).withDeadlineAfter(TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
