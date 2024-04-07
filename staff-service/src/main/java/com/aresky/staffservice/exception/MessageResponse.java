package com.aresky.staffservice.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse {
    private String error;
    private String message;
    private Integer status;

    public MessageResponse(String message) {
        this.error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }

    public MessageResponse(String error, String message, Integer status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }
}
