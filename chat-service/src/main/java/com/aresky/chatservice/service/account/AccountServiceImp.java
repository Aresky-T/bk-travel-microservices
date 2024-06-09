package com.aresky.chatservice.service.account;

import com.aresky.chatservice.exception.ChatException;
import grpc.account.check.ReactorAccountServiceCheckGrpc;
import grpc.account.dto.request.CheckAccountByEmailRequest;
import grpc.account.dto.request.CheckAccountByIdRequest;
import grpc.account.dto.response.AccountResponse;
import grpc.account.dto.response.CheckAccountByEmailResponse;
import grpc.account.dto.response.CheckAccountByIdResponse;
import grpc.account.fields.AccountEmailField;
import grpc.account.fields.AccountIdField;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImp implements IAccountService{
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
    public Mono<AccountResponse> getAccountById(Integer accountId) {
        return initStub().checkAccountById(createRequest(accountId))
                .filter(CheckAccountByIdResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(CheckAccountByIdResponse::getAccount)
                .onErrorResume(err -> Mono.error(new ChatException(err.getMessage())));
    }

    @Override
    public Mono<Boolean> existsAccountById(Integer accountId) {
        return initStub().checkAccountById(createRequest(accountId))
                .map(CheckAccountByIdResponse::getIsExists)
                .onErrorResume(err -> Mono.error(new ChatException(err.getMessage())));
    }

    @Override
    public Mono<AccountResponse> getAccountByEmail(String email) {
        return initStub().checkAccountByEmail(createRequest(email))
                .filter(CheckAccountByEmailResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(CheckAccountByEmailResponse::getAccount)
                .onErrorResume(err -> Mono.error(new ChatException(err.getMessage())));
    }

    @Override
    public Mono<Boolean> existsAccountByEmail(String email) {
        return initStub().checkAccountByEmail(createRequest(email))
                .map(CheckAccountByEmailResponse::getIsExists)
                .onErrorResume(err -> Mono.error(new ChatException(err.getMessage())));
    }

    private ReactorAccountServiceCheckGrpc.ReactorAccountServiceCheckStub initStub(){
        return ReactorAccountServiceCheckGrpc.newReactorStub(this.channel);
    }

    private CheckAccountByIdRequest createRequest(Integer accountId){
        AccountIdField accountIdField = AccountIdField.newBuilder().setId(accountId).build();
        return CheckAccountByIdRequest.newBuilder().setAccountId(accountIdField).build();
    }

    private CheckAccountByEmailRequest createRequest(String email){
        AccountEmailField accountEmailField = AccountEmailField.newBuilder().setEmail(email).build();
        return CheckAccountByEmailRequest.newBuilder().setAccountEmail(accountEmailField).build();
    }

    private void shutdownChannel() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdownNow();
        }
    }
}
