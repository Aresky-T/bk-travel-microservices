package com.aresky.accountservice.model;

import java.io.Serializable;
import java.time.Clock;
import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "account")
public class Account implements Serializable {

    @Id
    private Integer id;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("role")
    private ERole role;

    @Column("activation_status")
    private EActivationStatus activationStatus;

    @Column("online_status")
    private EOnlineStatus onlineStatus;

    @Column("created_time")
    private ZonedDateTime createdTime;

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = ERole.USER;
        this.activationStatus = EActivationStatus.ACTIVE;
        this.onlineStatus = EOnlineStatus.OFFLINE;
        this.createdTime = ZonedDateTime.now(Clock.systemDefaultZone());
    }
}
