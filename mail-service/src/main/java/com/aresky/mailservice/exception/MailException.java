package com.aresky.mailservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailException extends RuntimeException {
    public static final MailException STAFF_NOT_FOUND_EX = new MailException(ExceptionMessage.STAFF_NOT_FOUND);
    public static final MailException STAFF_PERMISSION_HAS_BEEN_ALLOWED_EX = new MailException(ExceptionMessage.STAFF_PERMISSION_HAS_BEEN_ALLOWED);
    public static final MailException STAFF_PERMISSION_HAS_BEEN_DENIED_EX = new MailException(ExceptionMessage.STAFF_PERMISSION_HAS_BEEN_DENIED);

    private String message;

    public MailException(String message) {
        super(message);
        this.message = message;
    }
}
