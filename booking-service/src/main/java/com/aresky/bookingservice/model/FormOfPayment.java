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
@Table("form_of_payment")
public class FormOfPayment {
    @Id
    private Integer id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;
}
