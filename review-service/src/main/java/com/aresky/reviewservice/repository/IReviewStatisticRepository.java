package com.aresky.reviewservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.aresky.reviewservice.entity.ReviewStatistic;

@Repository
public interface IReviewStatisticRepository extends R2dbcRepository<ReviewStatistic, Integer> {

}
