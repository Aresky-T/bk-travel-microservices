package com.aresky.tourservice.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for Database server of BK Travel
 *
 * @author Aresky
 * @since 1.0.0
 */
@Component
@ConfigurationProperties("bk-travel.config.database")
public class DatabaseConfig {
    private final Server server = new Server();

    public Server getServer() {
        return this.server;
    }

    @Data
    @NoArgsConstructor
    public static class Server {
        private String driver = "mysql";
        private String protocol = "mysql";
        private String host = "localhost";
        private Integer port = 3306;
        private String url;
        private String database;
        private String username;
        private String password;
    }

}
