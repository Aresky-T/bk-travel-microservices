package com.aresky.notification_service.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.aresky.notification_service.entity.NotificationType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface INotificationTypeRepository extends R2dbcRepository<NotificationType, Integer> {
    Mono<Boolean> existsByName(String name);
    Mono<NotificationType> findByName(String name);
    Flux<NotificationType> findByEntityType(NotificationType.EntityType entityType);
}
