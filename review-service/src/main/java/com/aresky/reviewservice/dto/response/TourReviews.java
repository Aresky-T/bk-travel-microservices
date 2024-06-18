package com.aresky.reviewservice.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourReviews {
    private int tourId;
    private int numberOfReviews;
    private float avgStars;
    private List<ReviewItem> reviewList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewItem {
        private Integer id;
        private Integer stars;
        private String comment;
        private String reviewAt;
        private Reviewer reviewer;
    }
}
