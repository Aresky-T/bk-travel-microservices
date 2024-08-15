package com.aresky.accountservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.accountservice.model.Profile;

import reactor.core.publisher.Mono;

public interface ProfileRepository extends R2dbcRepository<Profile, Integer> {
    Mono<Profile> findByAccountId(int accountId);

    Mono<Boolean> existsByAccountId(int accountId);

    Mono<Void> deleteByAccountId(int accountId);
}
