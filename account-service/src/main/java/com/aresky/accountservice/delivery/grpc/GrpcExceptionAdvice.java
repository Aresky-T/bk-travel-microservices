package com.aresky.accountservice.delivery.grpc;

import com.aresky.accountservice.exception.AccountGrpcException;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@Slf4j
@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler(AccountGrpcException.class)
    public Status handleAccountException(AccountGrpcException ex) {
        log.error("GrpcException: {} with code: {}", ex.getMessage(), ex.getCode().name());
        return Status.fromCode(ex.getCode()).withDescription(ex.getMessage()).withCause(ex);
    }
}
