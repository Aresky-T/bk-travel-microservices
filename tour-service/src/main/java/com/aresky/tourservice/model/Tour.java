package com.aresky.tourservice.model;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("tour")
public class Tour {

    @Id
    private Integer id;

    @Column("title")
    private String title;

    @Column("destinations")
    private String destinations;

    @Column("duration")
    private String duration;

    @Column("departure_location")
    private String departureLocation;

    @Column("total_seats")
    private Integer totalSeats;

    @Column("vehicle")
    private String vehicle;

    @Column("schedules")
    private String schedules;

    @Column("adult_price")
    private Integer adultPrice;

    @Column("children_price")
    private Integer childrenPrice;

    @Column("baby_price")
    private Integer babyPrice;

    @Column("image1")
    private String image1;

    @Column("image2")
    private String image2;

    @Column("image3")
    private String image3;

    @Column("image4")
    private String image4;

    @Column("total_sub_tours")
    private Integer totalSubTours;

    @Column("created_time")
    private ZonedDateTime createdTime;

}
