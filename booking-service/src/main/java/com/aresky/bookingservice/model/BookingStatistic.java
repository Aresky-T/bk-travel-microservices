package com.aresky.bookingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("booking_statistic")
public class BookingStatistic {
    @Id
    private Integer Id;

    @Column("sub_tour_id")
    private Integer subTourId;

    @Column("tour_id")
    private Integer tourId;

    @Column("number_of_bookings")
    private Integer numberOfBookings;

    @Column("successful_bookings")
    private Integer successfulBookings;

    @Column("cancelled_bookings")
    private Integer cancelledBookings;

    @Column("month")
    private Integer month;

    @Column("year")
    private Integer year;

    @Column("updated_time")
    private ZonedDateTime updatedTime;
}
