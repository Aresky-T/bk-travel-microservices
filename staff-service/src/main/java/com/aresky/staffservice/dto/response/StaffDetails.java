package com.aresky.staffservice.dto.response;

import java.math.BigDecimal;

import com.aresky.staffservice.model.Department;
import com.aresky.staffservice.model.Job;
import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.model.Staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDetails {
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private String gender;
        private String dateOfBirth;
        private String avatarUrl;
        private String description;
        private String status;
        private String hireDate;
        private String contractUrl;
        private String accountInfo;
        private JobResponse job;

        public static StaffDetails toDTO(Staff staff) {
                return StaffDetails.builder()
                                .id(staff.getId())
                                .firstName(staff.getFirstName())
                                .lastName(staff.getLastName())
                                .avatarUrl(staff.getAvatarUrl())
                                .email(staff.getEmail())
                                .phone(staff.getPhone())
                                .address(staff.getAddress())
                                .description(staff.getDescription())
                                .gender(staff.getGender().name())
                                .dateOfBirth(staff.getDateOfBirth().toString())
                                .status(staff.getStatus().name())
                                .hireDate(staff.getHireDate().toString())
                                .contractUrl(staff.getContractUrl())
                                .accountInfo(
                                                staff.getAccountId() != null ? "Đã có tài khoản trong hệ thống"
                                                                : "Không có tài khoản trong hệ thống")
                                .build();
        }

        public static StaffDetails toDTO(Staff staff, JobResponse job) {
                StaffDetails sd = toDTO(staff);
                sd.job = job;
                return sd;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class JobResponse {
                public String position;
                public String department;
                public BigDecimal salary;
                public String startDate;
                public String endDate;
                private String status;

                public static JobResponse toDTO(Job job, Position position, Department department) {
                        return JobResponse.builder()
                                        .position(position.getName())
                                        .department(department.getName())
                                        .salary(job.getSalary())
                                        .startDate(job.getStartDate().toString())
                                        .endDate(job.getEndDate().toString())
                                        .status(job.getStatus().getValue())
                                        .build();
                }
        }
}
