package com.aresky.mailservice.mappers.http;

import com.aresky.mailservice.dto.response.StaffResponse;
import com.aresky.mailservice.entity.Staff;

public class StaffMapper {
    public static StaffResponse toStaffResponse(Staff staff){
        if(staff == null) return null;

        return StaffResponse.builder()
                .id(staff.getId())
                .email(staff.getEmail())
                .fullName(staff.getFullName())
                .avatarUrl(staff.getAvatarUrl())
                .permission(staff.getPermission())
                .build();
    }
}
