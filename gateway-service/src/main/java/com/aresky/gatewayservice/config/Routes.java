package com.aresky.gatewayservice.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Routes {
        private final Map<String, Route> routes = new HashMap<>();

        @PostConstruct
        private void registerRoutes() {
                String[] authPaths = {
                                "/api/v1/auth/**"
                };

                String[] accountPaths = {
                                "/api/v1/accounts/**"
                };

                String[] tourPaths = {
                                "/api/v1/tours/**"
                };

                String[] touristAttractionPaths = {
                                "/api/v1/tourist-attraction/**"
                };

                String[] bookingPaths = {
                                "/api/v1/bookings/**"
                };

                String[] staffPaths = {
                                "/api/v1/staffs/**",
                                "/api/v1/departments/**",
                                "/api/v1/positions/**"
                };

                String[] paymentPaths = {
                                "/api/v1/payment/**"
                };

                this.registerRoute(
                                "auth-service",
                                "http://auth-service:8081",
                                authPaths);
                this.registerRoute(
                                "account-service",
                                "http://account-service:8082",
                                accountPaths);
                this.registerRoute(
                                "tour-service",
                                "http://tour-service:8083",
                                tourPaths);
                this.registerRoute(
                                "booking-service",
                                "http://booking-service:8084",
                                bookingPaths);
                this.registerRoute(
                                "staff-service",
                                "http://staff-service:8085",
                                staffPaths);
                this.registerRoute(
                                "tourist-attraction-service",
                                "http://tourist-attraction-service:8086",
                                touristAttractionPaths);
                this.registerRoute("payment-service",
                                "http://payment-service:8090",
                                paymentPaths);
        }

        public Route getRoute(String id) {
                return this.routes.get(id);
        }

        private void registerRoute(String id, String uri, String[] paths) {
                if (!routes.containsKey(id)) {
                        this.routes.put(id, new Route(id, uri, paths));
                }
        }

        @Data
        @NoArgsConstructor
        public static class Route {
                private String id;
                private String uri;
                private String[] paths;

                public Route(String id, String uri, String[] paths) {
                        this.id = id;
                        this.uri = uri;
                        this.paths = paths;
                }
        }
}
