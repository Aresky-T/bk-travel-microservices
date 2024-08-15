import React, { useCallback, useEffect, useRef, useState } from "react";
import { useAuth, useNotification } from "../../../redux/selector";
import NotificationItem from "../../../components/notification/popup/items";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import {
  getNotificationsThunk,
  onHiddenNotificationPopup,
  onIncreaseNotificationPage,
  onViewNotifications,
} from "../../../redux/slices/notification.slice";
import { ROUTE } from "../../../constant/route";
import { viewNotificationApi } from "../../../api/notification/notification.api";
// import { TbTriangleFilled } from "react-icons/tb";

const TYPE_ALL = "all";
const TYPE_UNREAD = "unread";

const TYPE_BUTTONS = [
  {
    name: "Tất cả",
    value: TYPE_ALL,
    active: false,
  },
  {
    name: "Chưa đọc",
    value: TYPE_UNREAD,
    active: false,
  },
];

const NotificationPopupContainer = () => {
  const [type, setType] = useState(TYPE_ALL);

  const popupRef = useRef();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const auth = useAuth();
  const notification = useNotification();
  const notifications = notification.notifications;
  const { page, prevPage, size, totalPages } = notification.pageable;

  const isShow = notification.isShowPopup;
  const accountId = auth.id;

  const handleChooseType = (type) => setType(type);

  const renderTypeButtons = useCallback(() => {
    return TYPE_BUTTONS.map((btn) => (
      <button
        className={`notification-type-btn ${btn.value} ${
          btn.value === type ? "active" : ""
        }`}
        onClick={() => {
          handleChooseType(btn.value);
        }}
        key={btn.value}
      >
        {btn.name}
      </button>
    ));
  }, [type]);

  const renderNotifications = useCallback(() => {
    if (!notifications.length) {
      return <div className="notification-popup-item">Không có thông báo!</div>;
    }

    const handleClickNotification = (notification) => {
      // dispatch(onSetCurrentNotification(notification));
      // navigate(ROUTE.NOTIFICATION);
      // dispatch(onHiddenNotificationPopup());
    };

    const renderNotificationItems = (filteredNotifications) =>
      filteredNotifications.map((notification) => (
        <NotificationItem
          notification={notification}
          key={notification.id}
          onClickNotification={handleClickNotification}
        />
      ));

    if (type === TYPE_UNREAD) {
      const unreadNotifications = notifications.filter((n) => !n.isRead);
      return renderNotificationItems(unreadNotifications);
    }

    const news = notifications.filter((n) => n.isNew);
    const earliers = notifications.filter((n) => !n.isNew);

    if (news.length > 0) {
      return (
        <>
          {news.length > 0 && (
            <>
              <div className="notification-popup-item latest">
                <h5>Mới</h5>
              </div>
              {renderNotificationItems(news)}
            </>
          )}

          {earliers.length > 0 && (
            <>
              <div className="notification-popup-item earlier">
                <h5>Cũ hơn</h5>
              </div>
              {renderNotificationItems(earliers)}
            </>
          )}
        </>
      );
    }

    return renderNotificationItems(notifications);

    //eslint-disable-next-line
  }, [type, notifications]);

  const handleViewNotifications = () => {
    viewNotificationApi(accountId)
      .then((res) => {
        dispatch(onViewNotifications());
      })
      .catch((err) => {});
  };

  useEffect(() => {
    if (isShow && accountId) {
      dispatch(getNotificationsThunk({ accountId, page, prevPage, size }));
    }
  }, [isShow, dispatch, accountId, page, size, prevPage]);

  // useEffect(() => {
  //   const scrollableElement = document.querySelector(".notification-popup");

  //   scrollableElement.addEventListener("mouseover", function (event) {
  //     document.body.classList.add("no-scroll");
  //   });

  //   scrollableElement.addEventListener("mouseleave", function (event) {
  //     document.body.classList.remove("no-scroll");
  //   });
  // }, []);

  useEffect(() => {
    const onClickOutside = (event) => {
      if (!popupRef.current) return;

      const isClickOutside = !popupRef.current.contains(event.target);
      const isClickNotificationIcon = document
        .querySelector(".notification-icon")
        .contains(event.target);

      if (isShow && isClickOutside && !isClickNotificationIcon) {
        handleViewNotifications();
        dispatch(onHiddenNotificationPopup());
      }
    };

    document.addEventListener("mousedown", onClickOutside);
    return () => {
      document.removeEventListener("mousedown", onClickOutside);
    };

    // eslint-disable-next-line
  }, [dispatch, isShow]);

  return (
    <div
      className={`${
        isShow
          ? "notification-popup-container active"
          : "notification-popup-container"
      }`}
      ref={popupRef}
    >
      <div className="notification-popup  scroll-container">
        <div className="notification-popup-item title">
          <h3>Thông báo</h3>
          <div className="options">
            {/* <div className="options-icon">
              <SlOptions />
            </div> */}
            <div className="options--view-all">
              <button
                onClick={() => {
                  dispatch(onHiddenNotificationPopup());
                  navigate(ROUTE.NOTIFICATION);
                }}
              >
                Xem tất cả
              </button>
            </div>
            {/* <div className="options-popup">
            </div> */}
          </div>
        </div>
        <div className="notification-popup-item type">
          {renderTypeButtons()}
        </div>
        {renderNotifications()}
        {page < totalPages && (
          <div className="notification-popup-item view-more">
            <button
              onClick={() => {
                dispatch(onIncreaseNotificationPage());
              }}
            >
              Xem các thông báo trước đó
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default NotificationPopupContainer;
