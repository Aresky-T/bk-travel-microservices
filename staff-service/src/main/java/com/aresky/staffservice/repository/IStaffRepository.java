package com.aresky.staffservice.repository;

import com.aresky.staffservice.model.Staff;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IStaffRepository extends R2dbcRepository<Staff, Integer> {
    Flux<Staff> findAllBy(Pageable pageable);

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByPhone(String phone);

    Mono<Boolean> existsByAccountId(Integer accountId);

    Mono<Boolean> existsByContractUrl(String contractUrl);

    Mono<Staff> findByAccountId(Integer accountId);

    Mono<Staff> findByEmail(String email);

    Mono<Staff> findByPhone(String phone);
}
