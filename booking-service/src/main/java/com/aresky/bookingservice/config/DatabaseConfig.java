package com.aresky.bookingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bk-travel.config.database")
public class DatabaseConfig {

    private final Server server = new Server();

    public Server getServer() {
        return server;
    }

    public static class Server {
        private String driver = "mysql";
        private String protocol = "mysql";
        private String host = "localhost";
        private Integer port = 3306;
        private String url;
        private String database;
        private String username;
        private String password;

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
}
