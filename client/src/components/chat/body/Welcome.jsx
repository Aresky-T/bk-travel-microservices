import React from "react";
import { BiSolidBadgeCheck } from "react-icons/bi";
import { useDispatch } from "react-redux";
import { updateChatIsStarted } from "../../../redux/slices/chat.slice";

const Welcome = () => {
  const dispatch = useDispatch();
  return (
    <div className="chat-box_body--welcome">
      <div
        className="chat--welcome_item"
        style={{ display: "flex", alignItems: "center", fontSize: 20 }}
      >
        <strong style={{ fontWeight: 600 }}>Chat với BK TRAVEL</strong>
        <div
          style={{
            display: "flex",
            alignItems: "center",
            color: "var(--chat-primary-color)",
          }}
        >
          <BiSolidBadgeCheck size={23} />
        </div>
      </div>
      <div className="chat--welcome_item" style={{ fontSize: "15px" }}>
        Xin chào! Chúng tôi có thể giúp gì cho bạn?
      </div>
      <div className="chat--welcome_item">
        <button
          onClick={() => {
            dispatch(updateChatIsStarted(true));
          }}
        >
          Bắt đầu chat
        </button>
      </div>
    </div>
  );
};

export default Welcome;
