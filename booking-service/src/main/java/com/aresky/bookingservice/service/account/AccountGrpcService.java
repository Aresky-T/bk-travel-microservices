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

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AccountGrpcService {
    private static final String HOST = "account-service";
    private static final int PORT = 50082;
    private static final long TIMEOUT = 5000;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        channel = buildChannel();
    }

    @PreDestroy
    public void stop() {
        stopChannel();
    }

    public Mono<Boolean> validateAccount(int accountId) {
        System.out.println("account grpc client: validateAccount");
        ReactorAccountServiceGrpc.ReactorAccountServiceStub stub = buildStub();
        AccountIdRequest request = AccountIdRequest.newBuilder().setId(accountId).build();
        return stub.existAccountById(request).map(ExistAccountResponse::getValue)
                .doOnError(AccountGrpcService::handleException);
    }

    public Mono<AccountResponse> getAccountById(int accountId) {
        System.out.println("account grpc client: getAccountById");
        ReactorAccountServiceGrpc.ReactorAccountServiceStub stub = buildStub();
        AccountIdRequest request = AccountIdRequest.newBuilder().setId(accountId).build();
        return stub.getAccountById(request).doOnError(AccountGrpcService::handleException);
    }

    private static void handleException(Throwable t){
        log.error("Exception: {}", t.getMessage());
        if (t instanceof StatusRuntimeException) {
            log.error("StatusRuntimeException: {}", t.getMessage());
        }
    }

    private ManagedChannel buildChannel() {
        return ManagedChannelBuilder.forAddress(AccountGrpcService.HOST, AccountGrpcService.PORT).usePlaintext().build();
    }

    private ReactorAccountServiceGrpc.ReactorAccountServiceStub buildStub(){
        return ReactorAccountServiceGrpc.newReactorStub(channel).withDeadlineAfter(TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private void stopChannel(){
        if (channel != null) {
            channel.shutdownNow();
        }
    }
}
