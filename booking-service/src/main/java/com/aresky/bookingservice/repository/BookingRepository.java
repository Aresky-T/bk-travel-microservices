package com.aresky.bookingservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.Booking;

public interface BookingRepository extends R2dbcRepository<Booking, Integer> {

}
