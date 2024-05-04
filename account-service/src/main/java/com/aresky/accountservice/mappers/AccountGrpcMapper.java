package com.aresky.accountservice.mappers;

import grpc.account.AccountResponse;
import grpc.account.CreateAccountRequest;
import com.aresky.accountservice.model.Account;

import java.time.format.DateTimeFormatter;

public class AccountGrpcMapper {
    private static final DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ISO_INSTANT;

    public static Account of(CreateAccountRequest request){
        return new Account(request.getUsername(), request.getEmail(), request.getPassword());
    }

    public static AccountResponse toGrpc(Account account){
        return AccountResponse.newBuilder()
                .setId(account.getId())
                .setUsername(account.getUsername())
                .setEmail(account.getEmail())
                .setPassword(account.getPassword())
                .setRole(account.getRole().name())
                .setStatus(account.getActivationStatus().name())
                .setCreatedTime(FORMATTER_1.format(account.getCreatedTime()))
                .build();
    }
}
