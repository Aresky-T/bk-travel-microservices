package com.aresky.tourservice.dto.request;

import com.aresky.tourservice.entity.Tour;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class TourCreateForm {
    @NotNull(message = "Title can not be null!")
    private String title;

    @NotNull
    private MultipartFile image1;

    @NotNull
    private MultipartFile image2;

    @NotNull
    private MultipartFile image3;

    @NotNull
    private MultipartFile image4;

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
                .destinations(form.destinations)
                .duration(form.duration)
                .departureLocation(form.departureLocation)
                .schedules(form.schedules)
                .vehicle(form.vehicle)
                .totalSeats(form.totalSeats)
                .adultPrice(form.adultPrice)
                .childrenPrice(form.childrenPrice)
                .babyPrice(form.babyPrice)
                .totalSubTours(0)
                .build();
    }
}
