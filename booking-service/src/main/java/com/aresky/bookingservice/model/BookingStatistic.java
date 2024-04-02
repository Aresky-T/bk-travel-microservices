package com.aresky.bookingservice.model;

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
@Table("booking_statistic")
public class BookingStatistic {
    @Id
    @Column("sub_tour_id")
    private Integer subTourId;

    @Column("tour_id")
    private Integer tourId;

    @Column("number_of_booking")
    private Integer numberOfBooking;
}
