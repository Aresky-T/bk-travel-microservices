import React from "react";
import logo from "../../../../assets/logo/png/logo-color.png";
import {
  differenceInDays,
  differenceInHours,
  differenceInMinutes,
  differenceInSeconds,
  differenceInWeeks,
  differenceInYears,
} from "date-fns";

const NotificationItem = ({ notification, onClickNotification }) => {
  const isRead = notification.isRead;
  const createdAt = notification.createdAt;

  const renderCreatedAt = () => {
    const currentTime = new Date();
    const createdTime = new Date(createdAt);
    const seconds = differenceInSeconds(currentTime, createdTime);
    const minutes = differenceInMinutes(currentTime, createdTime);

    if (seconds < 60) {
      return `${minutes} giây trước`;
    }

    if (minutes < 60) {
      return `${minutes} phút trước`;
    }

    const hours = differenceInHours(currentTime, createdTime);
    if (hours < 24) {
      return `${hours} giờ trước`;
    }

    const days = differenceInDays(currentTime, createdTime);
    if (days < 7) {
      return `${days} ngày trước`;
    }

    const years = differenceInYears(currentTime, createdTime);
    if (years >= 1) {
      return `${years} năm trước`;
    }

    return `${differenceInWeeks(currentTime, createdTime)} tuần trước`;
  };

  return (
    <div
      className={`notification-popup-item notification ${isRead ? "read" : ""}`}
      onClick={() => onClickNotification(notification)}
    >
      <div className="notification-image">
        <img src={logo} alt="logo" />
        {!isRead && <div className="notification-status"></div>}
      </div>
      <div className="notification-content">
        <div className="notification-content_msg">
          {React.createElement("div", {
            dangerouslySetInnerHTML: { __html: notification.message },
          })}
        </div>
        <div className="notification-content_created-at">
          <div>
            {renderCreatedAt()}
            {isRead && " | Đã đọc"}
          </div>
        </div>
      </div>
    </div>
  );
};

export default NotificationItem;
