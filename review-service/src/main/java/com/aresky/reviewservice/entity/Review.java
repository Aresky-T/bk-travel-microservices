package com.aresky.reviewservice.entity;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
@Table("review")
public class Review {
    @Id
    @Column("id")
    private Integer id;

    @Column("reviewer_id")
    private Integer reviewerId;

    @Column("tour_id")
    private Integer tourId;

    @Column("sub_tour_id")
    private Integer subTourId;

    @Column("stars")
    private Integer stars;

    @Column("comment")
    private String comment;

    @Column("review_at")
    private ZonedDateTime reviewAt;

    @Column("edited_at")
    private ZonedDateTime editedAt;

    @Transient
    private Reviewer reviewer;
}
