package com.aresky.bookingservice.service.FOP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aresky.bookingservice.dto.response.FormOfPaymentResponse;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.repository.FormOfPaymentRepository;

import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FormOfPaymentServiceImp implements IFormOfPaymentService {

    @Autowired
    private FormOfPaymentRepository repository;

    @Override
    public Mono<List<FormOfPaymentResponse>> findAll() {
        return repository.findAll()
                .switchIfEmpty(Mono.empty())
                .map(FormOfPaymentResponse::toDTO)
                .collectList();
    }

    @Override
    public Mono<FormOfPaymentResponse> findById(int id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new BookingException("Invalid form_of_payment id")))
                .map(FormOfPaymentResponse::toDTO);
    }

    @Override
    public Mono<FormOfPaymentResponse> findByName(String name) {
        return repository.findByName(name)
                .switchIfEmpty(Mono.error(new BookingException("Invalid form_of_payment name")))
                .map(FormOfPaymentResponse::toDTO);
    }
}
