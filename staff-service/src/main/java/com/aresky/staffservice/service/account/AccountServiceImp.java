package com.aresky.staffservice.service.account;

import com.aresky.staffservice.exception.StaffException;
import grpc.account.check.ReactorAccountServiceCheckGrpc;
import grpc.account.dto.request.CheckAccountByEmailRequest;
import grpc.account.dto.response.AccountResponse;
import grpc.account.dto.response.CheckAccountByEmailResponse;
import grpc.account.fields.AccountEmailField;
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
        AccountEmailField accountEmailField = AccountEmailField
                .newBuilder()
                .setEmail(email)
                .build();

        CheckAccountByEmailRequest request = CheckAccountByEmailRequest
                .newBuilder()
                .setAccountEmail(accountEmailField)
                .build();

        return initStub()
                .checkAccountByEmail(request)
                .filter(CheckAccountByEmailResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(CheckAccountByEmailResponse::getAccount)
                .onErrorResume(err -> {
                    if(err instanceof UnknownHostException || err instanceof StatusRuntimeException){
                        return Mono.error(new StaffException("Unable to connect to account-service!"));
                    }

                    return Mono.error(new StaffException(err.getMessage()));
                });
    }

    private ReactorAccountServiceCheckGrpc.ReactorAccountServiceCheckStub initStub(){
        return ReactorAccountServiceCheckGrpc
                .newReactorStub(this.channel)
                .withDeadlineAfter(3000, TimeUnit.MILLISECONDS);
    }

    private void shutdownChannel(){
        if(this.channel != null && !this.channel.isShutdown()){
            this.channel.shutdownNow();
        }
    }
}
