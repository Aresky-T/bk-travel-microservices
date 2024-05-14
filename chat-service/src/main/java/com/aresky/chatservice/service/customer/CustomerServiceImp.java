package com.aresky.chatservice.service.customer;

import com.aresky.chatservice.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImp implements ICustomerService {
    @Override
    public Mono<Customer> save(Customer customer) {
        return null;
    }

    @Override
    public Mono<Boolean> isExistsByEmail(String email) {
        return null;
    }

    @Override
    public Mono<Boolean> isExistsByAccountId(int accountId) {
        return null;
    }

    @Override
    public Mono<Customer> findById(int customerId) {
        return null;
    }

    @Override
    public Mono<Customer> findByEmail(String email) {
        return null;
    }

    @Override
    public Mono<Customer> findByAccountId(int accountId) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(int customerId) {
        return null;
    }
}
