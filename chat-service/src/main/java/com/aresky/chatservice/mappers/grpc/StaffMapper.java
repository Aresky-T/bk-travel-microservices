package com.aresky.chatservice.mappers.grpc;

import com.aresky.chatservice.entity.EActivationStatus;
import com.aresky.chatservice.entity.Staff;

import grpc.staff.dto.response.StaffResponse;

public class StaffMapper {
    public static Staff mapStaffResponseToStaff(StaffResponse input) {
        return Staff.builder()
                .id(input.getId().getId())
                .email(input.getEmail().getEmail())
                .fullName(input.getLastname().getLastName() + " " + input.getFirstname().getFistName())
                .avatarUrl(input.getAvatarUrl().getAvatarUrl())
                .status(EActivationStatus.OFFLINE)
                .build();
    }
}
