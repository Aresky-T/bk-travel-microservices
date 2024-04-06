package com.aresky.staffservice.dto.request;

import com.aresky.staffservice.model.Department;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentCreateForm {
    private String name;
    private String description;

    public static Department toEntity(DepartmentCreateForm dto) {
        return Department.builder()
                .name(dto.name)
                .description(dto.description)
                .build();
    }
}
