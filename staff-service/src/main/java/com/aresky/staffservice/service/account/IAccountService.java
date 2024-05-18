package com.aresky.staffservice.service.account;

import grpc.account.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<AccountResponse> checkAccountByEmail(String email);
}
