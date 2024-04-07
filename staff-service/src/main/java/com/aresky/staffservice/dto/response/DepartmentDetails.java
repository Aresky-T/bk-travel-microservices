package com.aresky.staffservice.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aresky.staffservice.model.Department;
import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.model.Staff;

import lombok.AllArgsConstructor;
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
    private List<PositionResponse> positions = new ArrayList<>();
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

    public static DepartmentDetails toDTO(Department department, List<Staff> staffs, List<Position> positions) {
        DepartmentDetails dto = new DepartmentDetails(department);

        if (!positions.isEmpty()) {
            dto.positions = positions.stream().map(PositionResponse::toDTO).collect(Collectors.toList());
        }

        if (!staffs.isEmpty()) {
            dto.staffs = staffs.stream().map(StaffResponse::toDTO).collect(Collectors.toList());
        }

        return dto;
    }
}
