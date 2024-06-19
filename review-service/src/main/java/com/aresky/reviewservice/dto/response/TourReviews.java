package com.aresky.reviewservice.dto.response;

import java.util.List;
import java.util.Optional;

import com.aresky.reviewservice.entity.Review;
import com.aresky.reviewservice.entity.ReviewStatistic;
import com.aresky.reviewservice.utils.DateUtils;
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

    public static TourReviews toDto(ReviewStatistic statistic, List<Review> reviews){
        return TourReviews.builder()
                .tourId(statistic.getTourId())
                .numberOfReviews(statistic.getNumberOfReviews())
                .avgStars(statistic.getStarsAverage())
                .reviewList(reviews.stream().map(ReviewItem::toDto).toList())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewItem {
        private Integer id;
        private Integer stars;
        private String comment;
        private String reviewAt;
        private String editedAt;
        private ReviewerResponse reviewer;

        public static ReviewItem toDto(Review review){
            ReviewerResponse reviewerResponse = Optional.ofNullable(review.getReviewer())
                    .map(ReviewerResponse::new)
                    .orElse(null);

            return ReviewItem.builder()
                    .id(review.getId())
                    .stars(review.getStars())
                    .comment(review.getComment())
                    .reviewAt(DateUtils.formatDateTime(review.getReviewAt()))
                    .editedAt(DateUtils.formatDateTime(review.getEditedAt()))
                    .reviewer(reviewerResponse)
                    .build();
        }
    }
}
