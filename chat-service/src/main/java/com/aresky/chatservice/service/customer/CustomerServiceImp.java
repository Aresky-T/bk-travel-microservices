package com.aresky.chatservice.service.customer;

import com.aresky.chatservice.dto.request.CustomerRequest;
import com.aresky.chatservice.entity.Customer;
import com.aresky.chatservice.exception.ChatException;
import com.aresky.chatservice.exception.ExceptionMessage;
import com.aresky.chatservice.mappers.http.CustomerMapper;
import com.aresky.chatservice.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImp implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Mono<Customer> register(CustomerRequest request) {
        return customerRepository.existsByEmail(request.getEmail())
                .flatMap(existsEmail -> {
                    if (existsEmail) {
                        return customerRepository.findByEmail(request.getEmail())
                                .flatMap(customer -> {
                                    customer.setFullName(request.getFullName());
                                    return customerRepository.save(customer);
                                });
                    }

                    return customerRepository.save(CustomerMapper.mapCustomerRequestToCustomer(request));
                });
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Boolean> existsById(Integer customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Mono<Customer> getById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Mono<Customer> getByEmail(String email) {
        return customerRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_CUSTOMER_EMAIL)));
    }

    @Override
    public Mono<Void> deleteById(Integer customerId) {
        return customerRepository.existsById(customerId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_CUSTOMER_ID)))
                .then(customerRepository.deleteById(customerId))
                .then();
    }
}
