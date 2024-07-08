package com.aresky.notification_service.controller;

import com.aresky.notification_service.dto.request.NotificationRequest;
import com.aresky.notification_service.dto.request.TemplateUpdateRequest;
import com.aresky.notification_service.dto.request.TypeRequest;
import com.aresky.notification_service.dto.response.NotificationTypeResponse;
import com.aresky.notification_service.dto.response.UserNotification;
import com.aresky.notification_service.service.notification.INotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    public static final ResponseEntity<String> SUCCESS_RESPONSE = ResponseEntity.ok("success");

    @Autowired
    private INotificationService notificationService;

    // GET - getAllNotifications(Integer userId)
    // @GetMapping("/all-by-user")
    // public Mono<ResponseEntity<List<NotificationResponse>>> getAllNotifications(
    // @RequestParam Integer userId,
    // @RequestParam Integer limit,
    // @RequestParam Integer offset
    // ){
    // return notificationService.getAllNotifications(userId, limit,
    // offset).map(ResponseEntity::ok);
    // }

    @GetMapping
    public Mono<ResponseEntity<UserNotification>> getUserNotifications(@RequestParam Integer userId, @RequestParam Integer page, @RequestParam Integer size) {
        return notificationService.getUserNotificationDto(userId, page, size).map(ResponseEntity::ok);
    }

    @GetMapping("/type/id/{id}")
    public Mono<ResponseEntity<NotificationTypeResponse>> getNotificationType(@PathVariable("id") Integer typeId) {
        return notificationService.getNotificationTypeResponse(typeId).map(ResponseEntity::ok);
    }

    @GetMapping("/type/name/{name}")
    public Mono<ResponseEntity<NotificationTypeResponse>> getNotificationType(@PathVariable("name") String typeName) {
        return notificationService.getNotificationTypeResponse(typeName).map(ResponseEntity::ok);
    }

    @GetMapping("/types")
    public Mono<ResponseEntity<List<NotificationTypeResponse>>> getAllNotificationTypes(@RequestParam String entityType) {
        return notificationService.getAllNotificationTypesByEntityType(entityType).map(ResponseEntity::ok);
    }

    @GetMapping("/new/count")
    public Mono<ResponseEntity<Long>> getNewNotificationCount(@RequestParam Integer userId) {
        return notificationService.getNewNotificationCount(userId).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<?>> createNotification(@Valid @RequestBody NotificationRequest request) {
        return notificationService.createNotification(request).thenReturn(SUCCESS_RESPONSE);
    }

    @PostMapping("/type")
    public Mono<ResponseEntity<?>> createNotificationType(@Valid @RequestBody TypeRequest request) {
        return notificationService.createNotificationType(request).thenReturn(SUCCESS_RESPONSE);
    }

    @PostMapping("/keyword")
    public Mono<ResponseEntity<?>> addKeyWord(@RequestParam Integer typeId, @RequestParam String keyword) {
        return notificationService.addKeyWord(typeId, keyword).thenReturn(SUCCESS_RESPONSE);
    }

    @PatchMapping("/read")
    public Mono<ResponseEntity<?>> readNotification(@RequestParam Integer notificationId) {
        return notificationService.readNotification(notificationId).thenReturn(SUCCESS_RESPONSE);
    }

    @PatchMapping("/read/all")
    public Mono<ResponseEntity<?>> readAllNotifications(@RequestParam Integer userId) {
        return notificationService.readAllNotifications(userId).thenReturn(SUCCESS_RESPONSE);
    }

    @PatchMapping("/view")
    public Mono<ResponseEntity<?>> viewNotification(@RequestParam Integer userId) {
        return notificationService.viewNotification(userId).thenReturn(SUCCESS_RESPONSE);
    }

    @PatchMapping("/template")
    public Mono<ResponseEntity<?>> updateTemplate(@RequestBody TemplateUpdateRequest request) {
        return notificationService.updateTemplate(request.getTypeId(), request.getTemplate()).thenReturn(SUCCESS_RESPONSE);
    }

    @DeleteMapping
    public Mono<ResponseEntity<?>> removeNotification(@RequestParam Integer notificationId) {
        return notificationService.removeNotification(notificationId).thenReturn(SUCCESS_RESPONSE);
    }

    @DeleteMapping("/type")
    public Mono<ResponseEntity<?>> removeNotificationType(@RequestParam Integer typeId) {
        return notificationService.removeNotificationType(typeId).thenReturn(SUCCESS_RESPONSE);
    }

    @DeleteMapping("/keyword")
    public Mono<ResponseEntity<?>> removeKeyWord(@RequestParam Integer typeId, @RequestParam String keyword) {
        return notificationService.removeKeyWord(typeId, keyword).thenReturn(SUCCESS_RESPONSE);
    }
}
