package com.aresky.notification_service.dto.response;

import com.aresky.notification_service.entity.Notification;
import com.aresky.notification_service.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Integer id;
    private Integer userId;
    private String type;
    private String message;
    private Integer entityId;
    private String entityType;
    private Boolean isRead;
    private Boolean isNew;
    private String createdAt;

    public static NotificationResponse fromNotification(Notification notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .type(notification.getType().getName())
                .message(notification.getMessage())
                .entityId(notification.getEntityId())
                .entityType(notification.getType().getEntityType().name())
                .isRead(notification.getIsRead())
                .isNew(notification.getIsNew())
                .createdAt(DateUtils.formatDateTime(notification.getCreatedAt()))
                .build();
    }
}
