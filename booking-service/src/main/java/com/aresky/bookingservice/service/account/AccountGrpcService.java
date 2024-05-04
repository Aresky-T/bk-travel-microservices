package com.aresky.bookingservice.service.account;

import grpc.account.AccountIdRequest;
import grpc.account.AccountResponse;
import grpc.account.ExistAccountResponse;
import grpc.account.ReactorAccountServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AccountGrpcService {

    private ManagedChannel channel;
    private ReactorAccountServiceGrpc.ReactorAccountServiceStub stub;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress("account-service", 50082).usePlaintext().build();
        stub = ReactorAccountServiceGrpc.newReactorStub(channel);
    }

    @PreDestroy
    public void stop() {
        if (channel != null) {
            channel.shutdownNow();
        }
    }

    public Mono<Boolean> validateAccount(int accountId) {
        AccountIdRequest request = AccountIdRequest.newBuilder().setId(accountId).build();
        return stub.existAccountById(request).map(ExistAccountResponse::getValue).doOnError(err -> {
            if (err instanceof StatusRuntimeException) {
                log.error("StatusRuntimeException: {}", err.getMessage());
            }
        });
    }

    public Mono<AccountResponse> getAccountById(int accountId) {
        AccountIdRequest request = AccountIdRequest.newBuilder().setId(accountId).build();
        return stub.getAccountById(request).doOnError(err -> {
            if (err instanceof StatusRuntimeException) {
                log.error("StatusRuntimeException: {}", err.getMessage());
            }
        });
    }
}
