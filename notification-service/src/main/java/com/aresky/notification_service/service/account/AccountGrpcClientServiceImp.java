package com.aresky.notification_service.service.account;

import com.aresky.notification_service.exception.NotificationException;
import grpc.account.v2.dto.request.CheckAccountByIdRequest;
import grpc.account.v2.dto.response.CheckAccountByIdResponse;
import grpc.account.v2.service.ReactorAccountCheckingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Service
public class AccountGrpcClientServiceImp implements IAccountGrpcClientService {
    public static final String HOST = "account-service";
    public static final Integer PORT = 50082;

    private ManagedChannel channel;

    @PostConstruct
    public void init(){
        this.channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
    }

    @Override
    public Mono<Boolean> checkExistsAccountById(Integer accountId) {
        CheckAccountByIdRequest request = CheckAccountByIdRequest.newBuilder().setAccountId(accountId).build();

        return Mono.justOrEmpty(initCheckingStub())
                .doOnError(this::handleGrpcException)
                .flatMap(stub -> stub.checkAccountById(request))
                .map(CheckAccountByIdResponse::getIsExists);
    }

    private ReactorAccountCheckingServiceGrpc.ReactorAccountCheckingServiceStub initCheckingStub (){
        return ReactorAccountCheckingServiceGrpc.newReactorStub(this.channel)
                .withDeadlineAfter(3000, TimeUnit.MILLISECONDS);
    }

    private void handleGrpcException(Throwable err){
        if(err instanceof UnknownHostException){
            throw new NotificationException("Unable to connection account-service");
        }

        throw new NotificationException(err.getMessage());
    }

    @PreDestroy
    private void shutdownChannel(){
        if(this.channel != null && !this.channel.isShutdown()){
            this.channel.shutdownNow();
        }
    }
}
