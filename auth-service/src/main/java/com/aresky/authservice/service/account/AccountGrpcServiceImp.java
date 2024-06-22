package com.aresky.authservice.service.account;

import org.springframework.stereotype.Service;

import com.aresky.authservice.exception.AuthException;

import grpc.account.v2.dto.request.CheckAccountByEmailRequest;
import grpc.account.v2.dto.request.CheckAccountByUsernameRequest;
import grpc.account.v2.dto.request.CreateAccountRequest;
import grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest;
import grpc.account.v2.dto.request.ResetPasswordRequest;
import grpc.account.v2.dto.response.AccountResponse;
import grpc.account.v2.dto.response.CheckAccountByEmailResponse;
import grpc.account.v2.dto.response.CheckAccountByUsernameResponse;
import grpc.account.v2.dto.response.CreateAccountResponse;
import grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse;
import grpc.account.v2.dto.response.ResetPasswordResponse;
import grpc.account.v2.service.ReactorAccountCheckingServiceGrpc;
import grpc.account.v2.service.ReactorAccountCreatingServiceGrpc;
import grpc.account.v2.service.ReactorAccountGettingServiceGrpc;
import grpc.account.v2.service.ReactorAccountUpdatingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import reactor.core.publisher.Mono;

@Service
public class AccountGrpcServiceImp implements IAccountGrpcService {
    private static final String HOST = "account-service";
    private static final Integer POST = 50082;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        this.channel = ManagedChannelBuilder.forAddress(HOST, POST).usePlaintext().build();
    }

    @PreDestroy
    public void destroy() {
        this.shutdownChannel();
    }

    @Override
    public Mono<Boolean> checkAccountByUsername(String username) {
        Mono<CheckAccountByUsernameRequest> request = Mono
                .just(CheckAccountByUsernameRequest.newBuilder().setUsername(username).build());

        return Mono.justOrEmpty(initCheckingStub())
                .switchIfEmpty(Mono.error(new AuthException("Connect to account-service failed!")))
                .flatMap(stub -> stub.checkAccountByUsername(request))
                .map(CheckAccountByUsernameResponse::getIsExists)
                .onErrorResume(err -> Mono.error(new AuthException(err.getMessage())));
    }

    @Override
    public Mono<Boolean> checkAccountByEmail(String email) {
        Mono<CheckAccountByEmailRequest> request = Mono
                .just(CheckAccountByEmailRequest.newBuilder().setEmail(email).build());

        return Mono.justOrEmpty(initCheckingStub())
                .switchIfEmpty(Mono.error(new AuthException("Connect to account-service failed!")))
                .flatMap(stub -> stub.checkAccountByEmail(request))
                .map(CheckAccountByEmailResponse::getIsExists)
                .onErrorResume(err -> Mono.error(new AuthException(err.getMessage())));
    }

    @Override
    public Mono<AccountResponse> getAccountByUsernameAndPassword(String username, String password) {
        Mono<GetAccountByUsernameAndPasswordRequest> request = Mono
                .just(GetAccountByUsernameAndPasswordRequest.newBuilder().setUsername(username).setPassword(password)
                        .build());

        return Mono.justOrEmpty(initGettingStub())
                .switchIfEmpty(Mono.error(new AuthException("Connect to account-service failed!")))
                .flatMap(stub -> stub.getAccountByUsernameAndPassword(request))
                .filter(GetAccountByUsernameAndPasswordResponse::hasAccount)
                .switchIfEmpty(Mono.empty())
                .map(GetAccountByUsernameAndPasswordResponse::getAccount)
                .onErrorResume(err -> Mono.error(new AuthException(err.getMessage())));
    }

    @Override
    public Mono<Boolean> createAccount(String username, String email, String password) {
        Mono<CreateAccountRequest> request = Mono.just(
                CreateAccountRequest.newBuilder()
                        .setUsername(username)
                        .setEmail(email)
                        .setPassword(password)
                        .build());

        return Mono.justOrEmpty(initCreatingStub())
                .switchIfEmpty(Mono.error(new AuthException("Connect to account-service failed!")))
                .flatMap(stub -> stub.createAccount(request))
                .map(CreateAccountResponse::getIsSuccess)
                .onErrorResume(err -> Mono.error(new AuthException(err.getMessage())));
    }

    @Override
    public Mono<String> resetPassword(String email) {
        Mono<ResetPasswordRequest> request = Mono.just(ResetPasswordRequest.newBuilder().setEmail(email).build());

        return Mono.justOrEmpty(initUpdatingStub())
                .switchIfEmpty(Mono.error(new AuthException("Connect to account-service failed!")))
                .flatMap(stub -> stub.resetPassword(request))
                .map(ResetPasswordResponse::getNewPassword)
                .onErrorResume(err -> Mono.error(new AuthException(err.getMessage())));
    }

    public ReactorAccountCheckingServiceGrpc.ReactorAccountCheckingServiceStub initCheckingStub() {
        return ReactorAccountCheckingServiceGrpc.newReactorStub(channel);
    }

    public ReactorAccountGettingServiceGrpc.ReactorAccountGettingServiceStub initGettingStub() {
        return ReactorAccountGettingServiceGrpc.newReactorStub(channel);
    }

    public ReactorAccountCreatingServiceGrpc.ReactorAccountCreatingServiceStub initCreatingStub() {
        return ReactorAccountCreatingServiceGrpc.newReactorStub(channel);
    }

    public ReactorAccountUpdatingServiceGrpc.ReactorAccountUpdatingServiceStub initUpdatingStub() {
        return ReactorAccountUpdatingServiceGrpc.newReactorStub(channel);
    }

    private void shutdownChannel() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }
}
