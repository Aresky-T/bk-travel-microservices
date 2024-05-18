package com.aresky.staffservice.dto.request;

import com.aresky.staffservice.model.EStaffStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffFilter {
    private String name;
    private String email;
    private String phone;
    private EStaffStatus status;
}
