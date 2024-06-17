package com.aresky.mailservice.service.customer;

import com.aresky.mailservice.entity.Customer;
import com.aresky.mailservice.exception.ExceptionMessage;
import com.aresky.mailservice.exception.MailException;
import com.aresky.mailservice.mappers.http.CustomerMapper;
import com.aresky.mailservice.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImp implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Mono<Customer> register(String email, String fullName) {
        return existsByEmail(email)
                .flatMap(existsEmail -> {
                    if(existsEmail){
                        return customerRepository.findByEmail(email)
                                .flatMap(customer -> {
                                    customer.setFullName(fullName);
                                    return customerRepository.save(customer);
                                });
                    }

                    return customerRepository.save(CustomerMapper.toCustomer(email, fullName));
                });
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
        return customerRepository.findById(customerId)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_CUSTOMER_ID)));
    }

    @Override
    public Mono<Customer> getByEmail(String email) {
        return customerRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_CUSTOMER_EMAIL)));
    }

    @Override
    public Mono<Void> deleteById(Integer customerId) {
        return existsById(customerId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_CUSTOMER_ID)))
                .then(customerRepository.deleteById(customerId))
                .then();
    }
}
