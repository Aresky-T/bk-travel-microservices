package com.aresky.chatservice.service.customer;

import com.aresky.chatservice.dto.request.CustomerRequest;
import com.aresky.chatservice.dto.response.CustomerResponse;
import com.aresky.chatservice.entity.Customer;
import com.aresky.chatservice.entity.EActivationStatus;
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
    public Mono<CustomerResponse> register(CustomerRequest request) {
        return customerRepository.existsByEmail(request.getEmail())
                .flatMap(existsEmail -> existsEmail
                        ? customerRepository.findByEmail(request.getEmail())
                                .flatMap(customer -> {
                                    customer.setFullName(request.getFullName());
                                    return customerRepository.save(customer);
                                })
                        : customerRepository.save(CustomerMapper.mapToCustomer(request)))
                .map(CustomerMapper::mapToCustomerResponse);
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

    @Override
    public Mono<Customer> updateActivationStatus(Integer customerId, EActivationStatus status) {
        return customerRepository.findById(customerId)
                .filter(customer -> !customer.getStatus().equals(status))
                .flatMap(customer -> Mono.just(status).map(customer::status))
                .flatMap(customerRepository::save);
    }
}
