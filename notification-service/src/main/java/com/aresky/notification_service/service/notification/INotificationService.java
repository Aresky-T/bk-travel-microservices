package com.aresky.notification_service.service.notification;

import com.aresky.notification_service.dto.request.NotificationRequest;
import com.aresky.notification_service.dto.request.TypeRequest;
import com.aresky.notification_service.dto.response.NotificationResponse;
import com.aresky.notification_service.dto.response.NotificationTypeResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface INotificationService {
    Mono<List<NotificationResponse>> getAllNotifications(Integer accountId);
    Mono<NotificationTypeResponse> getNotificationTypeResponse(Integer typeId);
    Mono<NotificationTypeResponse> getNotificationTypeResponse(String typeName);
    Mono<List<NotificationTypeResponse>> getAllNotificationTypesByEntityType(String entityType);
    Mono<Void> createNotification(NotificationRequest request);
    Mono<Void> createNotification(Mono<NotificationRequest> request);
    Mono<Void> createNotificationType(TypeRequest request);
    Mono<Void> readNotification(Integer notificationId);
    Mono<Void> addKeyWord(Integer notificationTypeId, String keyword);
    Mono<Void> removeKeyWord(Integer notificationTypeId, String keyword);
    Mono<Void> updateTemplate(Integer notificationTypeId, String template);
    Mono<Void> removeNotificationType(Integer typeId);
}
