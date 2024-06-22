package com.aresky.markingservice.entity;

import com.aresky.markingservice.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Table("marked_tours")
public class MarkedTour {
    @Id
    private Integer id;

    @Column("account_id")
    private Integer accountId;

    @Column("sub_tour_id")
    private Integer subTourId;

    @Column("tour_id")
    private Integer tourId;

    @Column("marked_at")
    private ZonedDateTime markedAt;

    public MarkedTour(Integer accountId, Integer subTourId, Integer tourId) {
        this.accountId = accountId;
        this.subTourId = subTourId;
        this.tourId = tourId;
        this.markedAt = DateUtils.now();
    }
}
