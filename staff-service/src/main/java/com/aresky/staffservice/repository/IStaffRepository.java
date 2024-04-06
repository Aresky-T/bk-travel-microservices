package com.aresky.staffservice.repository;

import com.aresky.staffservice.model.Staff;

import reactor.core.publisher.Flux;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IStaffRepository extends R2dbcRepository<Staff, Integer> {
    Flux<Staff> findAllByDepartmentId(Integer departmentId);

    Flux<Staff> findAllByPositionId(Integer positionId);
}
