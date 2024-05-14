package com.aresky.chatservice.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bk-travel.database.config")
public class DatabaseConfig {
    private final Server server = new Server();

    public Server getServer() {
        return server;
    }

    @Data
    @NoArgsConstructor
    public static class Server {
        private String hostname;
        private String url;
        private String username;
        private String password;
        private String driver;
        private String protocol;
        private Integer port;
    }
}
