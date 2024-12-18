package com.aresky.notification_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationRequest {

    @NotNull(message = "typeName cannot be null!")
    @NotEmpty(message = "typeName cannot be empty!")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "typeName must be uppercase without spaces and can include underscores!")
    private String typeName;

    @NotNull(message = "userId cannot be null!")
    @Min(value = 0, message = "userId must be greater than zero!")
    private Integer userId;

    private Integer entityId;
    private Map<String, Object> keywords;
}
