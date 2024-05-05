package com.aresky.paymentservice.exception;

import io.grpc.ManagedChannelProvider;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcAdvice
public class GlobalGrpcExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalGrpcExceptionHandler.class);

    @GrpcExceptionHandler(PaymentGrpcException.class)
    public Status handlePaymentGrpcException(PaymentGrpcException ex){
        log.error("Payment exception: {} with code: {}", ex.getMessage(), ex.getCode().name());
        return Status.fromCode(ex.getCode()).withDescription(ex.getMessage()).withCause(ex);
    }

    @GrpcExceptionHandler(ManagedChannelProvider.ProviderNotFoundException.class)
    public Status handleProviderNotFoundException(ManagedChannelProvider.ProviderNotFoundException ex){
        log.error("ProviderNotFoundException: {}", ex.getMessage());
        return Status.UNAVAILABLE.withDescription(ex.getMessage()).withCause(ex);
    }
}
