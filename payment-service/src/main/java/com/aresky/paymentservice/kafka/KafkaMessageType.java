package com.aresky.paymentservice.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class KafkaMessageType {
    @Data
    @NoArgsConstructor
    public static class NotificationRequest {
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
