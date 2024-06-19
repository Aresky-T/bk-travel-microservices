package com.aresky.reviewservice.dto.response;

import com.aresky.reviewservice.entity.ReviewStatistic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticResponse {
    private int tourId;
    private int numberOfReviews;
    private float avgStars;

    public static StatisticResponse toDto(ReviewStatistic statistic){
        return StatisticResponse.builder()
                .tourId(statistic.getTourId())
                .numberOfReviews(statistic.getNumberOfReviews())
                .avgStars(statistic.getStarsAverage())
                .build();
    }
}
