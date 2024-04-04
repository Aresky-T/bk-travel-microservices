package com.aresky.authservice.service.account;

import com.aresky.authservice.dto.AccountResponse;
import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.exception.AuthException;
import com.aresky.authservice.exception.MessageResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImp implements IAccountService{
    private final String accountServiceURI = "http://account-service:8082/api/v1/accounts";

    private WebClient webClient;

    @PostConstruct
    private void initWebClient(){
        this.webClient = WebClient.builder().baseUrl(accountServiceURI).build();
    }

    @Override
    public Mono<AccountResponse> findAccountById(int accountId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/find/id")
                        .queryParam("id", accountId).build())
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> response.bodyToMono(MessageResponse.class)
                            .flatMap(messageResponse -> Mono.error(new AuthException(messageResponse.getMessage())))
                )
                .bodyToMono(AccountResponse.class);
    }

    @Override
    public Mono<AccountResponse> findByUsernameAndPassword(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/find/username-and-password")
                .queryParam("username", "{username}")
                .queryParam("password", "{password}")
                .build(username, password))
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> response.bodyToMono(MessageResponse.class)
                        .flatMap(messageResponse -> Mono.error(new AuthException(messageResponse.getMessage())))
                )
                .bodyToMono(AccountResponse.class);
    }

    @Override
    public Mono<String> onSignupAccount(SignupForm form) {
        return webClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(form), SignupForm.class)
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, response ->
                        response.bodyToMono(MessageResponse.class)
                                .flatMap(messageResponse -> Mono.error(new AuthException(messageResponse.getMessage())))
                ).bodyToMono(Void.class)
                .thenReturn("success");
    }
}
