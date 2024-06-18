package com.aresky.reviewservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.reviewservice.dto.request.ReviewForm;
import com.aresky.reviewservice.dto.response.TourReviews;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @GetMapping("/one-for-customer")
    public Mono<ResponseEntity<TourReviews>> getReviewsForTour(
            @RequestParam Integer accountId,
            @RequestParam Integer tourId) {
        return null;
    }

    @GetMapping("/all-for-tour")
    public Mono<ResponseEntity<TourReviews>> getReviewsForTour(@RequestParam Integer tourId) {
        return null;
    }

    @GetMapping("/statistic")
    public Mono<ResponseEntity<?>> getStatisticForTour(@RequestParam Integer tourId) {
        return null;
    }

    @PostMapping
    public Mono<ResponseEntity<?>> review(@RequestBody ReviewForm form) {
        return null;
    }

    @PatchMapping
    public Mono<ResponseEntity<?>> updateReview() {
        return null;
    }
}
