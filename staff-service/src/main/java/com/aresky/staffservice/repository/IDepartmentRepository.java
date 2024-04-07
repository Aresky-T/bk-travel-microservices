package com.aresky.staffservice.repository;

import com.aresky.staffservice.model.Department;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IDepartmentRepository extends R2dbcRepository<Department, Integer> {
    Mono<Department> findByName(String name);

    Mono<Boolean> existsByName(String name);
}
