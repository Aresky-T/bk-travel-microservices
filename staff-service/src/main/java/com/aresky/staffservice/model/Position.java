package com.aresky.staffservice.model;

import java.math.BigDecimal;

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
@Table("position")
public class Position {
    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("basic_salary")
    private BigDecimal basicSalary;
}
