package com.aresky.tourservice.dto.response;

import com.aresky.tourservice.entity.SubTour;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTourAdminResponse {
    private Integer id;
    private String title;
    private String tourCode;
    private String departureTime;
    private Integer availableSeats;
    private String status;
    private Integer tourGuideId;
    private String createdTime;

    public static SubTourAdminResponse toDTO(SubTour subTour) {
        return SubTourAdminResponse.builder()
                .id(subTour.getId())
                .title(subTour.getTitle())
                .tourCode(subTour.getTourCode())
                .departureTime(subTour.getDepartureTime().toString())
                .availableSeats(subTour.getAvailableSeats())
                .status(subTour.getStatus().name())
                .tourGuideId(subTour.getTourGuideId())
                .createdTime(subTour.getCreatedTime().toString())
                .build();
    }
}
