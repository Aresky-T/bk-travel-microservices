package com.aresky.tourservice.dto.response;

import java.time.format.DateTimeFormatter;

import com.aresky.tourservice.model.SubTour;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTour2Response {
    private Integer id;
    private Integer tourId;
    private String title;
    private String tourCode;
    private String departureTime;
    private Integer availableSeats;
    private String status;
    private Integer tourGuideId;
    private String createdTime;

    public static SubTour2Response toDTO(SubTour subTour) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return SubTour2Response.builder()
                .id(subTour.getId())
                .tourId(subTour.getTourId())
                .title(subTour.getTitle())
                .tourCode(subTour.getTourCode())
                .departureTime(formatter.format(subTour.getDepartureTime()))
                .availableSeats(subTour.getAvailableSeats())
                .status(subTour.getStatus() != null ? subTour.getStatus().name() : null)
                .tourGuideId(subTour.getTourGuideId())
                .createdTime(formatter.format(subTour.getCreatedTime()))
                .build();
    }
}
