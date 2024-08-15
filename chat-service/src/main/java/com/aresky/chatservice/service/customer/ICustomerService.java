package com.aresky.chatservice.service.customer;

import com.aresky.chatservice.dto.request.CustomerRequest;
import com.aresky.chatservice.dto.response.CustomerResponse;
import com.aresky.chatservice.entity.Customer;
import com.aresky.chatservice.entity.EActivationStatus;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    Mono<CustomerResponse> register(CustomerRequest request);

    Mono<Customer> save(Customer customer);

    Mono<Boolean> existsById(Integer customerId);

    Mono<Boolean> existsByEmail(String email);

    Mono<Customer> getById(Integer customerId);

    Mono<Customer> getByEmail(String email);

    Mono<Void> deleteById(Integer customerId);

    Mono<Customer> updateActivationStatus(Integer customerId, EActivationStatus status);
}
