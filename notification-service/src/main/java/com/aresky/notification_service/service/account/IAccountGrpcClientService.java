package com.aresky.notification_service.service.account;

import reactor.core.publisher.Mono;

public interface IAccountGrpcClientService {
    Mono<Boolean> checkExistsAccountById(Integer accountId);
}
