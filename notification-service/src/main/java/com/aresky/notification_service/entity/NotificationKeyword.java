package com.aresky.notification_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("notification_keywords")
public class NotificationKeyword {
    @Id
    private Integer id;

    @Column("keyword")
    private String keyword;

    @Column("notification_type_id")
    private Integer notificationTypeId;

    public NotificationKeyword(String keyword, Integer notificationTypeId) {
        this.keyword = keyword;
        this.notificationTypeId = notificationTypeId;
    }
}
