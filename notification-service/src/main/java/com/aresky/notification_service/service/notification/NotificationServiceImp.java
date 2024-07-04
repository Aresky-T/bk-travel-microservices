package com.aresky.notification_service.service.notification;

import com.aresky.notification_service.dto.request.NotificationRequest;
import com.aresky.notification_service.dto.request.TypeRequest;
import com.aresky.notification_service.dto.response.NotificationResponse;
import com.aresky.notification_service.dto.response.NotificationTypeResponse;
import com.aresky.notification_service.entity.Notification;
import com.aresky.notification_service.entity.NotificationKeyword;
import com.aresky.notification_service.entity.NotificationType;
import com.aresky.notification_service.exception.NotificationException;
import com.aresky.notification_service.repository.INotificationKeywordRepository;
import com.aresky.notification_service.repository.INotificationRepository;
import com.aresky.notification_service.repository.INotificationTypeRepository;
import com.aresky.notification_service.service.account.IAccountGrpcClientService;
import com.aresky.notification_service.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImp implements INotificationService {
    @Autowired
    private IAccountGrpcClientService accountGrpcClientService;

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private INotificationTypeRepository typeRepository;

    @Autowired
    private INotificationKeywordRepository keywordRepository;


    @Override
    public Mono<List<NotificationResponse>> getAllNotifications(Integer accountId) {
        return accountGrpcClientService.checkExistsAccountById(accountId)
                .flatMap(existsAccount -> existsAccount
                        ? notificationRepository.findByUserId(accountId)
                        .flatMap(notification -> typeRepository.findById(notification.getTypeId())
                                .map(notification::type))
                        .map(NotificationResponse::fromNotification)
                        .collectList()
                        : Mono.error(NotificationException.INVALID_USER_ID));
    }

    @Override
    public Mono<NotificationTypeResponse> getNotificationTypeResponse(Integer typeId) {
        return typeRepository.findById(typeId)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_TYPE_ID))
                .flatMap(type -> keywordRepository
                        .findByNotificationTypeId(type.getId())
                        .collectList()
                        .map(type::keywords))
                .map(NotificationTypeResponse::fromNotificationType);
    }

    @Override
    public Mono<NotificationTypeResponse> getNotificationTypeResponse(String typeName) {
        return typeRepository.findByName(typeName)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_TYPE_NAME))
                .flatMap(type -> keywordRepository
                        .findByNotificationTypeId(type.getId())
                        .collectList()
                        .map(type::keywords))
                .map(NotificationTypeResponse::fromNotificationType);
    }

    @Override
    public Mono<List<NotificationTypeResponse>> getAllNotificationTypesByEntityType(String entityType) {
        return Mono.justOrEmpty(NotificationType.EntityType.get(entityType))
                .switchIfEmpty(Mono.error(NotificationException.INVALID_ENTITY_TYPE))
                .flatMapMany(typeRepository::findByEntityType)
                .map(NotificationTypeResponse::fromNotificationType)
                .collectList();
    }

    @Override
    public Mono<Void> createNotification(NotificationRequest request) {
        return typeRepository.findByName(request.getTypeName())
                .switchIfEmpty(Mono.error(NotificationException.INVALID_TYPE_NAME))
                .flatMap(type -> {
                    Notification notification = new Notification();
                    notification.setTypeId(type.getId());
                    notification.setUserId(request.getUserId());
                    notification.setEntityId(request.getEntityId());
                    notification.setIsRead(Boolean.FALSE);
                    notification.setCreatedAt(DateUtils.now());

                    String message = buildMessage(type, request.getKeywords());
                    notification.setMessage(message);

                    return notificationRepository.save(notification).then();
                });
    }

    @Override
    public Mono<Void> createNotification(Mono<NotificationRequest> request) {
        return request
                .switchIfEmpty(Mono.empty())
                .flatMap(reqValue -> typeRepository.findByName(reqValue.getTypeName())
                        .switchIfEmpty(Mono.empty())
                        .flatMap(type -> {
                            Notification notification = new Notification();
                            notification.setTypeId(type.getId());
                            notification.setUserId(reqValue.getUserId());
                            notification.setEntityId(reqValue.getEntityId());
                            notification.setIsRead(Boolean.FALSE);
                            notification.setCreatedAt(DateUtils.now());

                            String message = buildMessage(type, reqValue.getKeywords());
                            notification.setMessage(message);
                            System.out.println(message);

                            return notificationRepository.save(notification).then();
                        })
                );
    }

    @Override
    public Mono<Void> createNotificationType(TypeRequest request) {
        return typeRepository.existsByName(request.getName())
                .flatMap(existsType -> existsType
                        ? Mono.error(NotificationException.TYPE_ALREADY_EXISTS)
                        : typeRepository.save(TypeRequest.toNotificationType(request)).then());
    }

    @Override
    public Mono<Void> readNotification(Integer notificationId) {
        return notificationRepository.findById(notificationId)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_NOTIFICATION_ID))
                .map(Notification::read)
                .flatMap(notificationRepository::save)
                .then();
    }

    @Override
    public Mono<Void> addKeyWord(Integer notificationTypeId, String keyword) {
        return typeRepository.existsById(notificationTypeId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_TYPE_ID))
                .then(keywordRepository.existsByNotificationTypeIdAndKeyword(notificationTypeId, keyword))
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(NotificationException.KEYWORD_ALREADY_EXISTS))
                .then(keywordRepository.save(new NotificationKeyword(keyword, notificationTypeId)))
                .then();
    }

    @Override
    public Mono<Void> removeKeyWord(Integer notificationTypeId, String keyword) {
        return typeRepository.existsById(notificationTypeId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_TYPE_ID))
                .then(keywordRepository.existsByNotificationTypeIdAndKeyword(notificationTypeId, keyword))
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(NotificationException.KEYWORD_NOT_FOUND))
                .then(keywordRepository.deleteByNotificationTypeIdAndKeyword(notificationTypeId, keyword));
    }

    @Override
    public Mono<Void> updateTemplate(Integer notificationTypeId, String template) {
        return typeRepository.findById(notificationTypeId)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_TYPE_ID))
                .map(type -> type.template(template))
                .flatMap(typeRepository::save)
                .then();
    }

    @Override
    public Mono<Void> removeNotificationType(Integer typeId) {
        return typeRepository.existsById(typeId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_TYPE_ID))
                .then(typeRepository.deleteById(typeId));
    }

    private static String buildMessage(NotificationType type, Map<String, Object> keysMap) {
        String template = type.getTemplate();
        String message = "<p>" + template + "</p>";

        if(keysMap != null){
            for (Map.Entry<String, Object> entry : keysMap.entrySet()) {
                String keyword = "{" + entry.getKey() + "}";
                Object value = entry.getValue();

                if(template.contains(keyword)){
                    message = message.replace(keyword, "<b>" + value.toString() + "</b>");
                }
            }
        }

        return message;
    }
}
