package com.aresky.paymentservice.exception;

import io.grpc.Status.Code;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentGrpcException extends RuntimeException {
    private Code code;
    private String message;

    public PaymentGrpcException(Code code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
