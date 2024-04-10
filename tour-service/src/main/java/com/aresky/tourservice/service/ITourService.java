package com.aresky.tourservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aresky.tourservice.dto.request.MyPageable;
import com.aresky.tourservice.dto.request.SubTourCreateForm;
import com.aresky.tourservice.dto.request.TourCreateForm;
import com.aresky.tourservice.dto.request.TourFilter;
import com.aresky.tourservice.dto.request.TourUpdateForm;
import com.aresky.tourservice.dto.response.SubTour2Response;
import com.aresky.tourservice.dto.response.SubTourDetails;
import com.aresky.tourservice.dto.response.SubTourResponse;
import com.aresky.tourservice.dto.response.TourResponse;

import reactor.core.publisher.Mono;

public interface ITourService {
    Mono<Void> createTour(TourCreateForm form);

    Mono<Void> createSubTour(SubTourCreateForm form);

    Mono<Void> updateTourWithSubTour(TourUpdateForm form);

    Mono<Void> updateTourWithSubTour(int tourId, int subTourId, Map<String, Object> fields);

    Mono<Void> updateTourWithSubTour(int tourId, String tourCode, Map<String, Object> fields);

    Mono<Void> updateOnlyTour(int tourId, Map<String, Object> fields);

    Mono<Void> updateOnlySubTour(int subTourId, Map<String, Object> fields);

    Mono<Page<TourResponse>> findAllTours(Pageable pageable);

    Mono<Page<TourResponse>> findAllTours(Pageable pageable, TourFilter tourFilter);

    Mono<List<TourResponse>> findLatestTours(int count);

    Mono<TourResponse> findTourById(int id);

    Mono<Page<SubTourResponse>> findAllSubTours(Pageable pageable);

    Mono<Page<SubTourResponse>> findAllSubTours(MyPageable pageable, TourFilter tourFilter);

    Mono<List<SubTour2Response>> findAllSubTours(int tourId);

    Mono<List<SubTourResponse>> findLatestSubTours(int count);

    Mono<SubTourDetails> findSubTourByTourCode(String tourCode);

    Mono<SubTourDetails> findSubTourById(int subTourId);

    Mono<Void> deleteTourById(int id);

    Mono<Void> deleteSubTourById(int id);

    Mono<Void> deleteSubTourByTourCode(String tourCode);

    Mono<Void> deleteAllSubToursByTourId(int tourId);

    Mono<Boolean> existsTourById(int id);

    Mono<Boolean> existsTourByTitle(String title);

    Mono<Boolean> existsSubTourById(int id);

    Mono<Boolean> existsSubTourByTitle(String title);

    Mono<Boolean> existsSubTourByTourCode(String tourCode);
}
