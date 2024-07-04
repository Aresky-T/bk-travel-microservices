package com.aresky.notification_service.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.aresky.notification_service.entity.NotificationKeyword;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface INotificationKeywordRepository extends R2dbcRepository<NotificationKeyword, Integer> {
    Mono<Boolean> existsByKeyword(String keyword);
    Mono<Boolean> existsByNotificationTypeIdAndKeyword(Integer notificationTypeId, String keyword);
    Mono<Void> deleteByNotificationTypeIdAndKeyword(Integer notificationTypeId, String keyword);
    Flux<NotificationKeyword> findByNotificationTypeId(Integer notificationTypeId);
}
