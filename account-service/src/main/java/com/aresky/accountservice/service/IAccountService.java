package com.aresky.accountservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aresky.accountservice.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<Account> findById(long id);

    Mono<Account> findByAuthId(long authId);

    Mono<Account> save(Account account);

    Flux<Account> findAll();

    Page<Account> findAll(Pageable pageable);

    Mono<Boolean> upgradeRoleToEmployee(long accountId);

    Mono<Boolean> lockAccount(long accountId);

    Mono<Boolean> activateAccount(long accountId);

    Mono<Boolean> deleteAccount(long accountId);
}
