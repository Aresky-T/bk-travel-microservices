package com.aresky.bookingservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.RequestCancelBooking;

public interface RequestCancelBookingRepository extends R2dbcRepository<RequestCancelBooking, Integer> {

}
