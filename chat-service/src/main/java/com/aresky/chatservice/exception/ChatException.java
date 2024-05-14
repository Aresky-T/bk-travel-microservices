package com.aresky.chatservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatException extends RuntimeException {
    private String message;

    public ChatException(String message) {
        super(message);
        this.message = message;
    }
}
