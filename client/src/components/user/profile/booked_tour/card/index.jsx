import React from "react";
import { Link } from "react-router-dom";
import { IoCheckmarkDone } from "react-icons/io5";
import { PiSealWarningFill } from "react-icons/pi";

export const OPTIONS = {
  REVIEW_BOOKED_TOUR: "review-booked-tour",
  CANCEL_BOOKED_TOUR: "cancel-booked-tour",
};

const BookedTourCard = ({
  bookedTour,
  detailsBooking,
  showModal,
  isReviewed,
  isShowDetails,
  onClickShowDetail,
  onClickSubmitPayment,
  onClickHiddenDetail,
}) => {
  const isShowReviewButton = bookedTour.status === "PAY_UP";

  const isShowCancelTourButton = bookedTour.status !== "PAY_UP";

  const isShowPaymentButton = bookedTour.status === "NOT_PAY";

  const renderTouristList = (tourists) => {
    const renderGender = (gender) => {
      switch (gender) {
        case "MALE":
          return "Nam";
        case "FEMALE":
          return "Nữ";
        case "OTHER":
          return "Khác";
        default:
          return "Không xác định";
      }
    };
    const length = tourists.length;
    if (length <= 1) {
      const tourist = tourists[0];
      return (
        <ul>
          <li>
            Họ tên: <b>{tourist.fullName}</b>
          </li>
          <li>
            Ngày sinh:{" "}
            <b>{new Date(tourist.birthDate).toLocaleDateString("vi-VN")}</b>
          </li>
          <li>
            Giới tính: <b>{renderGender(tourist.gender)}</b>
          </li>
        </ul>
      );
    }

    return (
      <ol>
        {tourists.map((tourist, index) => (
          <li key={index}>
            Khách {index + 1}:
            <ul>
              <li>
                Họ tên: <b>{tourist.fullName}</b>
              </li>
              <li>
                Ngày sinh:{" "}
                <b>{new Date(tourist.birthDate).toLocaleDateString("vi-VN")}</b>
              </li>
              <li>
                Giới tính: <b>{renderGender(tourist.gender)}</b>
              </li>
            </ul>
          </li>
        ))}
      </ol>
    );
  };

  const renderPaymentStatus = (status) => {
    switch (status) {
      case "NOT_PAY":
        return "Chưa thanh toán";
      case "PAY_UP":
        return "Đã thanh toán";
      case "REJECTED":
        return "Bị từ chối";
      default:
        return "Không xác định";
    }
  };

  const renderFormOfPayment = (formOfPayment) => {
    switch (formOfPayment) {
      case "BANK_TRANSFER":
        return "Chuyển khoản";
      case "CASH_PAYMENT":
        return "Thanh toán tiền mặt";
      case "VNPAY_ON_WEBSITE":
        return "Thanh toán VNPAY khi đặt tour";
      default:
        return "Không xác định";
    }
  };

  const renderTourStatus = (status) => {
    switch (status) {
      case "NOT_STARTED":
        return "Chưa xuất phát";
      case "ON_GOING":
        return "Đang diễn ra";
      case "FINISHED":
        return "Đã hoàn thành";
      case "CANCELED":
        return "Đã bị hủy";
      default:
        return "Không xác định";
    }
  };

  const renderTransactionInfo = (transactionInfo) => {
    return (
      <ul>
        <li>
          Ngân hàng: <b>{transactionInfo.bank}</b>
        </li>
        <li>
          Loại tài khoản/thẻ khách hàng sử dụng:{" "}
          <b>{transactionInfo.cardType}</b>
        </li>
        <li>
          Nội dung thanh toán: <b>{transactionInfo.orderInfo}</b>
        </li>
        <li>
          Thời gian thanh toán:{" "}
          <b>{new Date(transactionInfo.payDate).toLocaleString("vi-VN")}</b>
        </li>
        <li>
          Mã giao dịch: <b>{transactionInfo.transactionNo}</b>
        </li>
        <li>
          Số tiền thanh toán:{" "}
          <b>{Number(transactionInfo.amount).toLocaleString("vi-VN")} VND</b>
        </li>
      </ul>
    );
  };

  return (
    <div className={`booked-tour-card${isShowDetails ? " active" : ""}`}>
      <div className="booked-tour-card__main">
        {detailsBooking.tourInfo && (
          <div className="booked-tour-card__item __image_n_title">
            <div className="booked-tour-card__image">
              <img
                src={detailsBooking.tourInfo.image || ""}
                alt="booked-tour-img1"
                loading="lazy"
              />
            </div>
            <Link
              className="booked-tour-card__item tour-title"
              to={`/tour/${bookedTour.subTourId}`}
            >
              {detailsBooking.tourInfo.title}
            </Link>
          </div>
        )}
        <div className="booked-tour-card__item tour-code">
          <span className="booked-tour-card__item__label">▶ Mã Tour:</span>
          <span className="booked-tour-card__item__content">
            {bookedTour.tourCode}
          </span>
        </div>
        <div className="booked-tour-card__item booking-code">
          <span className="booked-tour-card__item__label">▶ Mã Booking:</span>
          <span className="booked-tour-card__item__content">
            {bookedTour.bookingCode}
          </span>
        </div>
        <div className="booked-tour-card__item book-time">
          <span className="booked-tour-card__item__label">
            ▶ Thời gian đặt:
          </span>
          <span className="booked-tour-card__item__content">
            {new Date(bookedTour.bookedTime).toLocaleString("vi-VN", {
              dateStyle: "short",
              timeStyle: "short",
            })}
          </span>
        </div>
        <div className="booked-tour-card__item tour-total-price">
          <span className="booked-tour-card__item__label">▶ Tổng chi phí:</span>
          <span className="booked-tour-card__item__content">
            {Number(bookedTour.amount).toLocaleString("vi-VN")} VND
          </span>
        </div>
        <div className="booked-tour-card__item payment-status">
          <span className="booked-tour-card__item__label">
            ▶ Trạng thái thanh toán:
          </span>
          <span className="booked-tour-card__item__content">
            {renderPaymentStatus(bookedTour.status)}
          </span>
        </div>
        {bookedTour.status === "PAY_UP" && (
          <div className="booked-tour-card__item payment-status">
            <span className="booked-tour-card__item__label">
              ▶ Hình thức thanh toán:
            </span>
            <span className="booked-tour-card__item__content">
              {renderFormOfPayment(bookedTour.formOfPayment)}
            </span>
          </div>
        )}
        {detailsBooking.tourInfo && (
          <div className="booked-tour-card__item tour-status">
            <span className="booked-tour-card__item__label">
              ▶ Trạng thái Tour:
            </span>
            <span className="booked-tour-card__item__content">
              {renderTourStatus(detailsBooking.tourInfo?.status)}
            </span>
          </div>
        )}
        <div className="booked-tour-card__item representative">
          <span className="booked-tour-card__item__label">
            ▶ Người đại diện:
          </span>
          <ul>
            <li>
              Họ tên: <b>{bookedTour.fullName}</b>
            </li>
            <li>
              Email: <b>{bookedTour.email}</b>
            </li>
            <li>
              Địa chỉ: <b>{bookedTour.address}</b>
            </li>
            <li>
              Số điện thoại: <b>{bookedTour.phone}</b>
            </li>
          </ul>
        </div>
        <div className="booked-tour-card__item tourists">
          <span className="booked-tour-card__item__label">
            ▶ Danh sách hành khách ({bookedTour.totalPersons} hành khách):
          </span>
          {detailsBooking.touristList &&
            renderTouristList(detailsBooking.touristList)}
        </div>
        <div className="booked-tour-card__item note">
          <span className="booked-tour-card__item__label">▶ Lời nhắn:</span>
          <span className="booked-tour-card__item__content">
            {bookedTour.note || "trống"}
          </span>
        </div>
        {detailsBooking.transactionInfo && (
          <div className="booked-tour-card__item transaction-info">
            <span className="booked-tour-card__item__label">
              ▶ Thông tin giao dịch:
            </span>
            {renderTransactionInfo(detailsBooking.transactionInfo)}
          </div>
        )}
        <div className="booked-tour-card__item review-status">
          {isReviewed ? (
            <span className="reviewed">
              <IoCheckmarkDone size={16} />
              Đã đánh giá
            </span>
          ) : (
            <span className="non-review">
              <PiSealWarningFill size={16} />
              Chưa đánh giá
            </span>
          )}
        </div>
        {bookedTour.isCancellationRequested && (
          <div className="booked-tour-card__item cancellation-request-status">
            <span>
              <PiSealWarningFill size={16} /> Bạn đã yêu cầu huỷ bỏ chuyến đi
            </span>
          </div>
        )}
        <div className="booked-tour-card__buttons">
          {isShowCancelTourButton && (
            <button
              className="profile-btn cancel"
              onClick={() => {
                showModal(bookedTour, OPTIONS.CANCEL_BOOKED_TOUR);
              }}
            >
              Huỷ chuyến đi
            </button>
          )}
          {isShowReviewButton && (
            <button
              className="profile-btn review"
              onClick={() => {
                showModal(bookedTour, OPTIONS.REVIEW_BOOKED_TOUR);
              }}
            >
              Đánh giá
            </button>
          )}
          {isShowDetails ? (
            <button
              className="profile-btn normal"
              onClick={onClickHiddenDetail}
            >
              Ẩn
            </button>
          ) : (
            <button className="profile-btn normal" onClick={onClickShowDetail}>
              Xem chi tiết
            </button>
          )}
          {isShowPaymentButton && (
            <button
              className="profile-btn submit"
              onClick={onClickSubmitPayment}
            >
              Thanh toán
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default BookedTourCard;
