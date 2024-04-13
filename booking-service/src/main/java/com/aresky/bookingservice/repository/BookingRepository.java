package com.aresky.bookingservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.Booking;

import reactor.core.publisher.Mono;

public interface BookingRepository extends R2dbcRepository<Booking, Integer> {

    Mono<Booking> findByAccountIdAndSubTourId(Integer accountId, Integer subTourId);

    Mono<Boolean> existsByAccountIdAndSubTourId(Integer accountId, Integer subTourId);
}
