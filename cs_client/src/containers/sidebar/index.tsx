import React, { useCallback, useEffect, useState } from "react";
import ListTag from "../../components/sidebar/ListTag";
import SearchBar from "../../components/sidebar/SearchBar";
import ConversationTag from "../../components/sidebar/ConversationTag";
import { IMailBox } from "../../types/mail";
import { IConversation } from "../../types/chat";
import Box from "../../components/styled/box";
import MailBoxTag from "../../components/mail/MailBoxTag";
import { useLocation } from "react-router-dom";
import { PATHS } from "../../constants/paths";
import { useAppContext } from "../../store/context";
import { BiSolidRightArrow } from "react-icons/bi";
import Button from "../../components/styled/button";
import { TbReload } from "react-icons/tb";
import { FetchAllMailBoxesAction } from "../../store/actions/mail.action";
// import { sortByTime } from "../../utils/sort";

export type ListTagType = IConversation[] | IMailBox[];

export type CurrentTagType =
  | {
      type: "IConversation";
      data: IConversation | null;
    }
  | {
      type: "IMailBox";
      data: IMailBox | null;
    };

// interface SidebarProps {
//     type: 'chat' | 'mail',
//     currentTag: CurrentTagType | null,
//     listTag: ListTagType,
//     handleChangeCurrentTag: <T extends CurrentTagType>(tag: T) => void
// }

type SidebarState =
  | {
      type: "chat";
      listTags: IConversation[];
      currentTag: IConversation | null;
    }
  | {
      type: "mail";
      listTags: IMailBox[];
      currentTag: IMailBox | null;
    };

const Sidebar = () => {
  const [search, setSearch] = useState("");
  const [sidebarState, setSidebarState] = useState<SidebarState>({
    type: "chat",
    listTags: [],
    currentTag: null,
  });

  const { dispatch, state } = useAppContext();
  const { staff, mailBoxList, currentMailBox } = state.mail;
  const { conversations, currentConversation } = state.chat;
  const location = useLocation();

  const handleChangeInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(event.target.value);
  };

  const renderChatTags = (tags: IConversation[]) => {
    if (tags.length === 0) {
      return (
        <Box
          $height={100}
          $marginTop={20}
          $fontSize={14}
          $textAlign="center"
          $lineHeight={20}
          $display="flex"
          $alignItems="center"
          $justifyContent="center"
          $borderRadius={15}
          $border="1px dashed #fff"
          $color="#fff"
          $userSelect="none"
        >
          Danh sách trò chuyện trống
        </Box>
      );
    }

    return tags.map((conversation, index) => {
      return <ConversationTag conversation={conversation} key={index} />;
    });
  };

  const renderMailTags = (tags: IMailBox[]) => {
    if (tags.length === 0) {
      return (
        <Box
          $height={100}
          $marginTop={20}
          $fontSize={14}
          $textAlign="center"
          $lineHeight={20}
          $display="flex"
          $alignItems="center"
          $justifyContent="center"
          $borderRadius={15}
          $border="1px dashed #fff"
          $color="#fff"
          $userSelect="none"
        >
          Hộp thư đến rỗng
        </Box>
      );
    }

    return tags.map((tag, index) => <MailBoxTag key={index} mailBox={tag} />);
  };

  const renderListTags = useCallback(() => {
    const { type, listTags } = sidebarState;

    switch (type) {
      case "chat":
        // return renderChatTags(listTags.filter(tag => tag.id !== sidebarState.currentTag?.id));
        return renderChatTags(listTags);
      case "mail":
        // return renderMailTags(listTags.filter(tag => tag.id !== sidebarState.currentTag?.id));
        return renderMailTags(listTags);
    }
  }, [sidebarState]);

  const renderCurrentTag = () => {
    const { currentTag, type } = sidebarState;
    if (type === "chat" && currentTag) {
      return (
        <Box $padding={"25px 0"} $borderBottom={"1px solid #ccc"}>
          <Box
            $fontSize={".8rem"}
            $padding={"10px 0"}
            $color="#000"
            $display="flex"
            $alignItems="center"
            $gap={5}
          >
            <BiSolidRightArrow /> Cuộc trò chuyện hiện tại
          </Box>
          <ConversationTag conversation={currentTag} />
        </Box>
      );
    } else if (type === "mail" && currentTag) {
      return (
        <Box $padding={"25px 0"} $borderBottom={"1px solid #ccc"}>
          <Box
            $fontSize={".8rem"}
            $padding={"10px 0"}
            $color="#000"
            $display="flex"
            $alignItems="center"
            $gap={5}
          >
            <BiSolidRightArrow /> Hộp thư hiện tại
          </Box>
          <MailBoxTag mailBox={currentTag} />
        </Box>
      );
    } else {
      return <></>;
    }
  };

  useEffect(() => {
    const path = location.pathname;
    switch (path) {
      case PATHS.CHAT:
        setSidebarState({
          type: "chat",
          listTags: conversations,
          currentTag: currentConversation,
        });
        break;
      case PATHS.MAIL:
        setSidebarState({
          type: "mail",
          listTags: mailBoxList,
          currentTag: currentMailBox,
        });
        break;
    }
    //eslint-disable-next-line
  }, [location, state.mail, state.chat]);

  return (
    <section className="sidebar-container">
      <SearchBar
        placeholder={
          sidebarState?.type === "chat"
            ? "Tìm kiếm tin nhắn..."
            : "Tìm kiếm email..."
        }
        inputValue={search}
        handleChange={handleChangeInput}
      />
      {renderCurrentTag()}
      <Box
        $fontSize={".8rem"}
        $marginTop={20}
        $color="#000"
        $display="flex"
        $alignItems="center"
        $gap={5}
        $paddingBlock={10}
      >
        <BiSolidRightArrow />
        {sidebarState.type === "chat" ? (
          <div className="sidebar--title">
            Danh sách cuộc trò chuyện <b>({conversations.length})</b>
          </div>
        ) : (
          <div className="sidebar--title">
            Danh sách hộp thư <b>({mailBoxList.length})</b>
            <Button
              icon={TbReload}
              onClick={() => {
                if (dispatch && staff)
                  FetchAllMailBoxesAction(dispatch, staff.email);
              }}
            />
          </div>
        )}
      </Box>
      {sidebarState.listTags && <ListTag renderListTags={renderListTags} />}
    </section>
  );
};

export default Sidebar;
