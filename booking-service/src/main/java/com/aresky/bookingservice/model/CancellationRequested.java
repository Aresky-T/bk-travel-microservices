package com.aresky.bookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cancellation_requested")
public class CancellationRequested {
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
