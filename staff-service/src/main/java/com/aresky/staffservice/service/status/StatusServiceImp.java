package com.aresky.staffservice.service.status;

import com.aresky.staffservice.dto.request.StatusCreateForm;
import com.aresky.staffservice.dto.response.StatusResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StatusServiceImp implements IStatusService{
    @Override
    public Mono<List<StatusResponse>> getAllStatus() {
        return null;
    }

    @Override
    public Mono<StatusResponse> getDetailsStatus(Integer statusId) {
        return null;
    }

    @Override
    public Mono<Void> createStatus(StatusCreateForm form) {
        return null;
    }

    @Override
    public Mono<Void> updateStatus(Integer id, String key, Object value) {
        return null;
    }

    @Override
    public Mono<Void> deleteStatus(Integer statusId) {
        return null;
    }
}
