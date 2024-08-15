import React, { useCallback, useEffect, useState } from "react";
import { IConversation } from "../../types/chat";
import Avatar from "../customer/Avatar";
import { useAppContext } from "../../store/context";
import Box from "../styled/box";
import moment from "moment";
import { EMessageSender } from "../../types/enum";

interface PropsType {
  conversation: IConversation;
}

const ConversationTag: React.FC<PropsType> = ({ conversation }) => {
  const { dispatch, state } = useAppContext();
  const { customer } = conversation;
  const { currentConversation } = state.chat;
  const latestMessage = conversation.latestMessage;

  const [sentAt, setSentAt] = useState("");
  // const newChats = chatList.filter(chat => chat.status === "NEW" && chat.sender === "CUSTOMER");
  const count = conversation.newCustomerMessageCount;
  const customerStatus = customer.status;
  const isCurrentTag = currentConversation?.id === conversation.id;

  const renderSentTimeUsingMomentJS = useCallback(() => {
    const sentAt = moment(conversation.latestMessage?.sentAt);
    const current = moment();

    const difference = current.diff(sentAt, "second");

    if (difference < 10) {
      return `vừa xong`;
    } else if (difference < 60) {
      return `${difference} giây`;
    } else if (difference < 3600) {
      return `${moment.duration(difference, "second").minutes()} phút`;
    } else if (difference < 86400) {
      return `${moment.duration(difference, "second").hours()} giờ`;
    } else if (difference < 604800) {
      return `${moment.duration(difference, "second").days()} ngày`;
    } else if (difference < 31536000) {
      return `${moment.duration(difference, "second").weeks()} tuần`;
    } else {
      return `${moment.duration(difference, "second").years()} năm`;
    }
    // eslint-disable-next-line
  }, [conversation.latestCustomerMessage?.sentAt, sentAt]);

  useEffect(() => {
    setTimeout(() => {
      setSentAt(renderSentTimeUsingMomentJS());
    }, 60000);
  }, [renderSentTimeUsingMomentJS]);

  return (
    <Box
      className={isCurrentTag ? "tag current" : "tag"}
      onClick={() => {
        dispatch &&
          dispatch({
            type: "CHAT|SET|CURRENT_CONVERSATION",
            payload: conversation,
          });
      }}
    >
      <Box className="tag-avatar">
        <Avatar
          type="url"
          url="https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"
        />
        {customerStatus === "ONLINE" && (
          <Box
            className="flex-center"
            $position="absolute"
            $bottom={1}
            $right={0}
            $backgroundColor="var(--green-color)"
            $borderRadius={"50%"}
            $width={16}
            $height={16}
            $border={"2px solid #fff"}
          />
        )}
      </Box>
      <Box className="tag-info" $width={"100%"}>
        <div className="tag-info_customer-info--fullname">
          {customer.fullName}
        </div>
        {latestMessage ? (
          <div className="tag-info_latest-msg--content">
            {latestMessage.sender === EMessageSender.STAFF ? (
              <div className="tag-info_latest-msg--content-data">
                Bạn: {latestMessage.content}
              </div>
            ) : (
              <div
                className="tag-info_latest-msg--content-data"
                style={{
                  fontWeight:
                    latestMessage.status === "NEW" ? "bold" : "normal",
                }}
              >
                {latestMessage.content}
              </div>
            )}
            <div className="tag-info_latest-msg--sent-at">
              {" "}
              .{renderSentTimeUsingMomentJS()}
            </div>
          </div>
        ) : (
          <div>
            <b>Khách hàng mới</b>
          </div>
        )}
      </Box>
      {count > 0 && (
        <Box
          className="flex-center"
          $position="absolute"
          $top={"50%"}
          $right={10}
          $fontSize={".6rem"}
          $backgroundColor="var(--red-color)"
          $color="#fff"
          $fontWeight={600}
          $borderRadius={"50%"}
          $width={16}
          $height={16}
          $transform={"translateY(-50%)"}
          $border={"2px solid #fff"}
        />
      )}
    </Box>
  );
};

export default ConversationTag;
