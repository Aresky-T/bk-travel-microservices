package com.aresky.staffservice.dto.request;

import com.aresky.staffservice.model.EJobStatus;
import com.aresky.staffservice.model.Job;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateJobRequest {
    private Integer staffId;
    private Integer departmentId;
    private Integer positionId;
    private BigDecimal salary;
    private LocalDate startDate;
    private LocalDate endDate;

    public static Job toJob(CreateJobRequest dto) {
        return Job.builder()
                .staffId(dto.staffId)
                .departmentId(dto.departmentId)
                .positionId(dto.positionId)
                .salary(dto.salary)
                .startDate(dto.startDate)
                .endDate(dto.endDate)
                .status(EJobStatus.CURRENT_JOB)
                .build();
    }
}
