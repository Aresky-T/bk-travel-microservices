package com.aresky.reviewservice.dto.request;

import com.aresky.reviewservice.entity.Review;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewForm {
    private Integer accountId;
    private Integer subTourId;
    private Integer stars;
    private String comment;

    public Review buildEntity() {
        return Review.builder()
                .accountId(accountId)
                .subTourId(subTourId)
                .stars(stars)
                .comment(comment)
                .build();
    }
}
