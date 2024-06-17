package com.aresky.mailservice.repository;

import com.aresky.mailservice.entity.Staff;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface IStaffRepository extends R2dbcRepository<Staff, Integer> {
    Mono<Boolean> existsByEmail(String email);

    Mono<Staff> findByEmail(String email);
}
