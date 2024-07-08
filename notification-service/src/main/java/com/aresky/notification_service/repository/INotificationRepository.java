package com.aresky.notification_service.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.aresky.notification_service.entity.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface INotificationRepository extends R2dbcRepository<Notification, Integer> {
    Flux<Notification> findByUserId(Integer userId);
    Mono<Long> countByUserIdAndIsNew(Integer userId, Boolean isNew);
    Flux<Notification> findByUserIdAndIsNew(Integer userId, Boolean isNew);
    Mono<Long> countByUserId(Integer userId);
}
