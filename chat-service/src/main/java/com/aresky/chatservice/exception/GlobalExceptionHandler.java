package com.aresky.chatservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.r2dbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChatException.class)
    public ResponseEntity<MessageResponse> handlingChatException(ChatException ex){
        log.error("Chat Exception: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<MessageResponse> handlingBadSqlGrammarException(BadSqlGrammarException ex){
        String message = Objects.requireNonNull(ex.getR2dbcException()).getMessage();
        log.error("Bad SQL Grammar Exception: {}", message);
        return ResponseEntity.badRequest().body(new MessageResponse(message));
    }
}
