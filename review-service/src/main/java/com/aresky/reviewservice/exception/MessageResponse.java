package com.aresky.reviewservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse {
    public static final String INVALID_ACCOUNT_ID = "Invalid accountId";
    public static final String INVALID_TOUR_ID = "Invalid tourId";
    public static final String INVALID_SUB_TOUR_ID = "Invalid subTourId";
    public static final String INVALID_STARS = "Invalid stars";
    public static final String REVIEWED_THIS_TOUR = "This tour is reviewed!";
    public static final String REVIEW_FAILED = "Failed review!";
    public static final String STATISTIC_DOES_NOT_EXIST = "There are no review statistics available for this tour!";
    public static final String INVALID_SUB_TOUR_ID_OR_ACCOUNT_ID = "Invalid accountId or subTourId!";
    public static final String REVIEW_DOES_NOT_EXIST = "Review does not exist!";

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
