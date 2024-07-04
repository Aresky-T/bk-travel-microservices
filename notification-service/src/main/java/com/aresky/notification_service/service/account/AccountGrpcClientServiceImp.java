package com.aresky.notification_service.service.account;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountGrpcClientServiceImp implements IAccountGrpcClientService {
    @Override
    public Mono<Boolean> checkExistsAccountById(Integer accountId) {
        return Mono.just(true);
    }
}
