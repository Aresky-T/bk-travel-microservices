package com.aresky.staffservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("position")
public class Position {
    @Id
    @Column("id")
    private Integer id;

    @Column("department_id")
    private Integer departmentId;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("headcount")
    private Integer headcount;

    public Position(String name, String description, Integer departmentId, Integer headcount) {
        this.name = name;
        this.description = description;
        this.departmentId = departmentId;
        this.headcount = headcount;
    }
}
