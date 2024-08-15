import React from "react";
import { IoCloseOutline } from "react-icons/io5";
import { MdStarRate } from "react-icons/md";
import { PiSealWarningFill } from "react-icons/pi";

const TourReviews = ({
  tour,
  avgStars,
  numberOfReviews,
  renderReviewItems,
  isShowReviews,
  hiddenReviewModal,
}) => {
  return (
    <div
      className={`tour-reviews-container ${
        isShowReviews ? "active" : ""
      } modal-container`}
    >
      <div className="modal">
        <div className="modal-close">
          <button onClick={hiddenReviewModal}>
            <IoCloseOutline />
          </button>
        </div>
        <header className="modal__header">
          <strong>
            <span className="icon">
              <PiSealWarningFill />
            </span>
            Đánh giá
          </strong>
        </header>
        <div className="modal__main scroll-container">
          <div className="modal__main-item">
            <h2 className="modal-title">
              <span>#</span> Tour:{" "}
              <u style={{ color: "#000" }}>
                <i>{tour?.title ?? "Không xác định"}</i>
              </u>
            </h2>
            <h2 className="modal-title">
              <div className="avg-stars">
                <strong>
                  {avgStars ?? 0}
                  <MdStarRate color={"var(--primary-color)"} />
                </strong>
                <p>({numberOfReviews ?? 0} lượt đánh giá)</p>
              </div>
            </h2>
          </div>
          <div className="modal__main-item">{renderReviewItems()}</div>
        </div>
      </div>
    </div>
  );
};

export default TourReviews;
