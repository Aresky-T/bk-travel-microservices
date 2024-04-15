package com.aresky.bookingservice.service.account;

import java.time.Duration;

import com.aresky.bookingservice.exception.MessageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aresky.bookingservice.exception.BookingException;

import jakarta.annotation.PostConstruct;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    private final Duration requestTimeout = Duration.ofSeconds(5);

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        String accountURL = "http://account-service:8082/api/v1/accounts";
        this.webClient = WebClient.builder().baseUrl(accountURL).build();
    }

    public Mono<Boolean> validateAccount(int accountId) {
        String endpoint = "/validate";
        return webClient
                .get()
                .uri(uri -> uri.path(endpoint).queryParam("id", accountId).build())
                .retrieve()
                .toEntity(Boolean.class)
                .timeout(requestTimeout)
                .filter(response -> response.getStatusCode().is2xxSuccessful()
                        && Boolean.TRUE.equals(response.getBody()))
                .switchIfEmpty(Mono.error(new BookingException("Invalid accountId!")))
                .flatMap(response -> Mono.just(true))
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
