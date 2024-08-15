package com.aresky.reviewservice.service.account;

import io.grpc.*;
import org.springframework.stereotype.Service;

import com.aresky.reviewservice.exception.ReviewException;

import grpc.account.check.ReactorAccountProfileCheckServiceGrpc;
import grpc.account.dto.request.CheckProfileByAccountIdRequest;
import grpc.account.dto.response.CheckProfileByAccountIdResponse;
import grpc.account.dto.response.ProfileResponse;
import grpc.account.fields.AccountIdField;
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
        CheckProfileByAccountIdRequest request = buildCheckProfileByAccountIdRequest(accountId);
        return initStub().checkProfileByAccountId(request)
                .map(CheckProfileByAccountIdResponse::getIsExistsAccountId)
                .onErrorResume(this::handleException);
    }

    @Override
    public Mono<ProfileResponse> getProfileById(Integer accountId) {
        CheckProfileByAccountIdRequest request = buildCheckProfileByAccountIdRequest(accountId);
        return initStub().checkProfileByAccountId(request)
                .filter(CheckProfileByAccountIdResponse::getIsExistsAccountId)
                .switchIfEmpty(Mono.empty())
                .map(CheckProfileByAccountIdResponse::getProfile)
                .onErrorResume(this::handleException);
    }

    private ReactorAccountProfileCheckServiceGrpc.ReactorAccountProfileCheckServiceStub initStub() {
        return ReactorAccountProfileCheckServiceGrpc.newReactorStub(channel)
                .withDeadline(Deadline.after(2000, TimeUnit.MILLISECONDS));
    }

    private CheckProfileByAccountIdRequest buildCheckProfileByAccountIdRequest(Integer accountId) {
        AccountIdField accountIdField = AccountIdField.newBuilder().setId(accountId).build();
        return CheckProfileByAccountIdRequest.newBuilder()
                .setAccountId(accountIdField).build();
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
