import React from "react";
import { AiOutlineClose } from "react-icons/ai";
import { ChatButton } from "./ChatBoxBody";
import { useDispatch } from "react-redux";
import {
  updateChatCustomerType,
  updateChatIsStarted,
} from "../../../redux/slices/chat.slice";

const SelectType = ({ isConnecting, onRegisterCustomer }) => {
  // const { chatStatus } = useChat();
  // const isConnecting = chatStatus.isConnecting;
  const dispatch = useDispatch();
  return (
    <div className="chat-box_body--select-type">
      <div className="chat-box_body--select-type_main">
        <div>
          <strong>Đăng nhập</strong>
          <button
            onClick={() => {
              dispatch(updateChatIsStarted(false));
            }}
          >
            <AiOutlineClose />
          </button>
        </div>
        <div>
          <ChatButton
            type="button"
            onClick={() => {
              onRegisterCustomer("REGISTERED_CUSTOMER");
            }}
          >
            {isConnecting ? "Đang kết nối..." : "Bạn đã đăng nhập?"}
          </ChatButton>
        </div>
        <div>
          <ChatButton
            type="button"
            onClick={() => {
              dispatch(updateChatCustomerType("GUEST_CUSTOMER"));
            }}
          >
            Tiếp tục với vai trò khách
          </ChatButton>
        </div>
      </div>
    </div>
  );
};

export default SelectType;
