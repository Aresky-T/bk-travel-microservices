package com.aresky.accountservice.delivery.grpc.v2;

import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.repository.AccountRepository;
import com.aresky.accountservice.repository.ProfileRepository;
import grpc.account.v2.dto.request.*;
import grpc.account.v2.dto.response.*;
import grpc.account.v2.service.ReactorAccountCheckingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@GrpcService
public class ReactorAccountCheckingServiceProvider extends ReactorAccountCheckingServiceGrpc.AccountCheckingServiceImplBase {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Mono<CheckAccountByIdResponse> checkAccountById(Mono<CheckAccountByIdRequest> request) {
        return request.map(CheckAccountByIdRequest::getAccountId)
                .flatMap(accountRepository::existsById)
                .map(existsAccount -> CheckAccountByIdResponse.newBuilder()
                        .setIsExists(existsAccount).build());
    }

    @Override
    public Mono<CheckAccountByEmailResponse> checkAccountByEmail(Mono<CheckAccountByEmailRequest> request) {
        return request.map(CheckAccountByEmailRequest::getEmail)
                .flatMap(accountRepository::existsByEmail)
                .map(existsAccount -> CheckAccountByEmailResponse.newBuilder()
                        .setIsExists(existsAccount).build());
    }

    @Override
    public Mono<CheckAccountByUsernameResponse> checkAccountByUsername(Mono<CheckAccountByUsernameRequest> request) {
        return request.map(CheckAccountByUsernameRequest::getUsername)
                .flatMap(accountRepository::existsByUsername)
                .map(existsAccount -> CheckAccountByUsernameResponse.newBuilder()
                        .setIsExists(existsAccount).build());
    }

    @Override
    public Mono<CheckProfileByAccountIdResponse> checkProfileByAccountId(Mono<CheckProfileByAccountIdRequest> request) {
        return request.map(CheckProfileByAccountIdRequest::getAccountId)
                .flatMap(profileRepository::existsByAccountId)
                .map(existsProfile -> CheckProfileByAccountIdResponse.newBuilder()
                        .setIsExists(existsProfile).build());
    }

    @Override
    public Mono<CheckProfileByEmailResponse> checkProfileByEmail(Mono<CheckProfileByEmailRequest> request) {
        return request.map(CheckProfileByEmailRequest::getEmail)
                .flatMap(email -> accountRepository.existsByEmail(email)
                        .flatMap(existsAccount -> existsAccount
                                ? accountRepository.findByEmail(email)
                                .map(Account::getId)
                                .flatMap(profileRepository::existsByAccountId)
                                .map(this::toCheckProfileByEmailResponse)
                                :
                                Mono.just(this.toCheckProfileByEmailResponse(false))
                        ));
    }

    private CheckProfileByEmailResponse toCheckProfileByEmailResponse(Boolean value){
        return CheckProfileByEmailResponse.newBuilder().setIsExists(value).build();
    }
}
