package com.aresky.tourservice.dto.response;

import java.time.format.DateTimeFormatter;

import com.aresky.tourservice.model.SubTour;
import com.aresky.tourservice.model.Tour;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubTourResponse {
    private int id;
    private String title;
    private String tourCode;
    private String image1;
    private String duration;
    private String departureTime;
    private String departureLocation;
    private String vehicle;
    private Integer availableSeats;
    private Integer adultPrice;

    public static SubTourResponse toDTO(SubTour subTour, Tour tour) {
        return SubTourResponse.builder()
                .id(subTour.getId())
                .title(subTour.getTitle())
                .tourCode(subTour.getTourCode())
                .image1(tour.getImage1())
                .duration(tour.getDuration())
                .departureTime(DateTimeFormatter.ISO_INSTANT.format(subTour.getDepartureTime().toInstant()))
                .departureLocation(tour.getDepartureLocation())
                .vehicle(tour.getVehicle())
                .availableSeats(subTour.getAvailableSeats())
                .adultPrice(tour.getAdultPrice())
                .build();
    }
}
