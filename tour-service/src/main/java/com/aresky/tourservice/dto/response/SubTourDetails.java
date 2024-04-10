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
@NoArgsConstructor
@AllArgsConstructor
public class SubTourDetails {
    private int id;
    private int tourId;
    private String title;
    private String tourTitle;
    private String tourCode;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String destinations;
    private String duration;
    private String departureTime;
    private String departureLocation;
    private String schedules;
    private String vehicle;
    private String status;
    private Integer totalSeats;
    private Integer availableSeats;
    private Integer adultPrice;
    private Integer childrenPrice;
    private Integer babyPrice;
    private Integer tourGuideId;
    private String createdTime;

    public static SubTourDetails toDTO(SubTour subTour, Tour tour) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return SubTourDetails.builder()
                .id(subTour.getId())
                .tourId(tour.getId())
                .title(subTour.getTitle())
                .tourTitle(tour.getTitle())
                .tourCode(subTour.getTourCode())
                .image1(tour.getImage1())
                .image2(tour.getImage2())
                .image3(tour.getImage3())
                .image4(tour.getImage4())
                .destinations(tour.getDestinations())
                .duration(tour.getDuration())
                .departureTime(formatter.format(subTour.getDepartureTime().toInstant()))
                .departureLocation(tour.getDepartureLocation())
                .schedules(tour.getSchedules())
                .vehicle(tour.getVehicle())
                .status(subTour.getStatus().name())
                .totalSeats(tour.getTotalSeats())
                .availableSeats(subTour.getAvailableSeats())
                .adultPrice(tour.getAdultPrice())
                .childrenPrice(tour.getChildrenPrice())
                .babyPrice(tour.getBabyPrice())
                .tourGuideId(subTour.getTourGuideId())
                .createdTime(formatter.format(subTour.getCreatedTime().toInstant()))
                .build();
    }
}
