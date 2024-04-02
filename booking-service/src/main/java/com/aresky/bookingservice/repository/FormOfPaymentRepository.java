package com.aresky.bookingservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.bookingservice.model.FormOfPayment;

import reactor.core.publisher.Mono;

public interface FormOfPaymentRepository extends R2dbcRepository<FormOfPayment, Integer> {
    Mono<FormOfPayment> findByName(String name);
}
