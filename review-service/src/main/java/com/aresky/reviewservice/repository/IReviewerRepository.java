package com.aresky.reviewservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.aresky.reviewservice.entity.Reviewer;
import reactor.core.publisher.Mono;

@Repository
public interface IReviewerRepository extends R2dbcRepository<Reviewer, Integer> {
    Mono<Boolean> existsByAccountId(Integer accountId);
    Mono<Reviewer> findByAccountId(Integer accountId);
}
