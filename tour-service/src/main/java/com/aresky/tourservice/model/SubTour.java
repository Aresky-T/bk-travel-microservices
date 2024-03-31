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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sub_tour")
public class SubTour {

    @Id
    private Integer id;

    @Column("title")
    private String title;

    @Column("tour_code")
    private String tourCode;

    @Column("departure_time")
    private ZonedDateTime departureTime;

    @Column("available_seats")
    private Integer availableSeats;

    @Column("status")
    private ETourStatus status;

    @Column("tour_id")
    private Integer tourId;

    @Column("tour_guide_id")
    private Integer tourGuideId;

    @Column("created_time")
    private ZonedDateTime createdTime;
}
