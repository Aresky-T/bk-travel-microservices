package com.aresky.authservice.dto;

import com.aresky.authservice.entity.Auth;

public class SignupForm {
    private String username;
    private String email;
    private String password;

    public Auth toEntity() {
        return new Auth(username, email, password);
    }
}
