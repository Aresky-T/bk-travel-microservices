package com.aresky.reviewservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("review_statistic")
public class ReviewStatistic {
    @Id
    @Column("tour_id")
    private Integer tourId;

    @Column("number_of_reviews")
    private Integer numberOfReviews;

    @Column("stars_average")
    private Float starsAverage;
}
