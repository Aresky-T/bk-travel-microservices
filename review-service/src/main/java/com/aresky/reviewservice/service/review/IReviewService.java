package com.aresky.reviewservice.service.review;

import com.aresky.reviewservice.dto.request.ReviewForm;
import com.aresky.reviewservice.dto.response.CustomerReview;
import com.aresky.reviewservice.dto.response.StatisticResponse;
import com.aresky.reviewservice.dto.response.TourReviews;

import reactor.core.publisher.Mono;

public interface IReviewService {
    Mono<CustomerReview> review(ReviewForm form);

    Mono<TourReviews> getAllReviewsWithStatisticForTour(Integer tourId, Integer limit, Integer offset);

    Mono<CustomerReview> getReviewForCustomer(Integer accountId, Integer subTourId);

    Mono<StatisticResponse> getStatisticForTour(Integer tourId);

    Mono<Boolean> existsReviewByAccountIdAndSubTourId(Integer accountId, Integer subTourId);
}
