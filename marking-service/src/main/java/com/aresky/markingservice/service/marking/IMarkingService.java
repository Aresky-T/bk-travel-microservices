package com.aresky.markingservice.service.marking;

import com.aresky.markingservice.dto.request.MarkedTourRequest;
import com.aresky.markingservice.dto.response.MarkedTourResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMarkingService {
    Mono<Void> markTour(MarkedTourRequest request);

    Mono<Void> unmarkTour(Integer markedTourId);

    Mono<Boolean> checkMarkedTour(Integer accountId, Integer subTourId);

    Mono<Void> unmarkTour(Integer accountId, Integer subTourId);

    Mono<List<MarkedTourResponse>> getAllMarkedTourResponses(Integer accountId);
}
