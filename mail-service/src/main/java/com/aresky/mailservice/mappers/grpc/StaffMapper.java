package com.aresky.mailservice.mappers.grpc;

import com.aresky.mailservice.entity.Staff;
import grpc.staff.dto.response.StaffResponse;

public class StaffMapper {
    public static Staff mapToStaff(StaffResponse response){
        return Staff.builder()
                .id(response.getId().getId())
                .email(response.getEmail().getEmail())
                .fullName(response.getLastname().getLastName() + " " + response.getFirstname().getFistName())
                .avatarUrl(response.getAvatarUrl().getAvatarUrl())
                .permission(Boolean.TRUE)
                .build();
    }
}
