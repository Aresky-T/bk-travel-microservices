package com.aresky.commonservice.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CommonException extends RuntimeException {
    private String message;
    private HttpStatus status;
    private String code;

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, HttpStatus status, String code) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
