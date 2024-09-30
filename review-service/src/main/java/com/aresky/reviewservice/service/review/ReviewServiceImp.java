package com.aresky.reviewservice.service.review;

import com.aresky.reviewservice.dto.response.StatisticResponse;
import com.aresky.reviewservice.entity.Review;
import com.aresky.reviewservice.entity.ReviewStatistic;
import com.aresky.reviewservice.entity.Reviewer;
import com.aresky.reviewservice.exception.MessageResponse;
import com.aresky.reviewservice.exception.ReviewException;
import com.aresky.reviewservice.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import com.aresky.reviewservice.dto.request.ReviewForm;
import com.aresky.reviewservice.dto.response.CustomerReview;
import com.aresky.reviewservice.dto.response.TourReviews;
import com.aresky.reviewservice.repository.IReviewRepository;
import com.aresky.reviewservice.repository.IReviewStatisticRepository;
import com.aresky.reviewservice.repository.IReviewerRepository;
import com.aresky.reviewservice.service.account.IAccountService;
import com.aresky.reviewservice.service.tour.ITourService;

import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
public class ReviewServiceImp implements IReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    @Autowired
    private IReviewerRepository reviewerRepository;

    @Autowired
    private IReviewStatisticRepository statisticRepository;

    @Autowired
    private ITourService tourService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private DatabaseClient databaseClient;

    @Transactional
    @Override
    public Mono<CustomerReview> review(ReviewForm form) {
        Integer accountId = form.getAccountId();
        Integer subTourId = form.getSubTourId();
        Integer stars = form.getStars();
        String comment = form.getComment();

        Mono<Reviewer> reviewerMono = existsReviewerByAccountId(accountId)
                .flatMap(existsReviewer -> existsReviewer
                        ? getReviewerByAccountId(accountId)
                        : createReviewerIfNotExists(accountId)
                );

        return reviewerMono
                .map(Reviewer::getId)
                .flatMap(reviewerId -> existsReviewByReviewerIdIdAndSubTourId(reviewerId, subTourId)
                        .flatMap(existsReview -> {
                            if(existsReview){
                                Mono<Review> currentReviewMono = getReviewByReviewerIdAndSubTourId(reviewerId, subTourId);
                                return updateReview(currentReviewMono, stars, comment);
                            }

                            return createAndSaveReview(reviewerId, subTourId, stars, comment);
                        }))
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.REVIEW_FAILED)))
                .map(CustomerReview::toDto);
    }

    @Override
    public Mono<TourReviews> getAllReviewsWithStatisticForTour(Integer tourId, Integer limit, Integer offset) {
        return tourService.checkExistsTourById(tourId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.INVALID_TOUR_ID)))
                .then(getStatisticForReview(tourId))
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.STATISTIC_DOES_NOT_EXIST)))
                .flatMap(statistic -> getAllReviewsByTourId(tourId, limit, offset)
                        .flatMap(review -> getReviewerById(review.getReviewerId())
                                .map(reviewer -> {
                                    review.setReviewer(reviewer);
                                    return review;
                                }))
                        .collectList()
                        .map(reviews -> TourReviews.toDto(statistic, reviews)));
    }

    @Override
    public Mono<CustomerReview> getReviewForCustomer(Integer accountId, Integer subTourId) {
        return getReviewerByAccountId(accountId)
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.INVALID_ACCOUNT_ID)))
                .map(Reviewer::getId)
                .flatMap(reviewerId -> getReviewByReviewerIdAndSubTourId(reviewerId, subTourId)
                        .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.REVIEW_DOES_NOT_EXIST)))
                        .map(CustomerReview::toDto));
    }

    @Override
    public Mono<StatisticResponse> getStatisticForTour(Integer tourId) {
        return statisticRepository.findByTourId(tourId)
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.STATISTIC_DOES_NOT_EXIST)))
                .map(StatisticResponse::toDto);
    }

    @Override
    public Mono<Boolean> existsReviewByAccountIdAndSubTourId(Integer accountId, Integer subTourId) {
        return reviewerRepository.findByAccountId(accountId)
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.INVALID_ACCOUNT_ID)))
                .map(Reviewer::getId)
                .flatMap(reviewerId -> reviewRepository.existsByReviewerIdAndSubTourId(reviewerId, subTourId));
    }

    private Mono<Review> createAndSaveReview(Integer reviewerId, Integer subTourId, Integer stars, String comment) {
        return tourService.getSubTourById(subTourId)
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.INVALID_SUB_TOUR_ID)))
                .flatMap(subTour -> {
                    Review review = Review.builder()
                            .reviewerId(reviewerId)
                            .subTourId(subTour.getId())
                            .tourId(subTour.getTourId())
                            .stars(stars)
                            .comment(comment)
                            .reviewAt(DateUtils.now())
                            .editedAt(DateUtils.now())
                            .build();

                    return reviewRepository.save(review);
                });
    }

    private Mono<Review> updateReview(Mono<Review> reviewMono, Integer newStars, String newComment){
        return reviewMono.flatMap(review -> {
            review.setStars(newStars);
            review.setComment(newComment);
            review.setEditedAt(DateUtils.now());
            return reviewRepository.save(review);
        });
    }

    private Flux<Review> getAllReviewsByTourId(Integer tourId, Integer limit, Integer offset){
        String query = "SELECT * FROM review WHERE tour_id = :tourId LIMIT :limit OFFSET :offset;";
        return databaseClient.sql(query)
                .bind("tourId", tourId)
                .bind("limit", limit)
                .bind("offset", offset)
                .fetch()
                .all()
                .map(row -> Review.builder()
                        .id((Integer) row.get("id"))
                        .reviewerId((Integer) row.get("reviewer_id"))
                        .subTourId((Integer) row.get("sub_tour_id"))
                        .tourId((Integer) row.get("tour_id"))
                        .stars((Integer) row.get("stars"))
                        .comment((String) row.get("comment"))
                        .reviewAt((ZonedDateTime) row.get("review_at"))
                        .editedAt((ZonedDateTime) row.get("edited_at"))
                        .build());
    }

    private Mono<Reviewer> getReviewerById(Integer reviewerId){
        return reviewerRepository.findById(reviewerId);
    }

    private Mono<Reviewer> getReviewerByAccountId(Integer accountId){
        return reviewerRepository.findByAccountId(accountId);
    }

    private Mono<Review> getReviewByReviewerIdAndSubTourId(Integer reviewerId, Integer subTourId){
        return reviewRepository.findByReviewerIdAndSubTourId(reviewerId, subTourId);
    }

    private Mono<ReviewStatistic> getStatisticForReview(Integer tourId){
        return statisticRepository.findByTourId(tourId);
    }

    private Mono<Reviewer> createReviewerIfNotExists(Integer accountId) {
        return accountService.checkExistsAccountById(accountId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new ReviewException(MessageResponse.INVALID_ACCOUNT_ID)))
                .flatMap(existsAccount -> accountService.getProfileById(accountId))
                .flatMap(profile -> createReviewer(accountId, profile.getFullName(), profile.getAvatarUrl()));
    }


    private Mono<Reviewer> createReviewer(Integer accountId, String fullName, String avatarUrl){
        return reviewerRepository.save(new Reviewer(fullName, avatarUrl, accountId));
    }

    private Mono<Boolean> existsReviewerByAccountId(Integer accountId){
        return reviewerRepository.existsByAccountId(accountId);
    }

    private Mono<Boolean> existsReviewByReviewerIdIdAndSubTourId(Integer reviewerId, Integer subTourId){
        return reviewRepository.existsByReviewerIdAndSubTourId(reviewerId, subTourId);
    }

}
