package com.aresky.accountservice.delivery.grpc.v2;

import com.aresky.accountservice.repository.AccountRepository;
import com.aresky.accountservice.utils.PasswordUtils;

import grpc.account.v2.dto.request.ResetPasswordRequest;
import grpc.account.v2.dto.response.ResetPasswordResponse;
import grpc.account.v2.service.ReactorAccountUpdatingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import reactor.core.publisher.Mono;

@GrpcService
public class ReactorAccountUpdatingServiceProvider
        extends ReactorAccountUpdatingServiceGrpc.AccountUpdatingServiceImplBase {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<ResetPasswordResponse> resetPassword(Mono<ResetPasswordRequest> request) {
        return request.map(ResetPasswordRequest::getEmail)
                .flatMap(email -> accountRepository.existsByEmail(email)
                        .flatMap(existsAccount -> {
                            ResetPasswordResponse.Builder responseBuilder = ResetPasswordResponse.newBuilder();

                            if (!existsAccount) {
                                return Mono.just(responseBuilder.build());
                            }

                            return accountRepository.findByEmail(email)
                                    .flatMap(account -> {
                                        String newPassword = PasswordUtils.randomSecurePassword(10);
                                        account.setPassword(passwordEncoder.encode(newPassword));
                                        return accountRepository.save(account)
                                                .thenReturn(responseBuilder.setNewPassword(newPassword).build());
                                    });
                        }));
    }
}
