package com.aresky.touristattractionservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FieldRequest {
    private String key;
    private Object value;
}
