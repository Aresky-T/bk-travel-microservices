package com.aresky.bookingservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.Tourist;

public interface TouristRepository extends R2dbcRepository<Tourist, Integer> {

}
