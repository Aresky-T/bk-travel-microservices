package com.aresky.accountservice.delivery.grpc.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.model.Profile;
import com.aresky.accountservice.repository.AccountRepository;
import com.aresky.accountservice.repository.ProfileRepository;

import grpc.account.v2.dto.request.CreateAccountRequest;
import grpc.account.v2.dto.request.CreateProfileRequest;
import grpc.account.v2.dto.response.CreateAccountResponse;
import grpc.account.v2.dto.response.CreateProfileResponse;
import grpc.account.v2.service.ReactorAccountCreatingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
public class ReactorAccountCreatingServiceProvider
        extends ReactorAccountCreatingServiceGrpc.AccountCreatingServiceImplBase {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Mono<CreateAccountResponse> createAccount(Mono<CreateAccountRequest> request) {
        return request.flatMap(reqVal -> {
            String username = reqVal.getUsername();
            String email = reqVal.getEmail();
            String password = reqVal.getPassword();

            Mono<Boolean> usernameExistsMono = accountRepository.existsByUsername(username);
            Mono<Boolean> emailExistsMono = accountRepository.existsByEmail(email);

            CreateAccountResponse.Builder responseBuilder = CreateAccountResponse.newBuilder();

            return Mono.zip(usernameExistsMono, emailExistsMono)
                    .flatMap(tuple -> {
                        boolean existsUsername = tuple.getT1();
                        boolean existsEmail = tuple.getT2();

                        if (existsUsername || existsEmail) {
                            return Mono.just(responseBuilder.setIsSuccess(false).build());
                        }

                        String encodedPassword = encoder.encode(password);
                        Account account = new Account(username, email, encodedPassword);

                        return accountRepository.save(account)
                                .flatMap(newAcc -> profileRepository.save(new Profile(newAcc)))
                                .thenReturn(responseBuilder.setIsSuccess(true).build());
                    });
        });
    }

    @Override
    public Mono<CreateProfileResponse> createProfile(Mono<CreateProfileRequest> request) {
        CreateProfileResponse successResponse = this.toCreateProfileResponse(true);
        CreateProfileResponse failedResponse = this.toCreateProfileResponse(false);

        return request.map(CreateProfileRequest::getAccountId)
                .flatMap(accountId -> accountRepository.existsById(accountId)
                        .flatMap(existsAccount -> existsAccount
                                ? (profileRepository.existsByAccountId(accountId)
                                        .flatMap(existProfile -> existProfile ? Mono.just(failedResponse)
                                                : profileRepository.save(new Profile(accountId))
                                                        .thenReturn(successResponse)))
                                : Mono.just(failedResponse)));
    }

    private CreateProfileResponse toCreateProfileResponse(boolean isSuccess) {
        return CreateProfileResponse.newBuilder().setIsSuccess(isSuccess).build();
    }

}
