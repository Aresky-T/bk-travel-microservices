package com.aresky.mailservice.repository;

import com.aresky.mailservice.entity.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ICustomerRepository extends R2dbcRepository<Customer, Integer> {
    Mono<Boolean> existsByEmail(String email);

    Mono<Customer> findByEmail(String email);
}
