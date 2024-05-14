package com.aresky.chatservice.repository;

import com.aresky.chatservice.entity.EActivationStatus;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.chatservice.entity.Staff;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IStaffRepository extends R2dbcRepository<Staff, Integer> {
    Mono<Boolean> existsByAccountId(Integer accountId);
    Mono<Staff> findByAccountId(Integer accountId);
    Flux<Staff> findAllByStatus(EActivationStatus status);
}
