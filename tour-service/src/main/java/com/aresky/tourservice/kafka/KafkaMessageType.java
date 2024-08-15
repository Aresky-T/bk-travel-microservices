package com.aresky.tourservice.kafka;

import com.aresky.tourservice.utils.JsonUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KafkaMessageType {
    @Data
    @NoArgsConstructor
    public static class NotificationRequest {
        public static final String USER_ID = "userId";
        public static final String ENTITY_ID = "entityId";
        public static final String KEYWORDS = "keywords";

        private Integer userId;
        private Integer entityId;
        private Map<String, Object> keywords;

        public NotificationRequest(Integer userId) {
            this.userId = userId;
        }

        public NotificationRequest(Integer userId, Integer entityId, Map<String, Object> keywords) {
            this.userId = userId;
            this.entityId = entityId;
            this.keywords = keywords;
        }


        public static NotificationRequest build (String jsonString){
            Set<String> filteredKey = new HashSet<>();
            filteredKey.add(USER_ID);
            filteredKey.add(ENTITY_ID);
            filteredKey.add(KEYWORDS);

            return JsonUtils.convertFromString(jsonString, NotificationRequest.class, filteredKey);
        }

        public static NotificationRequest builder(){
            return new NotificationRequest();
        }

        public NotificationRequest userId(Integer userId){
            this.userId = userId;
            return this;
        }

        public NotificationRequest entityId(Integer entityId){
            this.entityId = entityId;
            return this;
        }

        public NotificationRequest keyword(String name, Object value){
            if(this.keywords == null){
                this.keywords = new HashMap<>();
            }

            this.keywords.put(name, value);
            return this;
        }
    }
}
