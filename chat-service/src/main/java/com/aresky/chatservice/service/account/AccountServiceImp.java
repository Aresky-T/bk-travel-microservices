package com.aresky.chatservice.service.account;

import com.aresky.chatservice.dto.response.AccountResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImp implements IAccountService{
    @Override
    public Mono<AccountResponse> getAccountById(int accountId) {
        return Mono.empty();
    }

    @Override
    public Mono<Boolean> existsAccountById(int accountId) {
        return Mono.just(Boolean.TRUE);
    }
}
