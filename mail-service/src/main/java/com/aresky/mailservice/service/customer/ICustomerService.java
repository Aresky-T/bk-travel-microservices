package com.aresky.mailservice.service.customer;

import com.aresky.mailservice.entity.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    Mono<Customer> register(String email, String fullName);

    Mono<Boolean> existsById(Integer customerId);

    Mono<Boolean> existsByEmail(String email);

    Mono<Customer> getById(Integer customerId);

    Mono<Customer> getByEmail(String email);

    Mono<Void> deleteById(Integer customerId);
}
