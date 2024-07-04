package com.aresky.notification_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table("notification_types")
public class NotificationType {
    @Id
    private Integer id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("template")
    private String template;

    @Column("entity_type")
    private EntityType entityType;

    @Transient
    private List<NotificationKeyword> keywords;

    public NotificationType(String name, String description, String template, EntityType entityType) {
        this.name = name;
        this.description = description;
        this.template = template;
        this.entityType = entityType;
        this.keywords = new ArrayList<>();
    }

    public NotificationType(Integer id, String name, String description, String template, EntityType entityType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.template = template;
        this.entityType = entityType;
        this.keywords = new ArrayList<>();
    }

    @Getter
    public static enum EntityType {
        AUTH("AUTH"),
        ACCOUNT("ACCOUNT"),
        TOUR("TOUR"),
        BOOKING("BOOKING"),
        PAYMENT("PAYMENT"),
        MAIL("MAIL"),
        OTHER("OTHER");

        private final String type;

        EntityType(String value){
            this.type = value;
        }

        public static EntityType get(String name){
            try {
                return EntityType.valueOf(name);
            } catch (IllegalArgumentException ex){
                return null;
            }
        }
    }

    public NotificationType template(String template){
        this.template = template;
        return this;
    }

    public NotificationType keywords(List<NotificationKeyword> keywords){
        this.keywords = keywords;
        return this;
    }
}
