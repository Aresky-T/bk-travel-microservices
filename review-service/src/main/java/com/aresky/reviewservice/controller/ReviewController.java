package com.aresky.reviewservice.controller;

import com.aresky.reviewservice.dto.response.CustomerReview;
import com.aresky.reviewservice.dto.response.StatisticResponse;
import com.aresky.reviewservice.service.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aresky.reviewservice.dto.request.ReviewForm;
import com.aresky.reviewservice.dto.response.TourReviews;

import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @GetMapping("/one-for-customer")
    public Mono<ResponseEntity<CustomerReview>> getReviewsForTour(
            @RequestParam Integer accountId,
            @RequestParam Integer subTourId) {
        return reviewService.getReviewForCustomer(accountId, subTourId).map(ResponseEntity::ok);
    }

    @GetMapping("/all-reviews-with-statistic-for-tour")
    public Mono<ResponseEntity<TourReviews>> getReviewsWithStatisticForTour(
            @RequestParam Integer tourId,
            @RequestParam Integer limit,
            @RequestParam Integer offset
    ) {
        return reviewService.getAllReviewsWithStatisticForTour(tourId, limit, offset).map(ResponseEntity::ok);
    }

    @GetMapping("/statistic")
    public Mono<ResponseEntity<StatisticResponse>> getStatisticForTour(@RequestParam Integer tourId) {
        return reviewService.getStatisticForTour(tourId).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerReview>> review(@Validated @RequestBody ReviewForm form) {
        return reviewService.review(form).map(ResponseEntity::ok);
    }

    @PatchMapping
    public Mono<ResponseEntity<?>> updateReview() {
        return Mono.just(ResponseEntity.ok("Coming soon"));
    }
}
