package com.aresky.authservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.authservice.entity.Auth;

import reactor.core.publisher.Mono;

public interface AuthRepository extends R2dbcRepository<Auth, Integer> {
    Mono<Auth> findByAccountId(int accountId);
    Mono<Boolean> existsByAccountId(int accountId);
}
