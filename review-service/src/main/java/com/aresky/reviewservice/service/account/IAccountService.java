package com.aresky.reviewservice.service.account;

import grpc.account.dto.response.ProfileResponse;
import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<Boolean> checkExistsAccountById(Integer accountId);
    Mono<ProfileResponse> getProfileById(Integer accountId);
}
