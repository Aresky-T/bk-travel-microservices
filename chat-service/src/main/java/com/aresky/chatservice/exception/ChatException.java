package com.aresky.chatservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatException extends RuntimeException {
    public static final ChatException STAFF_NOT_FOUND_EX = new ChatException(ExceptionMessage.STAFF_NOT_FOUND);

    private String message;

    public ChatException(String message) {
        super(message);
        this.message = message;
    }
}
