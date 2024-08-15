package com.aresky.bookingservice.service.tour;

import com.aresky.bookingservice.model.SubTour;
import reactor.core.publisher.Mono;

public interface ITourService {
    Mono<SubTour> getSubTourById(Integer subTourId);
}
