package com.aresky.reviewservice.service.account;

import grpc.account.v2.dto.request.CheckAccountByIdRequest;
import grpc.account.v2.dto.request.GetProfileByAccountIdRequest;
import grpc.account.v2.dto.response.CheckAccountByIdResponse;
import grpc.account.v2.dto.response.GetProfileByAccountIdResponse;
import grpc.account.v2.dto.response.ProfileResponse;
import grpc.account.v2.service.ReactorAccountCheckingServiceGrpc;
import grpc.account.v2.service.ReactorAccountGettingServiceGrpc;
import io.grpc.*;
import org.springframework.stereotype.Service;

import com.aresky.reviewservice.exception.ReviewException;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Service
public class AccountGrpcServiceIml implements IAccountService {
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
    public Mono<Boolean> checkExistsAccountById(Integer accountId) {
        CheckAccountByIdRequest request = CheckAccountByIdRequest.newBuilder().setAccountId(accountId).build();
        return initCheckingStub().checkAccountById(request)
                .map(CheckAccountByIdResponse::getIsExists)
                .onErrorResume(this::handleException);
    }

    @Override
    public Mono<ProfileResponse> getProfileById(Integer accountId) {
        GetProfileByAccountIdRequest request = GetProfileByAccountIdRequest.newBuilder().setAccountId(accountId).build();
        return initGettingStub()
                .getProfileByAccountId(request)
                .switchIfEmpty(Mono.empty())
                .map(GetProfileByAccountIdResponse::getProfile)
                .onErrorResume(this::handleException);
    }

    private ReactorAccountCheckingServiceGrpc.ReactorAccountCheckingServiceStub initCheckingStub() {
        return ReactorAccountCheckingServiceGrpc.newReactorStub(channel)
                .withDeadline(Deadline.after(2000, TimeUnit.MILLISECONDS));
    }

    private ReactorAccountGettingServiceGrpc.ReactorAccountGettingServiceStub initGettingStub() {
        return ReactorAccountGettingServiceGrpc.newReactorStub(channel)
                .withDeadline(Deadline.after(2000, TimeUnit.MILLISECONDS));
    }

    private void shutdownChannel() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }

    private <T> Mono<T> handleException (Throwable err) {
        if(err instanceof UnknownHostException){
            return Mono.error(new ReviewException("Unable to resolve host account-service!"));
        }

        if(err instanceof StatusRuntimeException && ((StatusRuntimeException) err).getStatus().getCode().equals(Status.Code.DEADLINE_EXCEEDED)){
            return Mono.error(new ReviewException("Connect to account-service failed!"));
        }

        return Mono.error(new ReviewException(err.getMessage()));
    };
}
