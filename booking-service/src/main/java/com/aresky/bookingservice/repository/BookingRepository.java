package com.aresky.bookingservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.Booking;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingRepository extends R2dbcRepository<Booking, Integer> {
    Flux<Booking> findAllBy(Pageable pageable);

    Flux<Booking> findAllByAccountId(Integer accountId);

    Mono<Booking> findByAccountIdAndSubTourId(Integer accountId, Integer subTourId);

    Mono<Boolean> existsByAccountIdAndSubTourId(Integer accountId, Integer subTourId);
}
