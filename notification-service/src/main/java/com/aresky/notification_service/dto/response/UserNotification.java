package com.aresky.notification_service.dto.response;

import com.aresky.notification_service.entity.Notification;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class UserNotification {
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
    private Map<String, Long> counts;
    private List<NotificationResponse> notifications;

    public UserNotification(Integer page, Integer size, Long totalElements, List<Notification> notifications) {
        this.size = size;
        this.page = page;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
        this.totalElements = totalElements;
        this.notifications = notifications.stream().map(NotificationResponse::fromNotification).toList();
    }

    public UserNotification(Integer page, Integer size, Map<String, Long> counts, List<Notification> notifications) {
        this.page = page;
        this.size = size;
        this.totalElements = counts.values().stream().reduce(Long::sum).orElse(0L);
        this.totalPages = (int) Math.ceil((double) totalElements / size);
        this.counts = counts;
        this.notifications = notifications.stream().map(NotificationResponse::fromNotification).toList();
    }
}
