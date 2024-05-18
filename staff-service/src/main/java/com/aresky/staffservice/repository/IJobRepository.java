package com.aresky.staffservice.repository;

import com.aresky.staffservice.model.EJobStatus;
import com.aresky.staffservice.model.Job;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IJobRepository extends R2dbcRepository<Job, Integer> {
    Mono<Job> findByStaffId(Integer staffId);

    Flux<Job> findByPositionId(Integer positionId);

    Flux<Job> findByDepartmentId(Integer departmentId);

    Mono<Boolean> existsByStaffIdAndStatus(Integer staffId, EJobStatus status);
}
