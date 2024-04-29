package com.aresky.accountservice.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("profile")
public class Profile implements Serializable {

    @Id
    private Integer id;

    @Column("account_id")
    private int accountId;

    @Column("avatar_url")
    private String avatarUrl;

    @Column("full_name")
    private String fullName;

    @Column("address")
    private String address;

    @Column("phone")
    private String phone;

    @Column("date_of_birth")
    private LocalDate dateOfBirth;

    @Column("gender")
    private EGender gender;

    public Profile(int accountId) {
        this.accountId = accountId;
    }

    public Profile(Account account) {
        this.accountId = account.getId();
    }
}
