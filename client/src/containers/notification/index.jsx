import React, { useCallback, useEffect, useState } from "react";
import { useAuth, useNotification } from "../../redux/selector";
import Notification from "../../components/notification";
import { ENTITY_TYPES } from "../../constant/notification";
import NotificationItem from "./items";
import { useDispatch } from "react-redux";
import { getNotificationsThunk } from "../../redux/slices/notification.slice";

const initEntityTypes = [
  {
    name: "Tất cả",
    value: ENTITY_TYPES.ALL,
    count: 0,
  },
  {
    name: "Booking",
    value: ENTITY_TYPES.BOOKING,
    count: 0,
  },
  {
    name: "Tour",
    value: ENTITY_TYPES.TOUR,
    count: 0,
  },
  {
    name: "Thanh toán",
    value: ENTITY_TYPES.PAYMENT,
    count: 0,
  },
  {
    name: "Mail",
    value: ENTITY_TYPES.MAIL,
    count: 0,
  },
];

const NotificationContainer = () => {
  const [entityType, setEntityType] = useState(ENTITY_TYPES.ALL);
  const [entityTypes, setEntityTypes] = useState(initEntityTypes);
  const auth = useAuth();
  const accountId = auth.id;

  const { currentNotification, counts, notifications, pageable } =
    useNotification();
  const { page, prevPage, size } = pageable;

  // const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleClickEntityType = (entityType) => {
    setEntityType(entityType);
  };

  const fetchAllNotifications = useCallback(() => {
    if (accountId) {
      dispatch(getNotificationsThunk({ accountId, page, prevPage, size }));
    }

    // eslint-disable-next-line
  }, [accountId, page, prevPage, size]);

  const renderHeaderOptions = () => {
    return entityTypes.map((type) => {
      const isActive = type.value === entityType;
      return (
        <div
          className={`notification_header_item--option${
            isActive ? " active" : ""
          }`}
          key={type.value}
        >
          <button onClick={() => handleClickEntityType(type.value)}>
            {type.name}
            <span className="count">{type.count}</span>
          </button>
        </div>
      );
    });
  };

  const renderNotifications = useCallback(() => {
    const renderFilteredNotification = (array) => {
      if (array.length === 0) {
        return (
          <div className="notification_main_item notification--empty">
            <strong>Không có thông báo nào!</strong>
          </div>
        );
      }

      return array.map((item) => (
        <NotificationItem notification={item} key={item.id} />
      ));
    };

    let filteredNotifications = notifications;

    if (entityType !== ENTITY_TYPES.ALL) {
      filteredNotifications = notifications.filter(
        (n) => n.entityType === entityType
      );
    }

    return renderFilteredNotification(filteredNotifications);
  }, [notifications, entityType]);

  useEffect(() => {
    fetchAllNotifications();
  }, [fetchAllNotifications]);

  useEffect(() => {
    if (!currentNotification) {
      // navigate("/");
    }

    // eslint-disable-next-line
  }, [currentNotification]);

  useEffect(() => {
    const arr = entityTypes.map((type) => {
      type.count = counts[type.value];
      return type;
    });

    setEntityTypes(arr);
    // eslint-disable-next-line
  }, [counts]);

  return (
    <Notification
      renderHeaderOptions={renderHeaderOptions}
      renderNotifications={renderNotifications}
    />
  );
};

export default NotificationContainer;
