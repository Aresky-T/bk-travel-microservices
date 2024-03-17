package com.aresky.accountservice.model;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    private Long id;

    @Column("avatar_url")
    private String avatarUrl;

    @Column("full_name")
    private String fullName;

    @Column("address")
    private String address;

    @Column("phone")
    private String phone;

    @Column("date_of_birth")
    private ZonedDateTime dateOfBirth;

    @Column("gender")
    private EGender gender;

    @Column("auth_id")
    private int authId;

    @Column("created_time")
    private ZonedDateTime createdTime;

    @Column("updated_time")
    private ZonedDateTime updatedTime;
}
