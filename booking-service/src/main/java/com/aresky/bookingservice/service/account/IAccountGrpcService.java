package com.aresky.bookingservice.service.account;

import reactor.core.publisher.Mono;

public interface IAccountGrpcService {
    Mono<Boolean> checkAccountById(Integer accountId);
    Mono<Boolean> checkAccountByEmail(String email);
}
