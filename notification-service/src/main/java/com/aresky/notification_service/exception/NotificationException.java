package com.aresky.notification_service.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationException extends RuntimeException {
    public static final NotificationException INVALID_TYPE_ID = new NotificationException("Invalid typeId!");
    public static final NotificationException INVALID_USER_ID = new NotificationException("Invalid userId!");
    public static final NotificationException INVALID_ENTITY_TYPE = new NotificationException("Invalid entity type!");
    public static final NotificationException INVALID_TYPE_NAME = new NotificationException("Invalid type name!");
    public static final NotificationException INVALID_NOTIFICATION_ID = new NotificationException("Invalid notificationId!");

    public static final NotificationException TYPE_ALREADY_EXISTS = new NotificationException("This notification type already exists!");
    public static final NotificationException KEYWORD_ALREADY_EXISTS = new NotificationException("This keyword already exists!");

    public static final NotificationException KEYWORD_NOT_FOUND = new NotificationException("Keyword not found!");
    public static final NotificationException INVALID_PAGE_AND_SIZE = new NotificationException("Page and size must be greater than zero!");

    private MessageResponse response;

    public NotificationException(String message) {
        super(message);
        this.response = new MessageResponse(message);
    }

    @Data
    @NoArgsConstructor
    public static class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }
    }
}
