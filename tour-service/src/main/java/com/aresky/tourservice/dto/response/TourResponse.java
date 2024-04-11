package com.aresky.tourservice.dto.response;

import java.util.Date;
import com.aresky.tourservice.entity.Tour;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourResponse {
    private int id;
    private String title;
    private String duration;
    private String departureLocation;
    private String vehicle;
    private Integer totalSeats;
    private Integer adultPrice;
    private Integer childrenPrice;
    private Integer babyPrice;
    private Integer totalSubTours;
    private String createdTime;

    public static TourResponse toDTO(Tour tour) {
        return TourResponse.builder()
                .id(tour.getId())
                .title(tour.getTitle())
                .duration(tour.getDuration())
                .departureLocation(tour.getDepartureLocation())
                .totalSeats(tour.getTotalSeats())
                .vehicle(tour.getVehicle())
                .adultPrice(tour.getAdultPrice())
                .childrenPrice(tour.getChildrenPrice())
                .babyPrice(tour.getBabyPrice())
                .createdTime(Date.from(tour.getCreatedTime().toInstant()).toString())
                .build();
    }
}
