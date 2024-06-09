package com.aresky.chatservice.service.account;

import grpc.account.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<AccountResponse> getAccountById(Integer accountId);

    Mono<Boolean> existsAccountById(Integer accountId);

    Mono<AccountResponse> getAccountByEmail(String email);

    Mono<Boolean> existsAccountByEmail(String email);
}
