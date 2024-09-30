package com.aresky.notification_service.service.notification;

import com.aresky.notification_service.dto.request.NotificationRequest;
import com.aresky.notification_service.dto.request.TypeRequest;
import com.aresky.notification_service.dto.response.NotificationResponse;
import com.aresky.notification_service.dto.response.NotificationTypeResponse;
import com.aresky.notification_service.dto.response.UserNotification;
import com.aresky.notification_service.entity.NotificationType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface INotificationService {
    Mono<Long> getNewNotificationCount(Integer userId);
    Mono<List<NotificationResponse>> getAllNotifications(Integer accountId,  Integer limit, Integer offset);
    Mono<UserNotification> getUserNotificationDto(Integer accountId, Integer page, Integer size);
    Flux<NotificationType> getAllNotificationTypes();
    Mono<NotificationTypeResponse> getNotificationTypeResponse(Integer typeId);
    Mono<NotificationTypeResponse> getNotificationTypeResponse(String typeName);
    Mono<List<NotificationTypeResponse>> getAllNotificationTypesByEntityType(String entityType);
    Mono<Void> createNotification(NotificationRequest request);
    Mono<Void> createNotification(Mono<NotificationRequest> request);
    Mono<Void> createNotificationType(TypeRequest request);
    Mono<Void> readNotification(Integer notificationId);
    Mono<Void> readAllNotifications(Integer userId);
    Mono<Void> viewNotification(Integer userId);
    Mono<Void> addKeyWord(Integer notificationTypeId, String keyword);
    Mono<Void> removeKeyWord(Integer notificationTypeId, String keyword);
    Mono<Void> updateTemplate(Integer notificationTypeId, String template);
    Mono<Void> removeNotificationType(Integer typeId);
    Mono<Void> removeNotification(Integer notificationId);
}
