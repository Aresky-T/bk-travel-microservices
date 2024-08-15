import React from "react";
import { FaCode } from "react-icons/fa6";
import { IoTimerOutline } from "react-icons/io5";
import { MdOutlineAirlineSeatReclineExtra } from "react-icons/md";
import { GrStatusGoodSmall } from "react-icons/gr";
import { GiTimeSynchronization } from "react-icons/gi";
import { PiPersonSimpleThrowBold } from "react-icons/pi";
import { Button } from "@mui/material";

const SubTourDetailsCard = ({ subTour, onSelectSubTour, onDeleteSubTour }) => {
  const renderStatus = (status) => {
    switch (status) {
      case "HIDDEN":
        return "Ẩn";
      case "NOT_STARTED":
        return "Chưa bắt đầu";
      case "ON_GOING":
        return "Đã khởi hành";
      case "FINISHED":
        return "Đã hoàn thành";
      case "BE_DELAY":
        return "Bị trì hoãn";
      case "CANCELED":
        return "Đã bị hủy";
      default:
        return "Không xác định";
    }
  };

  return (
    <div className="sub-tour-details-card--admin">
      <div className="sub-tour-details-card__border"></div>
      <div className="sub-tour-details-card__content">
        <div className="sub-tour-details-admin__item title">
          <span>{subTour.title || "NONE"}</span>
        </div>
        <div className="sub-tour-details-admin__item tour-code">
          <span className="icon">
            <FaCode />
          </span>
          <span className="data">
            <strong>{subTour.tourCode}</strong>
          </span>
        </div>
        <div className="sub-tour-details-admin__item departure-time">
          <span className="icon">
            <GiTimeSynchronization />
          </span>
          <span className="data">
            Thời gian khởi hành:{" "}
            <strong>
              {new Date(subTour.departureTime)
                .toLocaleString("vi-VN", {
                  dateStyle: "short",
                  timeStyle: "short",
                })
                .toUpperCase()}
            </strong>
          </span>
        </div>
        <div className="sub-tour-details-admin__item available-seats">
          <span className="icon">
            <MdOutlineAirlineSeatReclineExtra />
          </span>
          <span className="data">
            Số chỗ còn trống: <strong>{subTour.availableSeats}</strong>
          </span>
        </div>
        <div className="sub-tour-details-admin__item status">
          <span className="icon">
            <GrStatusGoodSmall />
          </span>
          <span className="data">
            Trạng thái: <strong>{renderStatus(subTour.status)}</strong>
          </span>
        </div>
        <div className="sub-tour-details-admin__item status">
          <span className="icon">
            <PiPersonSimpleThrowBold />
          </span>
          <span className="data">
            Dẫn đoàn: <strong>{subTour.tourGuideId ?? "không xác định"}</strong>
          </span>
        </div>
        <div className="sub-tour-details-admin__item created-time">
          <span className="icon">
            <IoTimerOutline />
          </span>
          <span className="data">
            Ngày tạo:{" "}
            <strong>
              {new Date(subTour.createdTime)
                .toLocaleString("vi-VN", {
                  dateStyle: "short",
                  timeStyle: "short",
                })
                .toUpperCase()}
            </strong>
          </span>
        </div>
      </div>
      <div className="sub-tour-details-cart__action-area">
        <Button
          className="admin-btn admin-btn--submit"
          onClick={() => onSelectSubTour(subTour)}
        >
          Cập nhật
        </Button>
        <Button
          className="admin-btn admin-btn--delete"
          onClick={() => onDeleteSubTour(subTour)}
        >
          Xóa
        </Button>
      </div>
    </div>
  );
};

export default SubTourDetailsCard;
