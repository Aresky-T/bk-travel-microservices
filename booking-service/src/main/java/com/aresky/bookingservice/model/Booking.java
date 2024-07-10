package com.aresky.bookingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
@Table("booking")
public class Booking {

    @Id
    private Integer id;

    @Column("booking_code")
    private String bookingCode;

    @Column("account_id")
    private Integer accountId;

    @Column("tour_id")
    private Integer tourId;

    @Column("sub_tour_id")
    private Integer subTourId;

    @Column("tour_code")
    private String tourCode;

    @Column("full_name")
    private String fullName;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("address")
    private String address;

    @Column("adult_number")
    private Integer adultNumber;

    @Column("children_number")
    private Integer childrenNumber;

    @Column("baby_number")
    private Integer babyNumber;

    @Column("note")
    private String note;

    @Column("amount")
    private Integer amount;

    @Column("booked_time")
    private ZonedDateTime bookedTime;

    @Column("status")
    private EBookingStatus status;

    @Column("cancellation_requested")
    private Boolean isCancellationRequested;

    @Column("form_of_payment")
    private EFormOfPayment formOfPayment;

    @Transient
    private CancellationRequested cancellationRequested;
}
