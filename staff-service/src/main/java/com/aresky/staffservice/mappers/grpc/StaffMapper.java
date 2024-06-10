package com.aresky.staffservice.mappers.grpc;

import com.aresky.staffservice.model.Staff;
import grpc.staff.constants.type.StaffGender;
import grpc.staff.constants.type.StaffStatus;
import grpc.staff.dto.response.StaffResponse;
import grpc.staff.fields.*;

public class StaffMapper {
    public static StaffResponse mapStaffToStaffResponse(Staff staff){
        return StaffResponse.newBuilder()
                .setId(StaffIdField.newBuilder().setId(staff.getId()).build())
                .setEmail(StaffEmailField.newBuilder().setEmail(staff.getEmail()).build())
                .setFirstname(StaffFirstnameField.newBuilder().setFistName(staff.getFirstName()).build())
                .setLastname(StaffLastnameField.newBuilder().setLastName(staff.getLastName()).build())
                .setGender(StaffGender.valueOf(staff.getGender().name()))
                .setStatus(StaffStatus.valueOf(staff.getStatus().name()))
                .setAvatarUrl(StaffAvatarUrlField.newBuilder().setAvatarUrl(staff.getAvatarUrl()).build())
                .build();
    }
}
