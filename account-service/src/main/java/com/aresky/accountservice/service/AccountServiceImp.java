package com.aresky.accountservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.repository.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImp implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Mono<Boolean> activateAccount(long accountId) {
        return null;
    }

    @Override
    public Mono<Boolean> deleteAccount(long accountId) {
        return null;
    }

    @Override
    public Flux<Account> findAll() {
        return null;
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Account> findByAuthId(long authId) {
        return null;
    }

    @Override
    public Mono<Account> findById(long id) {
        return null;
    }

    @Override
    public Mono<Boolean> lockAccount(long accountId) {
        return null;
    }

    @Override
    public Mono<Account> save(Account account) {
        return null;
    }

    @Override
    public Mono<Boolean> upgradeRoleToEmployee(long accountId) {
        return null;
    }

}
