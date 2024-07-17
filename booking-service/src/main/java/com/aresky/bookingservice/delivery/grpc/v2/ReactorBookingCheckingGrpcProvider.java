package com.aresky.bookingservice.delivery.grpc.v2;

import com.aresky.bookingservice.service.booking.IBookingService;
import grpc.booking.v2.dto.request.CheckBookingByIdRequest;
import grpc.booking.v2.dto.response.CheckBookingByIdResponse;
import grpc.booking.v2.service.ReactorBookingCheckingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@GrpcService
public class ReactorBookingCheckingGrpcProvider extends ReactorBookingCheckingServiceGrpc.BookingCheckingServiceImplBase {
    @Autowired
    private IBookingService bookingService;

    @Override
    public Mono<CheckBookingByIdResponse> checkBookingById(Mono<CheckBookingByIdRequest> request) {
        System.out.println("Checking booking by id...");
        return request.map(CheckBookingByIdRequest::getBookingId)
                .flatMap(bookingService::existsBookingBy)
                .map(isExists -> CheckBookingByIdResponse.newBuilder().setIsExists(isExists).build());
    }
}
