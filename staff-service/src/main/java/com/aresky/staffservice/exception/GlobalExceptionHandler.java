package com.aresky.staffservice.exception;

import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.r2dbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(StaffException.class)
        public ResponseEntity<MessageResponse> handlingStaffException(StaffException ex) {
                log.error("StaffException: {}", ex.getMessage());
                return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }

        @ExceptionHandler(BadSqlGrammarException.class)
        public ResponseEntity<MessageResponse> handlingBadSqlGrammarException(BadSqlGrammarException ex) {
                log.error("BadSqlGrammarException: {}", ex.getMessage());
                return ResponseEntity.internalServerError().body(
                                new MessageResponse(
                                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

        @ExceptionHandler(DataAccessResourceFailureException.class)
        public ResponseEntity<MessageResponse> handlingDataAccessResourceFailureException(
                        DataAccessResourceFailureException ex) {
                log.error("DataAccessResourceFailureException: {}", ex.getMessage());
                return ResponseEntity.internalServerError().body(
                                new MessageResponse(
                                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

        @ExceptionHandler(ClassCastException.class)
        public ResponseEntity<MessageResponse> handlingClassCastException(ClassCastException ex) {
                log.error("ClassCastException: {}", ex.getMessage());
                return ResponseEntity.internalServerError().body(
                                new MessageResponse(
                                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

        @ExceptionHandler(DuplicateKeyException.class)
        public ResponseEntity<MessageResponse> handlingDuplicateKeyException(DuplicateKeyException ex) {
                log.error("DuplicateKeyException: {}", ex.getMessage());
                return ResponseEntity.status(500)
                                .body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                ex.getMessage(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

        @ExceptionHandler(ConverterNotFoundException.class)
        public ResponseEntity<MessageResponse> handlingConverterNotFoundException(ConverterNotFoundException ex) {
                log.error("ConverterNotFoundException: {}", ex.getMessage());
                return ResponseEntity.status(500)
                                .body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                ex.getMessage(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
}
