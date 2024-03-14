package com.aresky.authservice.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "Auth")
public class Auth {

    public Auth(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = ERole.USER;
    }

    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private ERole role;
    private Date createdTime;
    private Date updatedTime;
}
