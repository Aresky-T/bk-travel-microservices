package com.aresky.mailservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailException extends RuntimeException {
    private String message;

    public MailException(String message) {
        super(message);
        this.message = message;
    }
}
