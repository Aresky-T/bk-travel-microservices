package com.aresky.bookingservice.repository;

import com.aresky.bookingservice.model.CancellationRequested;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CancellationRequestedRepository extends R2dbcRepository<CancellationRequested, Integer> {

    Mono<CancellationRequested> findByBookingId(Integer bookingId);

    Mono<Boolean> existsByBookingId(Integer bookingId);
}
