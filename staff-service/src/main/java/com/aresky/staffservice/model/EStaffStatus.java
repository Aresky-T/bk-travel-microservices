package com.aresky.staffservice.model;

import lombok.Getter;

public enum EStaffStatus {
    ACTIVE("Đang làm việc"),
    INACTIVE("Ngừng hoạt động"),
    ON_LEAVE("Đang nghỉ phép"),
    CONTRACT("Hợp đồng"),
    TERMINATED("Đã thôi việc"),
    RETIRED("Đã nghỉ hưu"),
    PROBATION("Thử việc"),
    TRAINING("Đào tạo"),
    SUSPENDED("Đình chỉ công việc"),
    TRANSFERRED("Đã chuyển công tác");

    @Getter
    private final String value;

    private EStaffStatus(String value) {
        this.value = value;
    }
}
