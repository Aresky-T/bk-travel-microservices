import React, { useCallback, useMemo } from "react";

function ChatItem({ chat }) {
  const { sender, status, sentAt, content } = chat;

  const renderMessageStatus = useMemo(() => {
    switch (status) {
      case "NEW":
        return "Đã gửi";
      case "SEEN":
        return "Đã xem";
      case "SENDING":
        return "Đang gửi";
      default:
        return "";
    }
  }, [status]);

  const renderSentAt = useCallback(() => {
    const sentTime = new Date(sentAt);
    return sentTime.toLocaleString("vi-VN", { timeStyle: "short" });
  }, [sentAt]);

  return (
    <div className={`chat-item ${chat.sender.toLowerCase()}`}>
      {sender === "STAFF" && <div className="avatar-sender">BK</div>}
      <div className="chat-item_message">
        <p>{content}</p>
        <div
          style={{
            fontSize: ".6rem",
            fontStyle: "italic",
            marginTop: "10px",
            display: "flex",
            alignItems: "center",
            gap: 5,
            alignSelf: sender === "CUSTOMER" ? "flex-end" : "flex-start",
          }}
        >
          <span>{renderSentAt()}</span>
          {sender === "CUSTOMER" && (
            <span
              style={{
                display: "flex",
                alignItems: "center",
              }}
            >
              .{renderMessageStatus}
            </span>
          )}
        </div>
      </div>
    </div>
  );
}

export default ChatItem;
