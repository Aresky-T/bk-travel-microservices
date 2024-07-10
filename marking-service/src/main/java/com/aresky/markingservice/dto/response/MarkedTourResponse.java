package com.aresky.markingservice.dto.response;

import com.aresky.markingservice.entity.MarkedTour;
import com.aresky.markingservice.utils.DateUtils;
import grpc.tour.dto.response.SubTourResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkedTourResponse {
    private Integer id;
    private Integer accountId;
    private Integer subTourId;
    private String markedAt;
    private SubTour subTour;

    public static MarkedTourResponse toDto(MarkedTour markedTour){
        return MarkedTourResponse.builder()
               .id(markedTour.getId())
               .accountId(markedTour.getAccountId())
               .subTourId(markedTour.getSubTourId())
               .markedAt(DateUtils.formatDateTime(markedTour.getMarkedAt()))
               .build();
    }

    public MarkedTourResponse subTour(SubTour subTour){
        this.subTour = subTour;
        return this;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubTour {
        private Integer id;
        private Integer tourId;
        private String title;
        private String tourCode;
        private String image1;
        private String duration;
        private String departureTime;
        private String departureLocation;
        private String vehicle;
        private Integer availableSeats;
        private Integer adultPrice;

        public static SubTour fromGrpcSubTourResponse(SubTourResponse input){
            return SubTour.builder()
                   .id(input.getId())
                   .tourId(input.getTourId())
                   .title(input.getTitle())
                   .tourCode(input.getTourCode())
                   .image1(input.getImage1())
                   .duration(input.getDuration())
                   .departureTime(input.getDepartureTime())
                   .departureLocation(input.getDepartureLocation())
                   .vehicle(input.getVehicle())
                   .availableSeats(input.getAvailableSeats())
                   .adultPrice(input.getAdultPrice())
                   .build();
        }
    }
}
