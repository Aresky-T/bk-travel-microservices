package com.aresky.staffservice.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aresky.staffservice.model.Department;
import com.aresky.staffservice.model.Staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDetails {
    private Integer id;
    private String name;
    private String description;
    private Integer numberOfStaffs;
    private ManagerResponse manager;
    private List<StaffResponse> staffs = new ArrayList<>();

    public DepartmentDetails(Integer id, String name, String description, Integer numberOfStaffs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfStaffs = numberOfStaffs;
    }

    public DepartmentDetails(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
        this.numberOfStaffs = department.getNumberOfStaffs();
    }

    public static DepartmentDetails toDTO(Department department) {
        return new DepartmentDetails(department);
    }

    public static DepartmentDetails toDTO(Department department, Staff manager, List<Staff> staffs) {
        DepartmentDetails dto = new DepartmentDetails(department);

        if (manager != null) {
            dto.manager = ManagerResponse.toDTO(manager);
        }

        if (!staffs.isEmpty()) {
            dto.staffs = staffs.stream().map(StaffResponse::toDTO).collect(Collectors.toList());
        }

        return dto;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ManagerResponse {
        private Integer id;
        private String firstName;
        private String lastName;
        private String avatarUrl;
        private String email;
        private String phone;
        private String gender;
        private String dateOfBirth;

        public static ManagerResponse toDTO(Staff staff){
            return ManagerResponse.builder()
                    .id(staff.getId())
                    .firstName(staff.getFirstName())
                    .lastName(staff.getLastName())
                    .avatarUrl(staff.getFirstName())
                    .email(staff.getEmail())
                    .phone(staff.getPhone())
                    .gender(staff.getGender().name())
                    .dateOfBirth(staff.getDateOfBirth().toString())
                    .build();
        }
    }
}
