package com.aresky.tourservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.aresky.tourservice.model.SubTour;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubTourRepository extends R2dbcRepository<SubTour, Integer> {
    Mono<SubTour> findByTourCode(String tourCode);

    Mono<SubTour> findByTitle(String title);

    Flux<SubTour> findAllBy(Pageable pageable);

    Flux<SubTour> findAllByTourId(Integer tourId);

    Flux<SubTour> findAllByTitleLike(String title);

    @Query("SELECT COUNT(*) AS count FROM sub_tour s GROUP BY tour_id HAVING s.tour_id = :tourId")
    Mono<Integer> findCountSubTourByTourId(Integer tourId);

    Mono<Boolean> existsByTitle(String title);

    Mono<Boolean> existsByTourId(Integer tourId);

    Mono<Boolean> existsByTourCode(String tourCode);

    Mono<Void> deleteAllByTourId(Integer tourId);

    Mono<Void> deleteByTourCode(String tourCode);
}
