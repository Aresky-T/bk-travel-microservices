package com.aresky.reviewservice.service.account;

import org.springframework.stereotype.Service;

import com.aresky.reviewservice.exception.ReviewException;

import grpc.account.check.ReactorAccountProfileCheckServiceGrpc;
import grpc.account.dto.request.CheckProfileByAccountIdRequest;
import grpc.account.dto.response.CheckProfileByAccountIdResponse;
import grpc.account.dto.response.ProfileResponse;
import grpc.account.fields.AccountIdField;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import reactor.core.publisher.Mono;

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
        return initStub()
                .flatMap(stub -> stub.checkProfileByAccountId(request))
                .map(CheckProfileByAccountIdResponse::getIsExistsAccountId)
                .onErrorResume(ex -> Mono.error(new ReviewException(ex.getMessage())));
    }

    @Override
    public Mono<ProfileResponse> getProfileById(Integer accountId) {
        CheckProfileByAccountIdRequest request = buildCheckProfileByAccountIdRequest(accountId);
        return initStub()
                .flatMap(stub -> stub.checkProfileByAccountId(request))
                .filter(CheckProfileByAccountIdResponse::getIsExistsAccountId)
                .switchIfEmpty(Mono.empty())
                .map(CheckProfileByAccountIdResponse::getProfile)
                .onErrorResume(ex -> Mono.error(new ReviewException(ex.getMessage())));
    }

    private Mono<ReactorAccountProfileCheckServiceGrpc.ReactorAccountProfileCheckServiceStub> initStub() {
        return Mono.justOrEmpty(ReactorAccountProfileCheckServiceGrpc.newReactorStub(channel))
                .switchIfEmpty(Mono.error(new ReviewException("Connect to account-service failed!")));
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
}
