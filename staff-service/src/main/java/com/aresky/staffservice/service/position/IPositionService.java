package com.aresky.staffservice.service.position;

import com.aresky.staffservice.dto.request.PositionCreateForm;
import com.aresky.staffservice.dto.request.PositionUpdateForm;
import com.aresky.staffservice.dto.response.PositionDetails;
import com.aresky.staffservice.dto.response.PositionResponse;
import com.aresky.staffservice.model.Position;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface IPositionService {
    Mono<List<Position>> getAllPositionsBy(Integer departmentId);

    Mono<List<PositionResponse>> getAllPositionResponses();

    Mono<List<PositionResponse>> getAllPositionResponses(Integer departmentId);

    Mono<PositionDetails> getPositionDetailsBy(Integer positionId);

    Mono<PositionDetails> getPositionDetailsBy(String positionName);

    Mono<Void> createPosition(PositionCreateForm form);

    Mono<PositionResponse> updatePosition(Integer positionId, PositionUpdateForm form);

    Mono<PositionResponse> updatePosition(Integer positionId, Map<String, Object> fields);

    Mono<PositionResponse> updatePosition(Integer positionId, String key, Object value);

    Mono<Void> deletePosition(Integer staffId);
}
