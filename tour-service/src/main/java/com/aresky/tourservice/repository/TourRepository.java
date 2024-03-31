package com.aresky.tourservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.tourservice.model.Tour;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TourRepository extends R2dbcRepository<Tour, Integer> {

    Flux<Tour> findAllBy(Pageable pageable);

    Mono<Tour> findByTitle(String title);

    Mono<Boolean> existsByTitle(String title);
}
