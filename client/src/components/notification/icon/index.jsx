import React from "react";
import { useNotification } from "../../../redux/selector";
import { IoNotificationsSharp } from "react-icons/io5";
import { useDispatch } from "react-redux";
import {
  onHiddenNotificationPopup,
  onShowNotificationPopup,
} from "../../../redux/slices/notification.slice";
import { useLocation } from "react-router-dom";
import { ROUTE } from "../../../constant/route";

const NotificationIcon = () => {
  const notification = useNotification();
  const dispatch = useDispatch();
  const location = useLocation();
  const isNotificationPath = location.pathname === ROUTE.NOTIFICATION;

  const isShowPopup = notification.isShowPopup;
  const newNotificationCount = notification.newNotificationCount;
  const isActive = isShowPopup || isNotificationPath;

  const handleClickIcon = () => {
    console.log({ isNotificationPath });
    if (isNotificationPath) {
      return;
    }

    isShowPopup
      ? dispatch(onHiddenNotificationPopup())
      : dispatch(onShowNotificationPopup());

    // eslint-disable-next-line
  };

  return (
    <div
      className={`notification-icon ${isActive ? "active" : ""}`}
      onClick={handleClickIcon}
    >
      <IoNotificationsSharp />
      {newNotificationCount > 0 && (
        <div className="new-notification-count">{newNotificationCount}</div>
      )}
    </div>
  );
};

export default NotificationIcon;
