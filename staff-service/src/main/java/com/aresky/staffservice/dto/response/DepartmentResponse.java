package com.aresky.staffservice.dto.response;

import com.aresky.staffservice.model.Department;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer numberOfStaffs;

    public DepartmentResponse(Integer id, String name, String description, Integer numberOfStaffs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfStaffs = numberOfStaffs;
    }

    public static DepartmentResponse toDTO(Department department) {
        return new DepartmentResponse(department.getId(), department.getName(), department.getDescription(),
                department.getNumberOfStaffs());
    }
}
