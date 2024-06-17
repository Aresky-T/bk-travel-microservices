package com.aresky.mailservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.r2dbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MailException.class)
    public ResponseEntity<MessageResponse> handlingMailException(MailException ex){
        logger.error("Mail Exception: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<MessageResponse> handlingBadSqlGrammarException(BadSqlGrammarException ex){
        String message = Objects.requireNonNull(ex.getR2dbcException()).getMessage();
        logger.error("Bad SQL Grammar Exception: {}", message);
        return ResponseEntity.badRequest().body(new MessageResponse(message));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<MessageResponse> handlingNullPointerException(NullPointerException ex){
        logger.error("Null Pointer Exception: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
    }
}
