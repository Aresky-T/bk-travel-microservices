package com.aresky.notification_service.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<NotificationException.MessageResponse> handlingNotificationException(NotificationException ex){
        log.error("Notification error occurred: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getResponse());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<NotificationException.MessageResponse> handlingIllegalStateException(IllegalStateException ex){
        log.error("IllegalStateException occurred: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new NotificationException.MessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<?> handlingWebExchangeBindException(WebExchangeBindException ex){
        log.error("WebExchangeBindException occurred: {}", ex.getMessage());
        var errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(NotificationException.MessageResponse::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<NotificationException.MessageResponse> handlingIllegalStateException(ValidationException ex){
        log.error("ValidationException occurred: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new NotificationException.MessageResponse(ex.getMessage()));
    }
}
