package com.aresky.bookingservice.service.tour;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.exception.BookingException;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class TourService {

    private final String tourURL = "http://tour-service:8083/api/v1/tours";
    private final Duration requestTimeout = Duration.ofSeconds(5);

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        this.webClient = WebClient.builder().baseUrl(tourURL).build();
    }

    public Mono<SubTourResponse> findSubTour(int subTourId) {
        String endpoint = "/sub-tour/id/{id}";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint).build(subTourId))
                .retrieve()
                .toEntity(SubTourResponse.class)
                .timeout(requestTimeout)
                .filter(response -> response.getStatusCode().is2xxSuccessful())
                .map(response -> response.getBody())
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientException) {
                        throw new BookingException("Invalid subTourId");
                    }

                    return Mono.error(new BookingException(ex.getMessage()));
                });
    }
}
