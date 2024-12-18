package com.aresky.markingservice.service.account;

import com.aresky.markingservice.exception.MarkingException;
import grpc.account.v2.dto.request.CheckAccountByIdRequest;
import grpc.account.v2.dto.response.CheckAccountByIdResponse;
import grpc.account.v2.service.ReactorAccountCheckingServiceGrpc;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

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
        CheckAccountByIdRequest request = CheckAccountByIdRequest.newBuilder().setAccountId(accountId).build();
        return initStub()
                .checkAccountById(request)
                .map(CheckAccountByIdResponse::getIsExists)
                .onErrorResume(ex -> Mono.error(new MarkingException(ex.getMessage())));
    }

    private ReactorAccountCheckingServiceGrpc.ReactorAccountCheckingServiceStub initStub() {
        return ReactorAccountCheckingServiceGrpc.newReactorStub(channel)
                .withDeadline(Deadline.after(3000, TimeUnit.MILLISECONDS));
    }

    private void shutdownChannel() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }
}
