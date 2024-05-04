package com.aresky.accountservice.exception;

import io.grpc.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountGrpcException extends Exception {

    private Status.Code code;
    private String message;

    public AccountGrpcException(Status.Code code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
