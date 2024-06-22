package com.aresky.markingservice.service.account;

import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<Boolean> checkExistsAccountById(Integer accountId);
}
