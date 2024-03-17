package com.aresky.accountservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.accountservice.model.Account;

public interface AccountRepository extends R2dbcRepository<Account, Long> {

}
