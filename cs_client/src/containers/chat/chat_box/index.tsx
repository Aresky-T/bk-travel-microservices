import React, { useCallback, useEffect, useState } from "react";
import ChatBox from "../../../components/chat/ChatBox";
import { useAppContext } from "../../../store/context";
import { IConversation, IMessage } from "../../../types/chat";
import { getAllMessagesForConversationApi } from "../../../api/chat.api";
import { EMessageSender, EMessageStatus } from "../../../types/enum";
import {
  WS_PUBLISH_SEND_MESSAGE_TO_CONVERSATION_ACTION,
  WS_READ_CUSTOMER_MESSAGES_ACTION,
  WS_SUBSCRIBE_CONVERSATION_ACTION,
} from "../../../socket/actions";

interface PropsType {
  conversation: IConversation;
}

const ChatBoxContainer: React.FC<PropsType> = (props) => {
  const [messages, setMessages] = useState<IMessage[]>([]);
  const [messageInput, setMessageInput] = useState<string>("");
  const { state, dispatch } = useAppContext();
  const staff = state.chat.staff;
  const conversation = props.conversation;

  const handleChangeInput = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setMessageInput(event.target.value);
  };

  const handleSubmitSendMessage = async (
    event: React.FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();
    if (!staff || messageInput.trim() === "") return;

    setMessageInput("");
    WS_PUBLISH_SEND_MESSAGE_TO_CONVERSATION_ACTION(conversation.id, {
      sender: "STAFF",
      senderId: staff.id,
      content: messageInput,
    });
  };

  const getAllMessagesForConversation = useCallback(async () => {
    try {
      const res = await getAllMessagesForConversationApi(
        conversation.id,
        20,
        0
      );
      const data = res.data;
      const messages = [...data].map((item) => {
        return {
          id: item.id,
          content: item.content,
          sentAt: item.sentAt,
          sender:
            item.sender === "CUSTOMER"
              ? EMessageSender.CUSTOMER
              : EMessageSender.STAFF,
          status:
            item.status === "NEW" ? EMessageStatus.NEW : EMessageStatus.SEEN,
        } as IMessage;
      });

      setMessages(messages);
    } catch (error) {}
  }, [conversation]);

  useEffect(() => {
    getAllMessagesForConversation();
  }, [getAllMessagesForConversation]);

  useEffect(() => {
    if (dispatch) {
      WS_SUBSCRIBE_CONVERSATION_ACTION(conversation.id, dispatch);
      if (conversation.newCustomerMessageCount > 0) {
        WS_READ_CUSTOMER_MESSAGES_ACTION(conversation.id);
      }
    }
  }, [conversation, dispatch]);

  return (
    <ChatBox
      conversation={conversation}
      messages={messages}
      messageInput={messageInput}
      handleChangeInput={handleChangeInput}
      handleSubmitSendMessage={handleSubmitSendMessage}
    />
  );
};

export default ChatBoxContainer;
