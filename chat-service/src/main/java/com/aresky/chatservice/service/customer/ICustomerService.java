package com.aresky.chatservice.service.customer;

import com.aresky.chatservice.entity.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    Mono<Customer> save(Customer customer);
    Mono<Boolean> isExistsByEmail (String email);
    Mono<Boolean>  isExistsByAccountId (int accountId);
    Mono<Customer> findById(int customerId);
    Mono<Customer> findByEmail(String email);
    Mono<Customer> findByAccountId(int accountId);
    Mono<Void> deleteById(int customerId);
}
