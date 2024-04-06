package com.aresky.staffservice.repository;

import com.aresky.staffservice.model.Position;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IPositionRepository extends R2dbcRepository<Position, Integer> {
    Flux<Position> findAllByDepartmentId(Integer departmentId);

    Mono<Position> findByName(String name);

    Mono<Boolean> existsByName(String name);
}
