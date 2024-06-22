package com.aresky.markingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("marked_tours_statistics")
public class MarkedTourStatistic {

    @Id
    private Integer id;

    @Column("tour_id")
    private Integer tourId;

    @Column("sub_tour_id")
    private Integer subTourId;

    @Column("marks")
    private Integer marks;

    @Column("month")
    private Integer month;

    @Column("year")
    private Integer year;
}
