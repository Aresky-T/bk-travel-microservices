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

                String[] chatPaths = {
                                "/api/v1/chat/**"
                };

                String[] mailPaths = {
                                "/api/v1/mail/**"
                };

                String[] markingPaths = {
                                "/api/v1/marking/**"
                };

                String[] notificationPaths = {
                                "/api/v1/notifications/**"
                };

                this.registerRoute(
                                "auth-service",
                                "lb://auth-service",
                                authPaths);
                this.registerRoute(
                                "account-service",
                                "lb://account-service",
                                accountPaths);
                this.registerRoute(
                                "tour-service",
                                "lb://tour-service",
                                tourPaths);
                this.registerRoute(
                                "booking-service",
                                "lb://booking-service",
                                bookingPaths);
                this.registerRoute(
                                "staff-service",
                                "lb://staff-service",
                                staffPaths);
                this.registerRoute(
                                "tourist-attraction-service",
                                "lb://tourist-attraction-service",
                                touristAttractionPaths);
                this.registerRoute("payment-service",
                                "lb://payment-service",
                                paymentPaths);
                this.registerRoute(
                                "chat-service",
                                "lb://chat-service",
                                chatPaths);
                this.registerRoute("mail-service",
                                "lb://mail-service",
                                mailPaths);
                this.registerRoute("marking-service",
                                "lb://marking-service",
                                markingPaths);
                this.registerRoute("notification-service",
                                "lb://notification-service",
                                notificationPaths);
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
