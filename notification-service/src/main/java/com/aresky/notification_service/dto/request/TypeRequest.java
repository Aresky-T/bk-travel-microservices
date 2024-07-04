package com.aresky.notification_service.dto.request;

import com.aresky.notification_service.entity.NotificationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TypeRequest {

    @NotNull(message = "Name must not be null!")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Name must be uppercase without spaces and can include underscores!")
    private String name;

    @NotNull(message = "Description must not be null!")
    @NotEmpty(message = "Description must not be empty!")
    private String description;

    @NotNull(message = "Template must not be null")
    @NotEmpty(message = "Template must not be empty")
    // @Pattern(regexp = "(?:\\{([^}]*)})?", message = "Template must contain keywords within curly braces or be empty!")
    private String template;

    @NotNull(message = "Type must not be null!")
    private NotificationType.EntityType entityType;

    public static NotificationType toNotificationType(TypeRequest dto){
        return new NotificationType(dto.name, dto.description, dto.template, dto.entityType);
    }
}
