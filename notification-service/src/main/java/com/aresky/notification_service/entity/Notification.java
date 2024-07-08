package com.aresky.notification_service.entity;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("notifications")
public class Notification {
    @Id
    private Integer id;

    @Column("user_id")
    private Integer userId;

    @Column("type_id")
    private Integer typeId;

    @Column("entity_id")
    private Integer entityId;

    @Column("message")
    private String message;

    @Column("is_read")
    private Boolean isRead;

    @Column("is_new")
    private Boolean isNew;

    @Column("created_at")
    private ZonedDateTime createdAt;

    @Transient
    private NotificationType type;

    public Notification type(NotificationType type) {
        this.type = type;
        return this;
    }

    public Notification isNew(Boolean isNew) {
        this.isNew = isNew;
        return this;
    }

    public Notification read(Boolean isRead) {
        this.isRead = isRead;
        return this;
    }

    public static Notification read(Notification notification){
        notification.isRead = Boolean.TRUE;
        return notification;
    }
}
