import ChatIcon from "../../components/chat/ChatIcon";
import { useChat } from "../../redux/selector";
import { useDispatch } from "react-redux";
import { onHiddenChatBox, onShowChatBox } from "../../redux/slices/chat.slice";
import ConversationContainer from "./conversation/ConversationContainer";

const ChatContainer = () => {
  const chat = useChat();
  const isShowBox = chat.isShowBox;
  const dispatch = useDispatch();

  const handleShowChat = () => {
    !isShowBox && dispatch(onShowChatBox());
  };
  const handleHiddenChat = () => {
    dispatch(onHiddenChatBox());
  };

  return (
    <div className="customer-support-container chat-container">
      <ChatIcon handleShowChat={handleShowChat} />
      <ConversationContainer
        isShowBox={chat.isShowBox}
        onHiddenBox={handleHiddenChat}
      />
    </div>
  );
};

export default ChatContainer;
