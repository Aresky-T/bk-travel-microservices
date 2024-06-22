package com.aresky.markingservice.service.marking;

import com.aresky.markingservice.dto.request.MarkedTourRequest;
import reactor.core.publisher.Mono;

public interface IMarkingService {
    Mono<Void> markTour(MarkedTourRequest request);

    Mono<Void> unmarkTour(Integer markedTourId);

    Mono<Boolean> checkMarkedTour(Integer accountId, Integer subTourId);
}
