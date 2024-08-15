import React, { Fragment, useEffect, useRef, useState } from "react";
import { IoMdSend } from "react-icons/io";
import { IConversation, IMessage, IMessageListFilter } from "../../types/chat";
import Avatar from "../customer/Avatar";
import ConversationInfo from "./ConversationInfo";
import Box from "../styled/box";
import Button from "../styled/button";
import { FiLogOut } from "react-icons/fi";
import { useAppContext } from "../../store/context";
import { LuBadgeInfo } from "react-icons/lu";
import { MdDeleteOutline } from "react-icons/md";
import { toast } from "react-toastify";
import { warningAlertWithCancel } from "../../config/sweetAlertConfig";
import { getStompClient } from "../../config/stompJsConfig";
import { deleteConversationApi } from "../../api/chat.api";
import MessageItem from "./MessageItem";

interface PropsType {
  conversation: IConversation;
  messages: IMessage[];
  messageInput: string;
  handleChangeInput: (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => void;
  handleSubmitSendMessage: (event: React.FormEvent<HTMLFormElement>) => void;
}

const ChatBox: React.FC<PropsType> = ({
  conversation,
  messages,
  messageInput,
  handleChangeInput,
  handleSubmitSendMessage,
}) => {
  const [isShowConversation, setIsShowConversation] = useState(false);
  const boxRef = useRef<HTMLDivElement>(null);
  const { dispatch, state } = useAppContext();
  const { auth, chat } = state;
  const { currentConversation, staff } = chat;

  function deleteConversation() {
    warningAlertWithCancel(
      "Cảnh báo",
      "Các thông tin về cuộc hội thoại bao gồm thông tin khách hàng và các tin nhắn sẽ bị xóa vĩnh viễn. Bạn có chắc chắn muốn xóa?",
      "Xóa",
      "Hủy bỏ"
    ).then((result) => {
      if (result.isConfirmed) {
        auth.data.token &&
          deleteConversationApi(conversation.id, auth.data.token).then(
            (res) => {
              const isDeleted = res.data;
              if (isDeleted) {
                getStompClient().then((client) => {
                  staff &&
                    client.publish({
                      destination: `/app/chat/update-conversations/${staff.id}`,
                    });
                });
                toast.success("Xóa cuộc trò chuyện thành công!");
                dispatch &&
                  dispatch({ type: "CHAT|REMOVE|CURRENT_CONVERSATION" });
              } else {
                toast.error("Xóa thất bại!");
              }
            }
          );
      }
    });
  }

  function groupMessagesBySentAt(messages: IMessage[]) {
    const groupedMessages = new Map<string, IMessage[]>();
    const result: IMessageListFilter[] = [];

    messages.forEach((message) => {
      const sentAt = new Date(message.sentAt);
      const date = new Date(
        sentAt.getFullYear(),
        sentAt.getMonth(),
        sentAt.getDate()
      );
      const dateString = date.toDateString();
      if (groupedMessages.has(dateString)) {
        const existingMessage = groupedMessages.get(dateString);
        existingMessage?.push(message);
      } else {
        groupedMessages.set(dateString, [message]);
      }
    });

    groupedMessages.forEach((value, key) => {
      result.push({ date: new Date(key), messages: value });
    });

    return result;
  }

  const renderMessageItem = () => {
    const { customer } = conversation;
    const getSentTime = (sentAt: string) => {
      return new Date(sentAt).getTime();
    };
    messages.sort((a, b) => getSentTime(a.sentAt) - getSentTime(b.sentAt));
    const chatListFilter = groupMessagesBySentAt(messages);

    if (messages.length) {
      return chatListFilter.map((item, index) => {
        const { messages, date } = item;
        const today = new Date();
        const dateStringFull = date.toLocaleDateString("vi-VN", {
          dateStyle: "full",
        });
        const dateStringLong = date.toLocaleDateString("vi-VN", {
          dateStyle: "long",
        });
        return (
          <Fragment key={index}>
            <div className="date-of-messages">
              <span>
                {date.getDate() === today.getDate()
                  ? `Hôm nay, ${dateStringLong}`
                  : dateStringFull}
              </span>
            </div>
            <>
              {messages.map((msg) => (
                <MessageItem message={msg} customer={customer} key={msg.id} />
              ))}
            </>
          </Fragment>
        );
      });
    }

    return (
      <div className="none-message message-item">
        <div className="content">
          Khách hàng "<b>{customer.fullName}</b>" đã kết nối tới bạn!
        </div>
      </div>
    );
  };

  useEffect(() => {
    if (boxRef.current) {
      const element = boxRef.current;
      element.scrollTop = element.scrollHeight;
    }
  });

  return (
    <div className="chat-box">
      <div className="chat-box__header">
        <div className="chat-box__header__customer">
          <div
            style={{ backgroundColor: "#c2c2c2" }}
            className="chat-area_header--customer_avatar"
          >
            <Avatar
              type="url"
              url={
                "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"
              }
            />
          </div>
          <div className="customer_name-n-status">
            <div>{currentConversation?.customer.fullName}</div>
            <div style={{ fontSize: 11 }}>
              {currentConversation?.customer.status === "ONLINE" ? (
                <span style={{ color: "var(--green-color)" }}>
                  Đang trực tuyến
                </span>
              ) : (
                <span style={{ color: "var(--red-color)" }}>
                  Không trực tuyến
                </span>
              )}
            </div>
          </div>
        </div>
        <Box
          className="chat-box__header__options"
          $display="flex"
          $alignItems="center"
          $justifyContent="flex-end"
          $gap={5}
        >
          <Button icon={MdDeleteOutline} onClick={deleteConversation} />
          <Button
            icon={LuBadgeInfo}
            onClick={() => {
              setIsShowConversation(!isShowConversation);
            }}
          />
          <Button
            icon={FiLogOut}
            onClick={() => {
              dispatch &&
                dispatch({
                  type: "CHAT|REMOVE|CURRENT_CONVERSATION",
                });
            }}
          />
        </Box>
      </div>
      <div className="chat-box__body cs-scroll" ref={boxRef}>
        {renderMessageItem()}
      </div>
      <form className="chat-box__footer" onSubmit={handleSubmitSendMessage}>
        <div className="message-container">
          <input
            id="message"
            // rows={1}
            max-rows={3}
            placeholder="Nhập tin nhắn"
            spellCheck={false}
            value={messageInput}
            onChange={handleChangeInput}
          ></input>
        </div>
        <button
          className="send-message"
          type="submit"
          style={
            messageInput.trim() !== ""
              ? {}
              : {
                  pointerEvents: "none",
                  color: "gray",
                }
          }
        >
          <IoMdSend />
        </button>
      </form>
      {isShowConversation && (
        <ConversationInfo
          conversation={conversation}
          deleteConversation={deleteConversation}
        />
      )}
    </div>
  );
};

export default ChatBox;
