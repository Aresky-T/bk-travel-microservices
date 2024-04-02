package com.aresky.bookingservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.BookingStatistic;

public interface BookingStatisticRepository extends R2dbcRepository<BookingStatistic, Integer> {

}
