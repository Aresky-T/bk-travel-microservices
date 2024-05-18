package com.aresky.staffservice.service.account;

import com.aresky.staffservice.exception.StaffException;
import grpc.account.check.ReactorAccountServiceCheckGrpc;
import grpc.account.dto.request.CheckAccountByEmailRequest;
import grpc.account.dto.response.AccountResponse;
import grpc.account.dto.response.CheckAccountByEmailResponse;
import grpc.account.fields.AccountEmailField;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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

        return initStub().flatMap(stub -> stub.checkAccountByEmail(request))
                .filter(CheckAccountByEmailResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(CheckAccountByEmailResponse::getAccount)
                .onErrorResume(err -> Mono.error(new StaffException(err.getMessage())));
    }

    private Mono<ReactorAccountServiceCheckGrpc.ReactorAccountServiceCheckStub> initStub(){
        return Mono.just(ReactorAccountServiceCheckGrpc.newReactorStub(this.channel));
    }

    private void shutdownChannel(){
        if(this.channel != null && !this.channel.isShutdown()){
            this.channel.shutdownNow();
        }
    }
}
