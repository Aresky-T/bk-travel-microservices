import React from "react";
import { FaLocationDot } from "react-icons/fa6";
import { GoClockFill } from "react-icons/go";
import { IoIosHourglass } from "react-icons/io";
import { HiMiniUserGroup } from "react-icons/hi2";

const TourInfo = ({ tour, handleConfirmBookingInfo }) => {
  return (
    <div className="tour-summary">
      <h3 className="tour-summary__title">Tóm tắt chuyến đi</h3>
      <div className="tour-summary__details">
        <div className="ts__details__image">
          <img src={tour?.image1} alt="" />
        </div>
        <div className="ts__details">
          <div className="ts__details__item ts__details__title">
            <span>
              <i>
                <b>{tour?.title}</b>
              </i>
            </span>
          </div>
          <div className="ts__details__item ts__details__start-address">
            <span className="ts__details__item__icon">
              <FaLocationDot />
            </span>
            <span>
              Điểm xuất phát: <b>{tour?.departureLocation}</b>
            </span>
          </div>
          <div className="ts__details__item ts__details__start-time">
            <span className="ts__details__item__icon">
              <GoClockFill />
            </span>
            <span>
              Thời gian khởi hành:{" "}
              <b>{new Date(tour?.departureTime).toLocaleString("vi-VN")}</b>
            </span>
          </div>
          <div className="ts__details__item ts__details__total-time">
            <span className="ts__details__item__icon">
              <IoIosHourglass />
            </span>
            <span>
              Tổng thời gian: <b>{tour?.duration}</b>
            </span>
          </div>
          <div className="ts__details__item ts__details__available-seats">
            <span className="ts__details__item__icon">
              <HiMiniUserGroup />
            </span>
            <span>
              Số chỗ trống: <b>{tour?.availableSeats}</b>
            </span>
          </div>
        </div>
      </div>
      <div className="book-tour--action">
        <button
          className="bt--action__btn"
          type="submit"
          onClick={handleConfirmBookingInfo}
        >
          Xác nhận thông tin
        </button>
      </div>
    </div>
  );
};

export default TourInfo;
