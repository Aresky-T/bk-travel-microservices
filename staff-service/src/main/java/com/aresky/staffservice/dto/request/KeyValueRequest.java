package com.aresky.staffservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeyValueRequest {
    private String key;
    private Object value;
}
