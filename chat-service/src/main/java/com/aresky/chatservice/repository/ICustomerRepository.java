package com.aresky.chatservice.repository;

import com.aresky.chatservice.entity.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ICustomerRepository extends R2dbcRepository<Customer, Integer> {
}
