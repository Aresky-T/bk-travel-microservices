package com.aresky.reviewservice.dto.response;

import com.aresky.reviewservice.entity.Review;
import com.aresky.reviewservice.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReview {
    private Integer id;
    private Integer stars;
    private String comment;
    private String reviewTime;
    private String editedAt;

    public static CustomerReview toDto(Review review){
        return CustomerReview.builder()
               .id(review.getId())
               .stars(review.getStars())
               .comment(review.getComment())
               .reviewTime(DateUtils.formatDateTime(review.getReviewAt()))
               .editedAt(DateUtils.formatDateTime(review.getEditedAt()))
               .build();
    }
}
