package com.aresky.tourservice.exception;

import io.grpc.Status.Code;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourGrpcException extends RuntimeException {
    private Code code;
    private String message;

    public TourGrpcException(Code code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
