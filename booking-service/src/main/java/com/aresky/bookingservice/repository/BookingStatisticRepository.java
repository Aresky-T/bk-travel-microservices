package com.aresky.bookingservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.BookingStatistic;
import reactor.core.publisher.Mono;

public interface BookingStatisticRepository extends R2dbcRepository<BookingStatistic, Integer> {
    Mono<Boolean> existsBySubTourIdAndTourIdAndMonthAndYear(Integer subTourId, Integer tourId, Integer month, Integer year);
}
