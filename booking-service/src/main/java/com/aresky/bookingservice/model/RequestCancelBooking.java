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
@Table("request_cancel_booking")
public class RequestCancelBooking {
    @Id
    private Integer id;

    @Column("booking_id")
    private Integer bookingId;

    @Column("reason")
    private String reason;

    @Column("status")
    private ERequestStatus status;

    @Column("created_time")
    private ZonedDateTime createdTime;
}
