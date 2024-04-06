package com.aresky.staffservice.dto.request;

import com.aresky.staffservice.model.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusCreateForm {
    private String name;
    private String description;

    public static Status toStatus(StatusCreateForm dto) {
        return new Status(dto.name, dto.description);
    }
}
