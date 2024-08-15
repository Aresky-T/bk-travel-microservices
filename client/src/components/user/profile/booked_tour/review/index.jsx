import React, { useEffect, useState } from "react";
import { IoCloseOutline } from "react-icons/io5";
import { PiSealWarningFill } from "react-icons/pi";
import Rating from "./Rating";
import Comment from "./Comment";
import { toast } from "react-hot-toast";
import { OPTIONS } from "../card";
import { getReviewApi, reviewApi } from "../../../../../api/user/review.api";

const ReviewBookedTour = ({
  type,
  bookedTour,
  hiddenModal,
  isShow,
  onRefetchBookings,
}) => {
  const [review, setReview] = useState();
  const [comment, setComment] = useState("");
  const [currentStars, setCurrentStars] = useState(0);
  const [hoverStars, setHoverStars] = useState(undefined);
  const [isReviewed, setIsReviewed] = useState(false);

  const stars = Array(5).fill(0);
  const isActive = type === OPTIONS.REVIEW_BOOKED_TOUR && isShow;

  const handleChangeComment = (event) => {
    setComment(event.target.value);
  };

  const handleHiddenModal = () => {
    resetReview();
    hiddenModal();
  };

  const handleMouseOver = (newHoverValue) => {
    setHoverStars(newHoverValue);
  };

  const handleMouseLeave = () => {
    setHoverStars(undefined);
  };

  const handleClick = (value) => {
    setCurrentStars(value);
  };

  const resetReview = () => {
    setComment(review?.comment ?? "");
    setCurrentStars(review?.stars ?? 0);
  };

  const handleSubmitReview = () => {
    if (!bookedTour) {
      toast.error("Thiếu thông tin booking!");
      return;
    }

    if (currentStars < 1 || comment.trim() === "") {
      toast.error("Dữ liệu chưa hợp lệ!");
      return;
    }

    const formData = {
      accountId: bookedTour.accountId,
      subTourId: bookedTour.subTourId,
      stars: currentStars,
      comment: comment,
    };

    const loading = toast.loading("Đang xử lý");

    reviewApi(formData)
      .then((res) => {
        setTimeout(() => {
          setReview(res.data);
          onRefetchBookings();

          toast.dismiss(loading);
          const message = isReviewed
            ? "Bạn đã cập nhật đánh giá thành công!"
            : "Cảm ơn quý khách đã gửi đánh giá tới BK TRAVEL!";
          toast.success(message, { position: "top-center" });
        }, 1500);
      })
      .catch((err) => {
        setTimeout(() => {
          toast.dismiss(loading);
          toast.error("Đánh giá không thành công, vui lòng thử lại!", {
            duration: 1000,
          });
        }, 1500);
      });
  };

  useEffect(() => {
    function checkReviewedTour(booking) {
      const { accountId, subTourId } = booking;
      getReviewApi(accountId, subTourId)
        .then((res) => {
          const data = res.data;
          setIsReviewed(true);
          setReview(data);
          setComment(data.comment);
          setCurrentStars(data.stars);
        })
        .catch((err) => {});
    }

    if (bookedTour) {
      checkReviewedTour(bookedTour);
    }
  }, [bookedTour]);

  useEffect(() => {
    if (review) {
      setCurrentStars(review.stars);
      setComment(review.comment);
    }
  }, [review]);

  return (
    <div
      className={`${OPTIONS.REVIEW_BOOKED_TOUR} modal-container ${
        isActive ? "active" : ""
      }`}
    >
      <div className="modal">
        <div className="modal-close">
          <button onClick={handleHiddenModal}>
            <IoCloseOutline />
          </button>
        </div>
        <header className="modal__header">
          <strong>
            <span className="icon">
              <PiSealWarningFill />
            </span>
            Đánh giá Tour
          </strong>
        </header>
        <div className="modal__main scroll-container">
          <div className="modal__main-item">
            <h2 className="modal-title">
              <span>#</span> Tour:{" "}
              <u style={{ color: "#000" }}>
                <i>{bookedTour?.tourCode ?? "Không xác định"}</i>
              </u>
            </h2>
          </div>
          <div className="modal__main-item">
            <Rating
              stars={stars}
              currentValue={currentStars}
              hoverValue={hoverStars}
              handleMouseOver={handleMouseOver}
              handleMouseLeave={handleMouseLeave}
              handleClick={handleClick}
            />
          </div>
          <div className="modal__main-item">
            <Comment
              comment={comment}
              handleChangeComment={handleChangeComment}
            />
          </div>
        </div>
        <div className="modal__footer">
          <div className="buttons-area">
            <button className="profile-btn cancel" onClick={handleHiddenModal}>
              Hủy
            </button>
            <button className="profile-btn submit" onClick={handleSubmitReview}>
              {isReviewed ? "Cập nhật đánh giá" : "Gửi đánh giá"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ReviewBookedTour;
