package com.aresky.staffservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("job")
public class Job {

    @Id
    @Column("id")
    private Integer id;

    @Column("staff_id")
    private Integer staffId;

    @Column("department_id")
    private Integer departmentId;

    @Column("position_id")
    private Integer positionId;

    @Column("salary")
    private BigDecimal salary;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("status")
    private EJobStatus status;
}
