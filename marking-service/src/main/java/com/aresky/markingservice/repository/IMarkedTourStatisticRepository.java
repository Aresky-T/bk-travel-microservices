package com.aresky.markingservice.repository;

import com.aresky.markingservice.entity.MarkedTourStatistic;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarkedTourStatisticRepository extends R2dbcRepository<MarkedTourStatistic, Integer> {
}
