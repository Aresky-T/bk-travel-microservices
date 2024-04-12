package com.aresky.bookingservice.service.account;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    private final String accountURL = "http://localhost:8082/api/v1/accounts";

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
                .bodyToMono(Boolean.class);
    }
}
