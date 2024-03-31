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

        this.registerRoute("auth-service", "lb://auth-service", authPaths);
        this.registerRoute("account-service", "lb://account-service", accountPaths);
        this.registerRoute("tour-service", "lb://tour-service", tourPaths);
        this.registerRoute("tourist-attraction-service", "lb://tourist-attraction-service", touristAttractionPaths);
        this.registerRoute("booking-service", "lb://booking-service", bookingPaths);
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
