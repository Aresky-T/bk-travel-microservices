package com.aresky.chatservice.service.account;

import com.aresky.chatservice.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<AccountResponse> getAccountById(int accountId);

    Mono<Boolean> existsAccountById(int accountId);
}
