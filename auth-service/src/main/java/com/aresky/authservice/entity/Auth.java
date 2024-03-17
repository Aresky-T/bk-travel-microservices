package com.aresky.authservice.entity;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "auth")
public class Auth {

    public Auth(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = ERole.USER;
        this.status = EStatus.ACTIVE;
    }

    @Id
    private Long id;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("role")
    private ERole role;

    @Column("status")
    private EStatus status;

    @Column(value = "created_time")
    private ZonedDateTime createdTime;

    @Column(value = "updated_time")
    private ZonedDateTime updatedTime;
}
