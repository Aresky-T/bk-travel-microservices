package com.aresky.gatewayservice.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomService {
    private String id;
    private String[] paths;
    private String uri;

    public CustomService(String id, String[] paths, String uri) {
        this.id = id;
        this.paths = paths;
        this.uri = uri;
    }
}
