import React, { useCallback } from "react";
import { FaOpencart, FaRegClock } from "react-icons/fa";
import { BsHourglassSplit } from "react-icons/bs";
import { BiMap } from "react-icons/bi";
import { useNavigate } from "react-router-dom";
import { IoBus } from "react-icons/io5";
import { MdOutlinePeopleAlt } from "react-icons/md";
import { useAuth } from "../../../../redux/selector";
import MarkingTourButton from "./MarkingTourButton";
import { ROLE } from "../../../../constant/role";

const TourCard = ({ tour, isMarked, onClickBooking, onClickMarkingButton }) => {
  const auth = useAuth();
  const accountId = auth.id;
  const role = auth.role;
  const navigate = useNavigate();

  const renderMarkingButton = useCallback(() => {
    if (!accountId || role !== ROLE.USER) return;
    return (
      <MarkingTourButton onClick={onClickMarkingButton} isMarked={isMarked} />
    );

    //eslint-disable-next-line
  }, [accountId, role, isMarked]);

  return (
    <div className="tour-card">
      {renderMarkingButton()}
      <div className="tour-card__image">
        <img src={tour.image1} alt="" />
        <span className="price">
          {tour.adultPrice.toLocaleString("en-US")}₫
        </span>
      </div>
      <div className="tour-card__main">
        <div className="tour-card__info">
          <div
            className="tour-card__title"
            onClick={() => {
              navigate(`/tour/${tour.id}`);
            }}
          >
            <p>{tour.title}</p>
          </div>
          <div className="tour-card-item">
            <p
              className="text"
              style={{
                fontWeight: "bold",
                color: "var(--primary-color)",
              }}
            >
              <span className="content">{tour.tourCode}</span>
            </p>
          </div>
          <div className="tour-card-item">
            <p className="text">
              <span className="tour-card__subtitle">
                <BsHourglassSplit />
              </span>
              <span className="content">{tour.duration}</span>
            </p>
          </div>
          <div className="tour-card-item">
            <p className="text">
              <span className="tour-card__subtitle">
                <FaRegClock /> Thời gian khởi hành:
              </span>
              <span className="content">
                {new Date(tour.departureTime).toLocaleString("vi-VN", {
                  timeStyle: "short",
                  dateStyle: "short",
                })}
              </span>
            </p>
          </div>
          <div className="tour-card-item">
            <p className="text">
              <span className="tour-card__subtitle">
                <BiMap /> Nơi khởi hành:
              </span>
              <span className="content">{tour.departureLocation}</span>
            </p>
          </div>
          <div className="tour-card-item">
            <p className="text">
              <span className="tour-card__subtitle">
                <IoBus /> Phuơng tiện di chuyển:
              </span>
              <span className="content">{tour.vehicle}</span>
            </p>
          </div>
          <div className="tour-card-item">
            <p className="text">
              <span className="tour-card__subtitle">
                <MdOutlinePeopleAlt /> Số chỗ còn trống:
              </span>
              <span className="content available-seats">
                {tour.availableSeats}
              </span>
            </p>
          </div>
        </div>
        <div className="tour-card__action">
          <div className="action_item booking">
            <button onClick={onClickBooking}>
              {" "}
              <FaOpencart /> Đặt ngay
            </button>
          </div>
          <div
            className="action_item detail"
            onClick={() => {
              navigate(`/tour/${tour.id}`);
            }}
          >
            <button>Xem chi tiết</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TourCard;
