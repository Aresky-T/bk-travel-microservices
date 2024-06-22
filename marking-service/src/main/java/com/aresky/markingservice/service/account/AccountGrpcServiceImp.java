package com.aresky.markingservice.service.account;

import com.aresky.markingservice.exception.MarkingException;
import grpc.account.check.ReactorAccountProfileCheckServiceGrpc;
import grpc.account.dto.request.CheckProfileByAccountIdRequest;
import grpc.account.dto.response.CheckProfileByAccountIdResponse;
import grpc.account.fields.AccountIdField;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountGrpcServiceImp implements IAccountService {
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
                .onErrorResume(ex -> Mono.error(new MarkingException(ex.getMessage())));
    }

    private Mono<ReactorAccountProfileCheckServiceGrpc.ReactorAccountProfileCheckServiceStub> initStub() {
        return Mono.justOrEmpty(ReactorAccountProfileCheckServiceGrpc.newReactorStub(channel))
                .switchIfEmpty(Mono.error(new MarkingException("Connect to account-service failed!")));
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
