import React from "react";
import { MdStarRate } from "react-icons/md";

const ReviewItem = ({ review }) => {
  const reviewer = review.reviewer;
  const stars = review.stars ?? 0;
  const reviewTime =
    review.reviewTime &&
    new Date(review.reviewTime).toLocaleString("vi-VN", {
      dateStyle: "short",
      timeStyle: "short",
    });

  function renderStars(stars) {
    return Array(stars)
      .fill(0)
      .map((_, index) => (
        <MdStarRate key={index} size={20} color={"var(--primary-color)"} />
      ));
  }
  return (
    <div className="review-item">
      <div className="reviewer-avatar">
        <div className="image">
          <img src={reviewer.avatarUrl ?? ""} alt="" />
        </div>
      </div>
      <div className="review-info">
        <div className="review-info-item fullname">
          <div className="fullname">
            <b>{reviewer?.fullName || "Không xác định"}</b>
          </div>
        </div>
        <div className="review-info-item">
          <div className="stars">{renderStars(stars)}</div>
          <div className="review-time">{reviewTime ?? ""}</div>
        </div>
        <div className="review-info-item">
          <pre className="comment">{review.comment || "Comment trống!"}</pre>
        </div>
      </div>
    </div>
  );
};

export default ReviewItem;
