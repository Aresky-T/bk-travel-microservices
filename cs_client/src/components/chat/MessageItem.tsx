import React, { useMemo } from "react";
import { IMessage } from "../../types/chat";
import ICustomer from "../../types/customer";
import { BsCheckAll } from "react-icons/bs";
import { FaEye } from "react-icons/fa";
import Avatar from "../customer/Avatar";
import Box from "../styled/box";
import { EMessageSender, EMessageStatus } from "../../types/enum";

interface PropsType {
  message: IMessage;
  customer: ICustomer;
}

const MessageItem: React.FC<PropsType> = (props) => {
  const { message } = props;
  const { content, sender, sentAt } = message;
  const messageClass =
    sender === EMessageSender.CUSTOMER ? "customer-message" : "your-message";

  const renderMessageStatus = useMemo(() => {
    switch (message.status) {
      case EMessageStatus.NEW:
        return <BsCheckAll />;
      case EMessageStatus.SEEN:
        return <FaEye />;
      default:
        return "undefine";
    }
  }, [message]);

  return (
    <div className={`${messageClass} chat-message`}>
      {sender === "CUSTOMER" && (
        <Box $width={30} $height={30} $alignSelf="flex-end">
          <Avatar
            type="url"
            url={
              "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"
            }
          />
        </Box>
      )}
      <div className="content">
        <div className="content__message">{content}</div>
        <div className="content__sent-at">
          {new Date(sentAt).toLocaleString("vi-VN", { timeStyle: "short" })}
        </div>
      </div>
      {sender === EMessageSender.STAFF && (
        <div className="status">{renderMessageStatus}</div>
      )}
    </div>
  );
};

export default MessageItem;
