package com.aresky.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<MessageResponse> handleCommonException(RuntimeException ex) {
        log.error("Runtime exception: {}", ex.getMessage());
        MessageResponse message = new MessageResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(AuthException.class)
    private ResponseEntity<MessageResponse> handleCommonException(AuthException ex) {
        log.error("Auth exception: {}", ex.getMessage());
        MessageResponse message = new MessageResponse(ex.getMessage());
        HttpStatus status = ex.getStatus();
        if (status == null) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.status(status).body(message);
    }
}
