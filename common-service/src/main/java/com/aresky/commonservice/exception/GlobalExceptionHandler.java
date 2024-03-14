package com.aresky.commonservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    private ResponseEntity<MessageResponse> handleCommonException(CommonException ex) {
        MessageResponse message = new MessageResponse(ex.getMessage());
        HttpStatus status = ex.getStatus();
        if (status == null) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.status(status).body(message);
    }
}
