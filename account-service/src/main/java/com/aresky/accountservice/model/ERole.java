package com.aresky.accountservice.model;

import lombok.Getter;

@Getter
public enum ERole {
    USER("Người dùng"),
    ADMIN("Quản trị viên"),
    STAFF("Nhân viên");

    private final String role;

    private ERole(String role){
        this.role = role;
    }

}
