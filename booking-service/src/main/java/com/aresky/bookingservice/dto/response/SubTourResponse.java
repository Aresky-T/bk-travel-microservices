package com.aresky.bookingservice.dto.response;

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
}
