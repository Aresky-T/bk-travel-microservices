package com.aresky.reviewservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.aresky.reviewservice.entity.Review;

@Repository
public interface IReviewRepository extends R2dbcRepository<Review, Integer> {

}
