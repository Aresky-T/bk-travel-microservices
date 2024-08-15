import { ImageList, ImageListItem } from "@mui/material";
import React, { useEffect, useRef, useState } from "react";
import { FaOpencart } from "react-icons/fa";
import { MdStarRate } from "react-icons/md";
import { useNavigate } from "react-router-dom";

const TourDetails = ({ tour, showReviewsModal, reviewState, isBooked }) => {
  const [shouldStop, setShouldStop] = useState(false);

  const navigate = useNavigate();
  const bookingRef = useRef();

  const onClickBooking = () => {
    navigate(`/booking/${tour?.tourCode}`);
  };

  useEffect(() => {
    const handleScroll = () => {
      const scrollTop =
        window.pageYOffset || document.documentElement.scrollTop;
      setShouldStop(scrollTop > 0);
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <div className="tour-details">
      <section className="tour-details--main-info">
        <div className="tour-details-item--tour-code">
          <h3>{tour.tourCode}</h3>
        </div>
        <div className="tour-details-item--title">
          <h1>{tour?.title || "Không xác định"}</h1>
        </div>
        <div className="tour-details-item--reviews">
          {reviewState.isReviewed ? (
            <>
              <strong>
                {reviewState?.avgStars ?? 0}
                <MdStarRate color={"var(--primary-color)"} />
              </strong>
              <div>
                ({reviewState?.numberOfReviews ?? 0} lượt đánh giá)
                <div onClick={showReviewsModal} className="show-reviews-btn">
                  Xem tất cả
                </div>
              </div>
            </>
          ) : (
            <div style={{ color: "var(--primary-color)" }}>
              Chưa có đánh giá!
            </div>
          )}
        </div>
        <ImageList
          sx={{ width: "100%", height: "fit-content" }}
          variant="quilted"
          cols={2}
          rowHeight={200}
          className="tour-details-item--images"
        >
          <ImageListItem
            className="tour-details-item--images_item __image1"
            cols={1}
            rows={2}
          >
            <img src={tour?.image1} alt="image1" loading="lazy" />
          </ImageListItem>
          <ImageListItem
            className="tour-details-item--images_item __image2"
            cols={1}
            rows={1}
          >
            <img src={tour?.image2} alt="image1" loading="lazy" />
          </ImageListItem>
          <ImageListItem
            className="tour-details-item--images_item __image3"
            cols={1}
            rows={1}
          >
            <img src={tour?.image3} alt="image1" loading="lazy" />
          </ImageListItem>
          <ImageListItem
            className="tour-details-item--images_item __image4"
            cols={2}
            rows={1}
          >
            <img src={tour?.image4} alt="image1" loading="lazy" />
          </ImageListItem>
        </ImageList>
        <h3>Danh sách điểm đến: </h3>
        <div className="tour-details-item--destinations">
          <p>{tour?.destinations}</p>
        </div>
        <h3>Lịch trình tham quan</h3>
        <div className="tour-details-item--schedules">
          {React.createElement("div", {
            dangerouslySetInnerHTML: { __html: tour?.schedules },
          })}
        </div>
      </section>
      <section
        className={
          shouldStop ? "tour-details--booking __fixed" : "tour-details--booking"
        }
        ref={bookingRef}
      >
        <div className="tour-details-item--price">
          <h2>Thông tin giá</h2>
          <table className="prices-table">
            <thead>
              <tr>
                <th>Loại khách</th>
                <th>Giá tour</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Người lớn (Từ 12 tuổi trở lên)</td>
                <td>{tour?.adultPrice?.toLocaleString("en-US")} đ</td>
              </tr>
              <tr>
                <td>Trẻ em (Từ 2 - 11 tuổi)</td>
                <td>
                  {tour?.childrenPrice > 0
                    ? `${tour?.childrenPrice.toLocaleString("en-US")} đ`
                    : "Miễn phí"}
                </td>
              </tr>
              <tr>
                <td>Em bé (Dưới 2 tuổi)</td>
                <td>
                  {tour?.babyPrice > 0
                    ? `${tour?.babyPrice.toLocaleString("en-US")} đ`
                    : "Miễn phí"}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div className="dividing-line"></div>
        <div className="tour-detail__other">
          <h2>Thông tin khác</h2>
          <div className="tour-detail__other-item">
            <span className="sub-title">Tổng thời gian: </span>
            <span className="sub-content">{tour?.duration}</span>
          </div>
          <div className="tour-detail__other-item">
            <span className="sub-title">Thời gian khởi hành: </span>
            <span className="sub-content">
              {new Date(tour?.departureTime).toLocaleString("vi-VN", {
                timeStyle: "short",
                dateStyle: "short",
              })}
            </span>
          </div>
          <div className="tour-detail__other-item">
            <span className="sub-title">Địa điểm tập trung: </span>
            <span className="sub-content">
              {tour?.departureLocation || "Chưa xác định"}
            </span>
          </div>
          <div className="tour-detail__other-item">
            <span className="sub-title">Số chỗ còn trống: </span>
            <span className="sub-content">
              {tour?.availableSeats || "Đã hết chỗ trống"}
            </span>
          </div>
          <div className="tour-detail__other-item">
            <span className="sub-title">Phương tiện: </span>
            <span className="sub-content">{tour?.vehicle}</span>
          </div>
          <div className="tour-detail__other-item">
            <span className="sub-title">Dẫn đoàn: </span>
            <span className="sub-content">
              {tour?.tourGuide?.fullName || "Chưa xác định"}
            </span>
          </div>
        </div>
        <div className="dividing-line"></div>
        <div className="booking-btn">
          {!isBooked && (
            <button onClick={onClickBooking}>
              <FaOpencart /> Đặt ngay
            </button>
          )}
        </div>
      </section>
    </div>
  );
};

export default TourDetails;
