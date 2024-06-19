package com.aresky.reviewservice.service.tour;

import grpc.tour.dto.response.SubTourResponse;
import reactor.core.publisher.Mono;

public interface ITourService {
    Mono<Boolean> checkExistsTourById(Integer tourId);
    Mono<Boolean> checkExistsSubTourById(Integer subTourId);
    Mono<SubTourResponse> getSubTourById(Integer subTourId);
}
