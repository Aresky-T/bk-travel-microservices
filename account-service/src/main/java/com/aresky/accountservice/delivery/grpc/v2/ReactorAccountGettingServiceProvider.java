package com.aresky.accountservice.delivery.grpc.v2;

import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.model.EGender;
import com.aresky.accountservice.model.Profile;
import com.aresky.accountservice.repository.AccountRepository;
import com.aresky.accountservice.repository.ProfileRepository;
import com.aresky.accountservice.utils.DateUtils;
import grpc.account.v2.dto.request.*;
import grpc.account.v2.dto.response.*;
import grpc.account.v2.service.ReactorAccountGettingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.Optional;

@GrpcService
public class ReactorAccountGettingServiceProvider extends ReactorAccountGettingServiceGrpc.AccountGettingServiceImplBase {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<GetAccountByIdResponse> getAccountById(Mono<GetAccountByIdRequest> request) {
        return request.map(GetAccountByIdRequest::getAccountId)
                .flatMap(accountId -> accountRepository.existsById(accountId)
                        .flatMap(existsAccount -> existsAccount
                                ? accountRepository.findById(accountId)
                                .map(this::toGetAccountByIdResponse)
                                : Mono.just(GetAccountByIdResponse.newBuilder().build())
                        ));
    }

    @Override
    public Mono<GetAccountByEmailResponse> getAccountByEmail(Mono<GetAccountByEmailRequest> request) {
        return request.map(GetAccountByEmailRequest::getEmail)
                .flatMap(email -> accountRepository.existsByEmail(email)
                        .flatMap(existsAccount -> existsAccount
                                ? accountRepository.findByEmail(email)
                                .map(this::mapToGetAccountByEmailResponse)
                                : Mono.just(GetAccountByEmailResponse.newBuilder().build())
                        ));
    }

    @Override
    public Mono<GetAccountByUsernameAndPasswordResponse> getAccountByUsernameAndPassword(Mono<GetAccountByUsernameAndPasswordRequest> request) {
        return request.flatMap(reqValue -> {
            String username = reqValue.getUsername();
            String password = reqValue.getPassword();

            GetAccountByUsernameAndPasswordResponse.Builder responseBuilder = GetAccountByUsernameAndPasswordResponse.newBuilder();

            return accountRepository.existsByUsername(username)
                    .flatMap(existsAccount -> existsAccount
                            ? accountRepository.findByUsername(username)
                            .map(account -> isMatchedPasswords(password, account.getPassword())
                                    ? responseBuilder.setAccount(mapToAccountResponse(account)).build()
                                    : responseBuilder.build())
                            : Mono.just(responseBuilder.build())
                    );
        });
    }

    @Override
    public Mono<GetProfileByAccountIdResponse> getProfileByAccountId(Mono<GetProfileByAccountIdRequest> request) {
        return request.map(GetProfileByAccountIdRequest::getAccountId)
                .flatMap(accountId -> accountRepository.existsById(accountId)
                        .flatMap(existsAccount -> existsAccount
                                ? profileRepository.findByAccountId(accountId)
                                .map(this::toGetProfileByAccountIdResponse)
                                : Mono.just(GetProfileByAccountIdResponse.newBuilder().build())));
    }

    @Override
    public Mono<GetProfileByEmailResponse> getProfileByEmail(Mono<GetProfileByEmailRequest> request) {
        return request.map(GetProfileByEmailRequest::getEmail)
                .flatMap(email -> accountRepository.existsByEmail(email)
                        .flatMap(existsAccount -> existsAccount
                                ? accountRepository.findByEmail(email)
                                .map(Account::getId)
                                .flatMap(profileRepository::findByAccountId)
                                .map(this::toGetProfileByEmailResponse)
                                :
                                 Mono.just(GetProfileByEmailResponse.newBuilder().build())
                        ));
    }

    private GetAccountByIdResponse toGetAccountByIdResponse(Account account){
        return GetAccountByIdResponse.newBuilder().setAccount(mapToAccountResponse(account)).build();
    }

    private GetAccountByEmailResponse mapToGetAccountByEmailResponse(Account account){
        return GetAccountByEmailResponse.newBuilder().setAccount(mapToAccountResponse(account)).build();
    }

    private GetProfileByAccountIdResponse toGetProfileByAccountIdResponse(Profile profile){
        return GetProfileByAccountIdResponse.newBuilder().setProfile(mapToProfileResponse(profile)).build();
    }

    private GetProfileByEmailResponse toGetProfileByEmailResponse(Profile profile){
        return GetProfileByEmailResponse.newBuilder().setProfile(mapToProfileResponse(profile)).build();
    }

    private Boolean isMatchedPasswords(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.newBuilder()
                .setId(account.getId())
                .setUsername(account.getUsername())
                .setEmail(account.getEmail())
                .setRole(account.getRole().name())
                .setStatus(account.getActivationStatus().name())
                .setCreatedTime(DateUtils.formatDateTime(account.getCreatedTime()))
                .build();
    }

    private ProfileResponse mapToProfileResponse(Profile profile) {
        return ProfileResponse.newBuilder()
                .setId(profile.getId())
                .setFullName(Optional.ofNullable(profile.getFullName()).orElse(""))
                .setDateOfBirth(Optional.ofNullable(profile.getDateOfBirth()).map(DateUtils::formatDate).orElse(""))
                .setGender(Optional.ofNullable(profile.getGender()).map(EGender::name).orElse(""))
                .setPhone(Optional.ofNullable(profile.getPhone()).orElse(""))
                .setAddress(Optional.ofNullable(profile.getAddress()).orElse(""))
                .setAvatarUrl(Optional.ofNullable(profile.getAvatarUrl()).orElse(""))
                .build();
    }
}
