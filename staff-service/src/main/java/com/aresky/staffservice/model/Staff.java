package com.aresky.staffservice.model;

import java.math.BigDecimal;
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
@Table("staff")
public class Staff {

    @Id
    private Integer id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("avatar_url")
    private String avatarUrl;

    @Column("gender")
    private EGender gender;

    @Column("date_of_birth")
    private ZonedDateTime dateOfBirth;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("address")
    private String address;

    @Column("description")
    private String description;

    @Column("basic_salary")
    private BigDecimal basicSalary;

    @Column("started_date")
    private ZonedDateTime startedDate;

    @Column("contract_url")
    private String contractUrl;

    @Column("account_id")
    private Integer accountId;

    @Column("department_id")
    private Integer departmentId;

    @Column("position_id")
    private Integer positionId;

    @Column("status_id")
    private Integer statusId;
}
