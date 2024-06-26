package com.aresky.bookingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tourist")
public class Tourist {

    @Id
    private Integer id;

    @Column("booking_id")
    private Integer bookingId;

    @Column("full_name")
    private String fullName;

    @Column("birth_date")
    private LocalDate birthDate;

    @Column("gender")
    private EGender gender;

    @Column("type")
    private ETouristType type;
}