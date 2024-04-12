package com.aresky.tourservice.dto.request;

import com.aresky.tourservice.entity.Tour;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourCreateForm {
    @NotNull(message = "Title can not be null!")
    private String title;

    @NotNull
    private String image1;

    @NotNull
    private String image2;

    @NotNull
    private String image3;

    @NotNull
    private String image4;

    @NotNull
    private String destinations;

    @NotNull
    private String duration;

    @NotNull
    private String departureLocation;

    @NotNull
    private String schedules;

    @NotNull
    private String vehicle;

    @NotNull
    private Integer totalSeats;

    @NotNull
    private Integer adultPrice;

    @NotNull
    private Integer childrenPrice;

    @NotNull
    private Integer babyPrice;

    public static Tour toEntity(TourCreateForm form) {
        return Tour.builder()
                .title(form.title)
                .image1(form.image1)
                .image2(form.image2)
                .image3(form.image3)
                .image4(form.image4)
                .destinations(form.destinations)
                .duration(form.duration)
                .departureLocation(form.departureLocation)
                .schedules(form.schedules)
                .vehicle(form.vehicle)
                .totalSeats(form.totalSeats)
                .adultPrice(form.adultPrice)
                .childrenPrice(form.childrenPrice)
                .babyPrice(form.babyPrice)
                .build();
    }
}
