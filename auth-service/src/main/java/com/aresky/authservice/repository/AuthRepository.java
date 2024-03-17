package com.aresky.authservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.authservice.entity.Auth;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthRepository extends R2dbcRepository<Auth, Long> {

    Flux<Auth> findAllBy(Pageable pageable);

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByUsername(String username);

    Mono<Auth> findByUsername(String username);

    Mono<Auth> findByEmail(String email);
}
