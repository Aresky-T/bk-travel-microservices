package com.aresky.bookingservice.service.tour;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.exception.MessageResponse;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class TourService {

    private final String tourURL = "http://localhost:8083/api/v1/tours";

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
                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> response.bodyToMono(MessageResponse.class)
                        .flatMap(msgRsp -> Mono.error(new BookingException(msgRsp.getMessage()))))
                .bodyToMono(SubTourResponse.class);
    }
}
