package com.aresky.staffservice.model;

import java.time.LocalDate;

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
    private LocalDate dateOfBirth;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("address")
    private String address;

    @Column("description")
    private String description;

    @Column("hire_date")
    private LocalDate hireDate;

    @Column("contract_url")
    private String contractUrl;

    @Column("status")
    private EStaffStatus status;

    @Column("account_id")
    private Integer accountId;
}
