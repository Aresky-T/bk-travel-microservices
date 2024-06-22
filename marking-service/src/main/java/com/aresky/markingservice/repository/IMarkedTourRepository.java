package com.aresky.markingservice.repository;

import com.aresky.markingservice.entity.MarkedTour;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IMarkedTourRepository extends R2dbcRepository<MarkedTour, Integer> {
    Mono<Boolean> existsByAccountIdAndSubTourId(Integer accountId, Integer subTourId);
    Mono<MarkedTour> findByAccountIdAndSubTourId(Integer accountId, Integer subTourId);
}
