package com.aresky.accountservice.delivery.grpc;

import com.aresky.accountservice.model.Profile;
import com.aresky.accountservice.service.profile.IProfileService;
import com.aresky.accountservice.utils.DateUtils;
import grpc.account.check.ReactorAccountProfileCheckServiceGrpc;
import grpc.account.dto.request.CheckProfileByAccountIdRequest;
import grpc.account.dto.request.CheckProfileByEmailRequest;
import grpc.account.dto.response.CheckProfileByAccountIdResponse;
import grpc.account.dto.response.CheckProfileByEmailResponse;
import grpc.account.dto.response.ProfileResponse;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.Optional;

@GrpcService
public class ReactorProfileServiceCheckGrpcImp
        extends ReactorAccountProfileCheckServiceGrpc.AccountProfileCheckServiceImplBase {
    @Autowired
    private IProfileService profileService;

    @Override
    public Mono<CheckProfileByAccountIdResponse> checkProfileByAccountId(Mono<CheckProfileByAccountIdRequest> request) {
        System.out.println("Checking profile by account_id...");
        return request.map(CheckProfileByAccountIdRequest::getAccountId)
                .flatMap(accountIdField -> {
                    int accountId = accountIdField.getId();
                    CheckProfileByAccountIdResponse.Builder responseBuilder = CheckProfileByAccountIdResponse
                            .newBuilder();
                    return profileService.findByAccountId(accountId)
                            .flatMap(profile -> Mono.just(responseBuilder
                                    .setIsExistsAccountId(true)
                                    .setProfile(mapToProfileResponse(profile))
                                    .build()))
                            .switchIfEmpty(Mono.just(responseBuilder.setIsExistsAccountId(false).build()));
                });
    }

    @Override
    public Mono<CheckProfileByEmailResponse> checkProfileByEmail(Mono<CheckProfileByEmailRequest> request) {
        System.out.println("Checking profile by email...");
        return request.map(CheckProfileByEmailRequest::getEmail)
                .flatMap(accountEmailField -> {
                    String email = accountEmailField.getEmail();
                    CheckProfileByEmailResponse.Builder responseBuilder = CheckProfileByEmailResponse.newBuilder();
                    return profileService.findByAccountEmail(email)
                            .flatMap(profile -> Mono.just(responseBuilder
                                    .setIsExistsEmail(true)
                                    .setProfile(mapToProfileResponse(profile))
                                    .build()))
                            .switchIfEmpty(Mono.just(responseBuilder.setIsExistsEmail(false).build()));
                });
    }

    private ProfileResponse mapToProfileResponse(Profile profile) {
        System.out.println(profile);
        return ProfileResponse.newBuilder()
                .setFullName(Optional.ofNullable(profile.getFullName()).orElse(""))
                .setDateOfBirth(profile.getDateOfBirth() != null ? DateUtils.formatDate(profile.getDateOfBirth()) : "")
                .setGender(profile.getGender() != null ? profile.getGender().name() : "")
                .setAddress(Optional.ofNullable(profile.getAddress()).orElse(""))
                .setPhone(Optional.ofNullable(profile.getPhone()).orElse(""))
                .setAvatarUrl(Optional.ofNullable(profile.getAvatarUrl()).orElse(""))
                .build();
    }
}
