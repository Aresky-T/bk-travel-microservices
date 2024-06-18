package com.aresky.reviewservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerReview {
    private Integer stars;
    private String comment;
    private String reviewTime;
}
