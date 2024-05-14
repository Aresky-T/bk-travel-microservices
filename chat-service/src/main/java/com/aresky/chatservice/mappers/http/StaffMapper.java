package com.aresky.chatservice.mappers.http;

import com.aresky.chatservice.dto.response.StaffResponse;
import com.aresky.chatservice.entity.Staff;

public class StaffMapper {
    public static StaffResponse mapToStaffResponse(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .status(staff.getStatus().name())
                .build();
    }
}
