package com.aresky.notification_service.service.notification;

import com.aresky.notification_service.compare.NotificationComparator;
import com.aresky.notification_service.dto.request.NotificationRequest;
import com.aresky.notification_service.dto.request.TypeRequest;
import com.aresky.notification_service.dto.response.NotificationResponse;
import com.aresky.notification_service.dto.response.NotificationTypeResponse;
import com.aresky.notification_service.dto.response.UserNotification;
import com.aresky.notification_service.entity.Notification;
import com.aresky.notification_service.entity.NotificationKeyword;
import com.aresky.notification_service.entity.NotificationType;
import com.aresky.notification_service.exception.NotificationException;
import com.aresky.notification_service.repository.INotificationKeywordRepository;
import com.aresky.notification_service.repository.INotificationRepository;
import com.aresky.notification_service.repository.INotificationTypeRepository;
import com.aresky.notification_service.service.account.IAccountGrpcClientService;
import com.aresky.notification_service.utils.DateUtils;
import io.r2dbc.spi.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.HashMap;
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

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private NotificationComparator notificationComparator;

    @Override
    public Mono<Long> getNewNotificationCount(Integer userId) {
        return accountGrpcClientService.checkExistsAccountById(userId)
                .flatMap(existsAccount -> existsAccount
                        ? notificationRepository.countByUserIdAndIsNew(userId, true)
                        : Mono.error(NotificationException.INVALID_USER_ID));
    }

    @Override
    public Mono<List<NotificationResponse>> getAllNotifications(Integer accountId, Integer limit, Integer offset) {
        return accountGrpcClientService.checkExistsAccountById(accountId)
                .flatMap(existsAccount -> existsAccount
                        ? this.findAllNotifications(accountId, limit, offset)
                                .flatMap(notification -> typeRepository.findById(notification.getTypeId())
                                        .map(notification::type))
                                .map(NotificationResponse::fromNotification)
                                .collectList()
                        : Mono.error(NotificationException.INVALID_USER_ID));
    }

    @Override
    public Mono<UserNotification> getUserNotificationDto(Integer accountId, Integer page, Integer size) {
        Mono<Boolean> validPageMono = Mono.just(page > 0);
        Mono<Boolean> validSizeMono = Mono.just(size > 0);
        Integer offset = (page - 1) * size;

        return Mono.zip(validPageMono, validSizeMono, (validPage, validSize) -> validPage && validSize)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_PAGE_AND_SIZE))
                .then(accountGrpcClientService.checkExistsAccountById(accountId))
                .flatMap(existsAccount -> existsAccount
                        ? this.findAllNotifications(accountId, size, offset)
                                .flatMap(notification -> typeRepository.findById(notification.getTypeId())
                                        .map(notification::type))
                                .collectList()
                                .map(list -> {
                                    list.sort(notificationComparator);
                                    return list;
                                })
                                .zipWith(this.countNotificationsWithEntityTypeByUserId(accountId))
                                .map(tuple -> {
                                    List<Notification> notifications = tuple.getT1();
                                    Map<String, Long> countMap = tuple.getT2();

                                    return new UserNotification(page, size, countMap, notifications);
                                })
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
                .flatMap(type -> keywordRepository
                        .findByNotificationTypeId(type.getId())
                        .collectList()
                        .map(type::keywords))
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
                    notification.setIsNew(Boolean.TRUE);
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
                        }));
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
                .map(notification -> notification.read(true).isNew(false))
                .flatMap(notificationRepository::save)
                .then();
    }

    @Override
    public Mono<Void> readAllNotifications(Integer userId) {
        return accountGrpcClientService.checkExistsAccountById(userId)
                .flatMap(existsAccount -> existsAccount
                        ? notificationRepository.findByUserId(userId)
                        .map(notification -> notification.read(true).isNew(false))
                        .flatMap(notificationRepository::save)
                        .then()
                        : Mono.error(NotificationException.INVALID_USER_ID)
                );
    }

    @Override
    public Mono<Void> viewNotification(Integer userId) {
        return accountGrpcClientService.checkExistsAccountById(userId)
                .flatMap(existsAccount -> existsAccount
                        ? notificationRepository.findByUserIdAndIsNew(userId, Boolean.TRUE)
                                .map(notification -> notification.isNew(Boolean.FALSE))
                                .flatMap(notificationRepository::save)
                                .then()
                        : Mono.error(NotificationException.INVALID_USER_ID));
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

    @Override
    public Mono<Void> removeNotification(Integer notificationId) {
        return notificationRepository.findById(notificationId)
                .switchIfEmpty(Mono.error(NotificationException.INVALID_NOTIFICATION_ID))
                .flatMap(notificationRepository::delete)
                .then();
    }

    private Flux<Notification> findAllNotifications(Integer userId, Integer limit, Integer offset) {
        String query = "SELECT * FROM notifications \n"
                + "WHERE user_id = :userId \n"
                + "ORDER BY created_at DESC \n"
                + "LIMIT :limit OFFSET :offset;";

        return databaseClient.sql(query)
                .bind("userId", userId)
                .bind("limit", limit)
                .bind("offset", offset)
                .map((row, metadata) -> mapToNotification(row))
                .all();
    }

    @SuppressWarnings("unused")
    private Mono<Long> countNotificationsByUserId(Integer userId) {
        return notificationRepository.countByUserId(userId);
    }

    private Mono<Map<String, Long>> countNotificationsWithEntityTypeByUserId(Integer userId) {
        String query = "SELECT N.user_id, COUNT(N.id) AS `count`, T.entity_type\n" +
                "FROM notifications as N\n" +
                "INNER JOIN notification_types as T\n" +
                "ON N.type_id = T.id\n" +
                "WHERE N.user_id = :userId\n" +
                "GROUP BY T.entity_type;";

        return Mono.just(new HashMap<String, Long>())
                .flatMap(resultMap -> databaseClient.sql(query)
                        .bind("userId", userId)
                        .fetch()
                        .all()
                        .map(row -> {
                            String entityType = (String) row.get("entity_type");
                            Long count = (Long) row.get("count");
                            resultMap.put(entityType, count);
                            return count;
                        })
                        .collectList()
                        .thenReturn(resultMap));
    }

    private static String buildMessage(NotificationType type, Map<String, Object> keysMap) {
        String template = type.getTemplate();
        String message = "<p>" + template + "</p>";

        if (keysMap != null) {
            for (Map.Entry<String, Object> entry : keysMap.entrySet()) {
                String keyword = "{" + entry.getKey() + "}";
                Object value = entry.getValue();

                if (template.contains(keyword)) {
                    message = message.replace(keyword, "<b>" + value.toString() + "</b>");
                }
            }
        }

        return message;
    }

    private Notification mapToNotification(Row row) {
        return Notification.builder()
                .id(row.get("id", Integer.class))
                .typeId(row.get("type_id", Integer.class))
                .userId(row.get("user_id", Integer.class))
                .entityId(row.get("entity_id", Integer.class))
                .message(row.get("message", String.class))
                .isRead(row.get("is_read", Boolean.class))
                .isNew(row.get("is_new", Boolean.class))
                .createdAt(row.get("created_at", ZonedDateTime.class))
                .build();
    }

    @SuppressWarnings("unused")
    private Notification mapToNotification(Map<String, Object> rows) {
        return Notification.builder()
                .id((Integer) rows.get("id"))
                .typeId((Integer) rows.get("type_id"))
                .userId((Integer) rows.get("user_id"))
                .entityId((Integer) rows.get("entity_id"))
                .message((String) rows.get("message"))
                .isRead((Boolean) rows.get("is_read"))
                .isNew((Boolean) rows.get("is_new"))
                .createdAt((ZonedDateTime) rows.get("created_at"))
                .build();
    }
}
