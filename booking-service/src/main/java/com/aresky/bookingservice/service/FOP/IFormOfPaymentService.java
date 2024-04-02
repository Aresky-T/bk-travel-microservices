package com.aresky.bookingservice.service.FOP;

import com.aresky.bookingservice.dto.response.FormOfPaymentResponse;

import reactor.core.publisher.Mono;

import java.util.List;

public interface IFormOfPaymentService {
    Mono<List<FormOfPaymentResponse>> findAll();

    Mono<FormOfPaymentResponse> findById(int id);

    Mono<FormOfPaymentResponse> findByName(String name);
}
