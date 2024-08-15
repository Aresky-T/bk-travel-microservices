import React, { useEffect, useState } from "react";
import { BsFillPeopleFill } from "react-icons/bs";

const TourInfo = ({ tour, bookingFormik }) => {
  const [totalPrice, setTotalPrice] = useState();

  useEffect(() => {
    if (tour) {
      const adultPrice = bookingFormik.values.adultNumber * tour.price1;
      const childPrice = bookingFormik.values.childrenNumber * tour.price2;
      const babyPrice = bookingFormik.values.babyNumber * tour.price3;
      setTotalPrice(adultPrice + childPrice + babyPrice);
    }
  }, [tour, bookingFormik]);

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
          <div className="ts__details__item ts__details__start-time">
            Thời gian khởi hành:{" "}
            <b>{new Date(tour?.departureTime).toLocaleString()}</b>
          </div>
          <div className="ts__details__item ts__details__total-time">
            Tổng thời gian: <b>{tour?.duration}</b>
          </div>
          <div className="ts__details__item ts__details__available-seats">
            Số chỗ trống: <b>{tour?.availableSeats}</b>
          </div>
        </div>
      </div>
      <div className="tour-summary__tourists">
        <h4 className="tour-summary__tourists__title">Hành khách</h4>
        <div className="ts__tourists_details">
          <div>
            Tổng:
            <span>
              <b>
                {bookingFormik.values.adultNumber +
                  bookingFormik.values.childrenNumber +
                  bookingFormik.values.babyNumber}
              </b>
            </span>
            <BsFillPeopleFill />
          </div>
          <div>
            (
            <span>
              Người lớn:
              <b>{bookingFormik.values.adultNumber}</b>
            </span>
            <span>-</span>
            <span>
              Trẻ em:
              <b>{bookingFormik.values.childrenNumber}</b>
            </span>
            <span>-</span>
            <span>
              Em bé:
              <b>{bookingFormik.values.babyNumber}</b>
            </span>
            )
          </div>
        </div>
        <div className="ts__price">
          <div className="ts__price__item">
            <p className="ts__price__type">Người lớn</p>
            <span>
              {bookingFormik.values.adultNumber} x{" "}
              {tour.adultPrice?.toLocaleString("en-US")}₫
            </span>
          </div>
          <div className="ts__price__item">
            <p className="ts__price__type">Trẻ em</p>
            <span>
              {bookingFormik.values.childrenNumber} x{" "}
              {tour.childPrice?.toLocaleString("en-US")}₫
            </span>
          </div>
          <div className="ts__price__item">
            <p className="ts__price__type">Em bé</p>
            <span>
              {bookingFormik.values.babyNumber} x{" "}
              {tour.babyPrice?.toLocaleString("en-US")}₫
            </span>
          </div>
        </div>
      </div>
      <div className="book-tour--action">
        <span className="tour__total-price">
          <b>
            Tổng giá:
            <span>{totalPrice?.toLocaleString("en-US")}₫</span>
          </b>
        </span>
        <button
          className="bt--action__btn"
          type="submit"
          onClick={bookingFormik.handleSubmit}
        >
          Đặt ngay
        </button>
      </div>
    </div>
  );
};

export default TourInfo;
