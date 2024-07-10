package com.aresky.bookingservice.service.account;

import grpc.account.v2.dto.request.CheckAccountByEmailRequest;
import grpc.account.v2.dto.request.CheckAccountByIdRequest;
import grpc.account.v2.dto.response.CheckAccountByEmailResponse;
import grpc.account.v2.dto.response.CheckAccountByIdResponse;
import grpc.account.v2.service.ReactorAccountCheckingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Service
public class ReactorAccountServiceImp implements IAccountGrpcService {
    public static  final String HOST = "account-service";
    public static final Integer PORT = 50082;
    public static final long TIMEOUT = 5000;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        channel = buildChannel();
    }

    @PreDestroy
    public void stop() {
        stopChannel();
    }

    @Override
    public Mono<Boolean> checkAccountById(Integer accountId) {
        return checkStub().checkAccountById(
                CheckAccountByIdRequest.newBuilder().setAccountId(accountId).build()
        ).map(CheckAccountByIdResponse::getIsExists);
    }

    @Override
    public Mono<Boolean> checkAccountByEmail(String email) {
        return checkStub().checkAccountByEmail(
                CheckAccountByEmailRequest.newBuilder().setEmail(email).build()
        ).map(CheckAccountByEmailResponse::getIsExists);
    }

    private ManagedChannel buildChannel() {
        return ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
    }

    private ReactorAccountCheckingServiceGrpc.ReactorAccountCheckingServiceStub checkStub(){
        return ReactorAccountCheckingServiceGrpc.newReactorStub(channel).withDeadlineAfter(TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private void stopChannel(){
        if (channel != null) {
            channel.shutdownNow();
        }
    }
}
