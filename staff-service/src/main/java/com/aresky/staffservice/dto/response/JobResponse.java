package com.aresky.staffservice.dto.response;

import com.aresky.staffservice.model.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    private Integer id;
    private Integer staffId;
    private Integer departmentId;
    private Integer positionId;
    private BigDecimal salary;
    private String startDate;
    private String endDate;

    public static JobResponse toDTO(Job job){
        return JobResponse.builder()
                .id(job.getId())
                .staffId(job.getStaffId())
                .departmentId(job.getDepartmentId())
                .positionId(job.getPositionId())
                .salary(job.getSalary())
                .startDate(job.getStartDate().toString())
                .endDate(job.getEndDate().toString())
                .build();
    }
}
