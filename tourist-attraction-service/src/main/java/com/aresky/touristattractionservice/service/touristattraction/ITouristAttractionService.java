package com.aresky.touristattractionservice.service.touristattraction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aresky.touristattractionservice.dto.request.FieldRequest;
import com.aresky.touristattractionservice.dto.request.SearchRequest;
import com.aresky.touristattractionservice.dto.request.TouristAttractionCreateForm;
import com.aresky.touristattractionservice.dto.request.TouristAttractionUpdateForm;
import com.aresky.touristattractionservice.dto.response.TouristAttractionDetails;
import com.aresky.touristattractionservice.dto.response.TouristAttractionResponse;

public interface ITouristAttractionService {
    void create(TouristAttractionCreateForm form);

    Page<TouristAttractionResponse> findAll(Pageable pageable);

    List<TouristAttractionResponse> findAll(SearchRequest searchRequest);

    TouristAttractionDetails findById(Integer id);

    TouristAttractionDetails findByName(String name);

    TouristAttractionResponse update(Integer id, TouristAttractionUpdateForm form);

    TouristAttractionResponse update(Integer id, FieldRequest form);

    void delete(Integer id);
}
