package com.aresky.tourservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourFilter {
    public static final String DEPARTURE_LOCATION = "departureLocation";
    public static final String DESTINATION = "destination";
    public static final String VEHICLE = "vehicle";
    public static final String MIN_PRICE = "minPrice";
    public static final String MAX_PRICE = "maxPrice";


    private String departureLocation;
    private String destination;
    private String vehicle;
    private Integer minPrice;
    private Integer maxPrice;
}
