package com.aresky.staffservice.repository;

import com.aresky.staffservice.model.Staff;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;

public interface IStaffRepository extends R2dbcRepository<Staff, Integer> {
    Flux<Staff> findAllBy(Pageable pageable);

    Flux<Staff> findAllByDepartmentId(Integer departmentId);

    Flux<Staff> findAllByPositionId(Integer positionId);

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByPhone(String phone);

    Mono<Boolean> existsByAccountId(Integer accountId);

    Mono<Boolean> existsByContractUrl(String contractUrl);

    Mono<Staff> findByAccountId(Integer accountId);

    Mono<Staff> findByEmail(String email);

    Mono<Staff> findByPhone(String phone);

    Mono<Staff> findByContractUrl(String contractUrl);

    @Query("SELECT CASE WHEN COUNT(*) > 0 FROM staff WHERE :fieldName = :value")
    Mono<Boolean> existsByField(@Param("fieldName") String fieldName, @Param("value") Object value);
}
