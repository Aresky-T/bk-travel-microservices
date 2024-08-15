import React from "react";

const Notification = ({ renderHeaderOptions, renderNotifications }) => {
  return (
    <div className="notification-container main-session">
      <section className="notification_header">
        <div className="notification_header_item">
          <h3 className="notification_header_item--title">Thông báo</h3>
          <div className="notification_header_item--mark-all">
            <button>Đánh dấu tất cả là đã đọc</button>
          </div>
        </div>
        <div className="notification_header_item">
          <div className="notification_header_item--options">
            {renderHeaderOptions()}
          </div>
        </div>
      </section>
      <section className="notification_main">{renderNotifications()}</section>
    </div>
  );
};

export default Notification;
