package com.aresky.staffservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdateJobRequest {
    private BigDecimal salary;
    private LocalDate startDate;
    private LocalDate endDate;
}
