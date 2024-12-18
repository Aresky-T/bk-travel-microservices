package com.aresky.staffservice.service.account;

import grpc.account.v2.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<AccountResponse> checkAccountByEmail(String email);
}
