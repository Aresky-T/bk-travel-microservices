package com.aresky.tourservice.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.entity.Tour;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourDetails {
    private int id;
    private String title;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String destinations;
    private String duration;
    private String departureLocation;
    private String schedules;
    private String vehicle;
    private Integer totalSeats;
    private Integer adultPrice;
    private Integer childrenPrice;
    private Integer babyPrice;
    private Integer totalSubTours;
    private String createdTime;
    private final List<SubTourResponse> subTours = new ArrayList<>();

    public static TourDetails toDTO(Tour tour) {
        return TourDetails.builder()
                .id(tour.getId())
                .title(tour.getTitle())
                .destinations(tour.getDestinations())
                .duration(tour.getDuration())
                .departureLocation(tour.getDepartureLocation())
                .totalSeats(tour.getTotalSeats())
                .schedules(tour.getSchedules())
                .vehicle(tour.getVehicle())
                .adultPrice(tour.getAdultPrice())
                .childrenPrice(tour.getChildrenPrice())
                .babyPrice(tour.getBabyPrice())
                .image1(tour.getImage1())
                .image2(tour.getImage2())
                .image3(tour.getImage3())
                .image4(tour.getImage4())
                .totalSubTours(tour.getTotalSubTours())
                .createdTime(tour.getCreatedTime().toString())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubTourResponse {
        private int id;
        private String title;
        private String tourCode;
        private String departureTime;
        private String status;
        private Integer availableSeats;
        private Integer tourGuideId;
        private String createdTime;

        public static SubTourResponse toDTO(SubTour subTour) {
            return SubTourResponse.builder()
                    .id(subTour.getId())
                    .title(subTour.getTitle())
                    .tourCode(subTour.getTourCode())
                    .departureTime(subTour.getDepartureTime().toString())
                    .availableSeats(subTour.getAvailableSeats())
                    .status(subTour.getStatus().name())
                    .createdTime(subTour.getCreatedTime().toString())
                    .build();
        }
    }
}
