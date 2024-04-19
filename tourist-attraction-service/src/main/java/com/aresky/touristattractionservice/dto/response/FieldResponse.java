package com.aresky.touristattractionservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FieldResponse {
    private String key;
    private Object value;

    public FieldResponse(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
