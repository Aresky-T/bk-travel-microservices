package com.aresky.staffservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentUpdateForm {
    private String name;
    private String description;
}
