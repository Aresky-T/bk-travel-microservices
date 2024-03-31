package com.aresky.tourservice.dto.request;

import java.time.ZoneId;
import java.util.Date;

import com.aresky.tourservice.model.ETourStatus;
import com.aresky.tourservice.model.SubTour;
import com.aresky.tourservice.model.Tour;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourUpdateForm {
    private int id;
    private int subTourId;
    private String title;
    private String subTourTitle;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String destinations;
    private String duration;
    private Date departureTime;
    private String departureLocation;
    private String schedules;
    private String vehicle;
    private ETourStatus status;
    private Integer totalSeats;
    private Integer availableSeats;
    private Integer adultPrice;
    private Integer childrenPrice;
    private Integer babyPrice;
    private String createdTime;
    private Integer tourGuideId;

    public static Tour buildTour(TourUpdateForm form) {
        return Tour.builder()
                .id(form.id)
                .title(form.title)
                .destinations(form.destinations)
                .duration(form.duration)
                .departureLocation(form.departureLocation)
                .schedules(form.schedules)
                .vehicle(form.vehicle)
                .totalSeats(form.totalSeats)
                .adultPrice(form.adultPrice)
                .childrenPrice(form.childrenPrice)
                .babyPrice(form.babyPrice)
                .image1(form.image1)
                .image2(form.image2)
                .image3(form.image3)
                .image4(form.image4)
                .build();
    }

    public static SubTour buildSubTour(TourUpdateForm form) {
        return SubTour.builder()
                .id(form.subTourId)
                .title(form.subTourTitle)
                .availableSeats(form.availableSeats)
                .departureTime(form.departureTime.toInstant().atZone(ZoneId.systemDefault()))
                .status(form.status)
                .tourGuideId(form.tourGuideId)
                .build();
    }
}
