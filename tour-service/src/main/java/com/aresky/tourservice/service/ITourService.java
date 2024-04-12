package com.aresky.tourservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aresky.tourservice.dto.request.SubTourCreateForm;
import com.aresky.tourservice.dto.request.TourCreateForm;
import com.aresky.tourservice.dto.request.TourFilter;
import com.aresky.tourservice.dto.response.SubTourAdminResponse;
import com.aresky.tourservice.dto.response.SubTourDetails;
import com.aresky.tourservice.dto.response.SubTourResponse;
import com.aresky.tourservice.dto.response.TourDetails;
import com.aresky.tourservice.dto.response.TourResponse;

public interface ITourService {
    void createTour(TourCreateForm form);

    void createSubTour(SubTourCreateForm form);

    void updateTour(Integer tourId, Map<String, Object> fields);

    void updateSubTour(Integer subTourId, Map<String, Object> fields);

    Page<TourResponse> findAllTourResponses(Pageable pageable);

    Page<TourResponse> findAllTourResponses(Pageable pageable, TourFilter tourFilter);

    TourDetails findTourDetailsById(Integer id);

    Page<SubTourResponse> findAllSubTourResponses(Pageable pageable);

    Page<SubTourResponse> findAllSubTourResponses(Pageable pageable, TourFilter tourFilter);

    List<SubTourAdminResponse> findAllSubTourResponses(Integer tourId);

    List<SubTourResponse> findLatestSubTours(Integer count);

    SubTourDetails findSubTourDetailsByTourCode(String tourCode);

    SubTourDetails findSubTourDetailsById(Integer subTourId);

    void deleteTourById(Integer id);

    void deleteSubTourById(Integer id);

    void deleteSubTourByTourCode(String tourCode);

    void deleteAllSubToursByTourId(Integer tourId);

    Boolean existsTourById(Integer id);

    Boolean existsTourByTitle(String title);

    Boolean existsSubTourById(Integer id);

    Boolean existsSubTourByTitle(String title);

    Boolean existsSubTourByTourCode(String tourCode);
}
