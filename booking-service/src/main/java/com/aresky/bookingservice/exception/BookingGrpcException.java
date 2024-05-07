package com.aresky.bookingservice.exception;

import io.grpc.Status.Code;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingGrpcException extends Exception {

    private Code code;
    private String message;

    public BookingGrpcException(Code code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
