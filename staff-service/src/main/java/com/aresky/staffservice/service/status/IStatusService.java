package com.aresky.staffservice.service.status;

import com.aresky.staffservice.dto.request.StatusCreateForm;
import com.aresky.staffservice.dto.response.StatusResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IStatusService {
    Mono<List<StatusResponse>> getAllStatus();
    Mono<StatusResponse> getDetailsStatus(Integer statusId);
    Mono<Void> createStatus(StatusCreateForm form);
    Mono<Void> updateStatus(Integer id, String key, Object value);
    Mono<Void> deleteStatus(Integer statusId);
}
