package com.aresky.reviewservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.aresky.reviewservice.entity.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IReviewRepository extends R2dbcRepository<Review, Integer> {
    Mono<Boolean> existsByReviewerIdAndSubTourId(Integer reviewerId, Integer subTourId);
    Mono<Review> findByReviewerIdAndSubTourId(Integer reviewerId, Integer subTourId);

    Flux<Review> findByTourId(Integer tourId);
}
