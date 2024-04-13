package com.aresky.bookingservice.service.account;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.aresky.bookingservice.exception.BookingException;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    private final String accountURL = "http://account-service:8082/api/v1/accounts";
    private final Duration requestTimeout = Duration.ofSeconds(5);

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
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
                .flatMap(response -> Mono.just(response.getBody()))
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientException) {
                        return Mono.error(new BookingException("Unable to request validate account!"));
                    }

                    return Mono.error(new BookingException(ex.getMessage()));
                });
    }
}
