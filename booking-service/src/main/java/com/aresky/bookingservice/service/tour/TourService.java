package com.aresky.bookingservice.service.tour;

import java.time.Duration;

import com.aresky.bookingservice.exception.MessageResponse;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.exception.BookingException;

import jakarta.annotation.PostConstruct;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class TourService {

    private final Duration requestTimeout = Duration.ofSeconds(5);

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        String tourURL = "http://localhost:8083/api/v1/tours";
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
                .map(HttpEntity::getBody)
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientResponseException) {
                        MessageResponse messageResponse = ((WebClientResponseException) ex)
                                .getResponseBodyAs(MessageResponse.class);
                        assert messageResponse != null;
                        return Mono.error(new BookingException(messageResponse.getMessage()));
                    }
                    return Mono.error(new BookingException(ex.getMessage()));
                });
    }
}
