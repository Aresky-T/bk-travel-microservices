import React from "react";
import CircleButton from "../styled/button/CircleButton";
import { MdOutlineHorizontalRule } from "react-icons/md";
import { IoMdClose } from "react-icons/io";
import { useDispatch } from "react-redux";
import { onCancelChat, onHiddenChatBox } from "../../redux/slices/chat.slice";
import { useChat } from "../../redux/selector";

const ConversationHeader = ({ onDisconnectConversation }) => {
  const dispatch = useDispatch();
  const chat = useChat();
  const staff = chat.staff;
  const isConnected = chat.isConnected;

  return (
    <div
      className="chat-box_header"
      style={isConnected ? { borderBottom: "1px solid #e9e7e7" } : {}}
    >
      {isConnected ? (
        <div className="staff-info">
          <div className="staff-avatar">
            <img src={staff.avatarUrl} alt="" />
          </div>
          <div>
            <div className="staff-name">{staff?.fullName}</div>
            <div
              className={
                staff.status ? "connect-status" : "connect-status disconnect"
              }
            >
              {staff?.status === "ONLINE" ? "Đã kết nối" : "Không có kết nối"}
            </div>
          </div>
        </div>
      ) : (
        <div className="chat-box_head_logo">
          <b>BK</b>
        </div>
      )}
      <div className="chat-box_head_icons">
        <CircleButton
          onClick={() => dispatch(onHiddenChatBox())}
          border={"none"}
          outline={"none"}
          title={"Ẩn chat"}
        >
          <MdOutlineHorizontalRule size={21} />
        </CircleButton>
        {isConnected && (
          <CircleButton
            onClick={() => {
              onDisconnectConversation();
              dispatch(onCancelChat());
            }}
            border={"none"}
            outline={"none"}
            title={"Hủy chat"}
          >
            <IoMdClose size={21} />
          </CircleButton>
        )}
      </div>
    </div>
  );
};

export default ConversationHeader;
