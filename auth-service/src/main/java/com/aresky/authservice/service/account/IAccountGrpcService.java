package com.aresky.authservice.service.account;

import grpc.account.v2.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

public interface IAccountGrpcService {
    Mono<Boolean> checkAccountByUsername(String username);

    Mono<Boolean> checkAccountByEmail(String email);

    Mono<AccountResponse> getAccountByEmail(String email);

    Mono<AccountResponse> getAccountByUsernameAndPassword(String username, String password);

    Mono<Boolean> createAccount(String username, String email, String password);

    Mono<String> resetPassword(String email);
}
