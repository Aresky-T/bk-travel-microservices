package com.aresky.staffservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffFilter {
    private String name;
    private String email;
    private String phone;
    private Integer statusId;
}
