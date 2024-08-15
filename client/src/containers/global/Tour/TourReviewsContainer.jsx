import React, { useCallback } from "react";
import ReviewItem from "../../../components/global/Tour/review/ReviewItem";
import TourReviews from "../../../components/global/Tour/review";

const TourReviewsContainer = ({ tour, reviewState, hiddenModal }) => {
  const isShowReviews = reviewState.isShowModal;
  const { avgStars, numberOfReviews, reviewList } = reviewState;

  const renderReviewItems = useCallback(() => {
    if (reviewList.length === 0) {
      return <>Không có lượt đánh giá nào!</>;
    }

    return reviewList.map((item, index) => {
      return <ReviewItem review={item} key={index} />;
    });
  }, [reviewList]);

  return (
    <TourReviews
      isShowReviews={isShowReviews}
      tour={tour}
      avgStars={avgStars}
      numberOfReviews={numberOfReviews}
      renderReviewItems={renderReviewItems}
      hiddenReviewModal={hiddenModal}
    />
  );
};

export default TourReviewsContainer;
