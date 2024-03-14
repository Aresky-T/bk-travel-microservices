package com.aresky.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private Integer id;
    private String token;
    private String type;
    private String username;
    private String email;
    private String role;
    private String status;

    public LoginResponse(Integer id, String token, String type, String username, String email, String role,
            String status) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
