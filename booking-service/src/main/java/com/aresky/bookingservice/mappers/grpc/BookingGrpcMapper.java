package com.aresky.bookingservice.mappers.grpc;

import com.aresky.bookingservice.model.Booking;
import grpc.booking.BookingResponse;
import grpc.booking.constants.BookingStatus;

public class BookingGrpcMapper {

    public static BookingResponse mapBookingToBookingResponse(Booking booking){
        return BookingResponse.newBuilder()
                .setBookingId(booking.getId())
                .setSubTourId(booking.getSubTourId())
                .setTourCode(booking.getTourCode())
                .setAmount(booking.getAmount())
                .setStatus(BookingStatus.valueOf(booking.getStatus().name()))
                .build();
    }
}
