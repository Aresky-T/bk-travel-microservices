package com.aresky.notification_service.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemplateUpdateRequest {
    private Integer typeId;
    private String template;
}
