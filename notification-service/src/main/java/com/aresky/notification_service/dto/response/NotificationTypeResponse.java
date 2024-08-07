package com.aresky.notification_service.dto.response;

import com.aresky.notification_service.entity.NotificationKeyword;
import com.aresky.notification_service.entity.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTypeResponse {
    private Integer id;
    private String name;
    private String description;
    private String template;
    private String entityType;
    private List<Keyword> keywords;

    public static NotificationTypeResponse fromNotificationType(NotificationType notificationType) {
        if (notificationType == null)
            return null;

        NotificationTypeResponse response = NotificationTypeResponse.builder()
                .id(notificationType.getId())
                .name(notificationType.getName())
                .description(notificationType.getDescription())
                .template(notificationType.getTemplate())
                .entityType(notificationType.getEntityType().name())
                .keywords(new ArrayList<>())
                .build();

        boolean isNullKeywords = notificationType.getKeywords() != null;
        if (isNullKeywords) {
            response.keywords = notificationType.getKeywords().stream().map(Keyword::fromNotificationKeyword).toList();
        }

        return response;
    }

    @Data
    @NoArgsConstructor
    public static class Keyword {
        private Integer id;
        private String keyword;

        public Keyword(Integer id, String keyword) {
            this.id = id;
            this.keyword = keyword;
        }

        public static Keyword fromNotificationKeyword(NotificationKeyword keyword) {
            return new Keyword(keyword.getId(), keyword.getKeyword());
        }
    }
}
