package com.aresky.bookingservice.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aresky.bookingservice.exception.BookingException;

import reactor.core.publisher.Mono;

@Service
public class AccountService {

    private final String accountURL = "http://localhost:8082/api/v1/accounts";

    @Autowired
    private RestTemplate restTemplate;

    public Mono<Boolean> validateAccount(int accountId) {
        String url = accountURL.concat("/validate").concat("?id=" + accountId);
        return Mono.fromCallable(() -> restTemplate.getForEntity(url, Boolean.class))
                .map(res -> {
                    if (!res.getStatusCode().is2xxSuccessful()) {
                        throw new BookingException("bad request to account-service");
                    }

                    return res.getBody();
                })
                .switchIfEmpty(Mono.error(new BookingException("Failed connect to account-service")));
    }
}
