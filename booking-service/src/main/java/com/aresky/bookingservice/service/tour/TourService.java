package com.aresky.bookingservice.service.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.exception.BookingException;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class TourService {

    private final String tourURL = "http://localhost:8083/api/v1/tours";

    @Autowired
    private RestTemplate restTemplate;

    public Mono<SubTourResponse> findSubTour(int subTourId) {
        String url = tourURL.concat("/sub-tour/id").concat("/" + subTourId);

        return Mono.fromCallable(() -> restTemplate.getForEntity(url, SubTourResponse.class))
                .map(res -> {
                    if (!res.getStatusCode().is2xxSuccessful()) {
                        throw new BookingException("Failed connect to tour-service!");
                    }
                    return res.getBody();
                });
    }
}
