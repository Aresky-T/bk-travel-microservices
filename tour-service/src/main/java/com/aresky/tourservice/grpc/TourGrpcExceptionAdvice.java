package com.aresky.tourservice.grpc;

import com.aresky.tourservice.exception.TourGrpcException;

import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@Slf4j
@GrpcAdvice
public class TourGrpcExceptionAdvice {

    @GrpcExceptionHandler(TourGrpcException.class)
    public Status handleTourGrpcException(TourGrpcException ex) {
        log.error("Tour grpc exception with code: {}, message: {}", ex.getCode().name(), ex.getMessage());
        return Status.fromCode(ex.getCode()).withDescription(ex.getMessage()).withCause(ex);
    }
}
