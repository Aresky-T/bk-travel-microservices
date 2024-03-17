package com.aresky.authservice.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AuthException extends RuntimeException {
    private String message;
    private HttpStatus status;
    private String code;

    public AuthException(String message) {
        super(message);
        this.message = message;
    }

    public AuthException(String message, HttpStatus status, String code) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
