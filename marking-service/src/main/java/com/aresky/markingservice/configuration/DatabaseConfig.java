package com.aresky.markingservice.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties("bk-travel.database.config")
public class DatabaseConfig {
    private final Server server = new Server();

    @Data
    @NoArgsConstructor
    public static class Server {
        private String driver;
        private String protocol;
        private String hostname;
        private Integer port;
        private String url;
        private String username;
        private String password;
    }
}
