package com.aresky.staffservice.service.account;

import com.aresky.staffservice.exception.StaffException;
import grpc.account.v2.dto.request.GetAccountByEmailRequest;
import grpc.account.v2.dto.response.AccountResponse;
import grpc.account.v2.dto.response.GetAccountByEmailResponse;
import grpc.account.v2.service.ReactorAccountCheckingServiceGrpc;
import grpc.account.v2.service.ReactorAccountGettingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImp implements IAccountService {

    private static final String HOST = "account-service";
    private static final Integer POST = 50082;

    private ManagedChannel channel;

    @PostConstruct
    public void init(){
        channel = ManagedChannelBuilder.forAddress(HOST, POST).usePlaintext().build();
    }

    @PreDestroy
    public void destroy(){
        shutdownChannel();
    }

    @Override
    public Mono<AccountResponse> checkAccountByEmail(String email) {
        GetAccountByEmailRequest request = GetAccountByEmailRequest
                .newBuilder()
                .setEmail(email)
                .build();

        return initGettingStub()
                .getAccountByEmail(request)
                .filter(GetAccountByEmailResponse::hasAccount)
                .switchIfEmpty(Mono.empty())
                .map(GetAccountByEmailResponse::getAccount)
                .onErrorResume(err -> {
                    if(err instanceof UnknownHostException || err instanceof StatusRuntimeException){
                        return Mono.error(new StaffException("Unable to connect to account-service!"));
                    }

                    return Mono.error(new StaffException(err.getMessage()));
                });
    }

    private ReactorAccountCheckingServiceGrpc.ReactorAccountCheckingServiceStub initCheckingStub(){
        return ReactorAccountCheckingServiceGrpc
                .newReactorStub(this.channel)
                .withDeadlineAfter(3000, TimeUnit.MILLISECONDS);
    }

    private ReactorAccountGettingServiceGrpc.ReactorAccountGettingServiceStub initGettingStub(){
        return ReactorAccountGettingServiceGrpc
                .newReactorStub(this.channel)
                .withDeadlineAfter(3000, TimeUnit.MILLISECONDS);
    }

    private void shutdownChannel(){
        if(this.channel != null && !this.channel.isShutdown()){
            this.channel.shutdownNow();
        }
    }
}
