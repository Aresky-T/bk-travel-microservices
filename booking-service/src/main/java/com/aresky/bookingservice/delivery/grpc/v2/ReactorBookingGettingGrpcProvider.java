package com.aresky.bookingservice.delivery.grpc.v2;

import com.aresky.bookingservice.service.booking.IBookingService;
import com.aresky.bookingservice.util.DateUtils;
import grpc.booking.v2.dto.request.GetBookingByIdRequest;
import grpc.booking.v2.dto.response.GetBookingByIdResponse;
import grpc.booking.v2.model.Booking;
import grpc.booking.v2.service.ReactorBookingGettingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@GrpcService
public class ReactorBookingGettingGrpcProvider extends ReactorBookingGettingServiceGrpc.BookingGettingServiceImplBase {

    @Autowired
    private IBookingService bookingService;

    @Override
    public Mono<GetBookingByIdResponse> getBookingById(Mono<GetBookingByIdRequest> request) {
        System.out.println("Getting booking by id...");
        return request.map(GetBookingByIdRequest::getBookingId)
                .flatMap(bookingService::findBookingBy)
                .map(booking -> GetBookingByIdResponse.newBuilder().setBooking(mapToGrpcBooking(booking)).build())
                .switchIfEmpty(Mono.just(GetBookingByIdResponse.newBuilder().build()));
    }


    public Booking mapToGrpcBooking(com.aresky.bookingservice.model.Booking booking){
        Booking.Builder builder = Booking.newBuilder()
                .setId(booking.getId())
                .setBookingCode(booking.getBookingCode())
                .setAccountId(booking.getAccountId())
                .setTourId(booking.getTourId())
                .setSubTourId(booking.getSubTourId())
                .setTourCode(booking.getTourCode())
                .setAdultNumber(booking.getAdultNumber())
                .setChildNumber(booking.getChildrenNumber())
                .setBabyNumber(booking.getBabyNumber())
                .setAmount(booking.getAmount())
                .setBookedTime(DateUtils.formatDateTime(booking.getBookedTime()))
                .setStatus(booking.getStatus().name())
                .setIsCancellationRequested(booking.getIsCancellationRequested());

        if(booking.getFormOfPayment() != null){
            builder.setFormOfPayment(booking.getFormOfPayment().name());
        }

        return builder.build();
    }
}
