package com.aresky.markingservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse {
    public static final String INVALID_ACCOUNT_ID = "Invalid accountId";
    public static final String INVALID_TOUR_ID = "Invalid tourId";
    public static final String INVALID_SUB_TOUR_ID = "Invalid subTourId";
    public static final String MARKED_THIS_TOUR = "This tour is marked!";
    public static final String NOT_MARKED_THIS_TOUR = "This tour is not marked!";
    public static final String MARKING_FAILED = "Failed mark!";
    public static final String STATISTIC_DOES_NOT_EXIST = "There are no marking statistics available for this tour!";
    public static final String INVALID_SUB_TOUR_ID_OR_ACCOUNT_ID = "Invalid accountId or subTourId!";

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
