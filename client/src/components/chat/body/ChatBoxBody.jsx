import { styled } from "styled-components"
import Welcome from "./Welcome";
import SelectType from "./SelectType";
import GuestCustomerLogin from "./GuestCustomerLogin";
import Connected from "./Connected";
import { useChat } from "../../../redux/selector";

export const ChatButton = styled.button`
    width: 100%;
    min-width: 160px;
    min-height: 54px;
    outline: none;
    border-radius: 16px;
    border: none;
    font-weight: 600;
    font-size: 1rem;
    font-family: inherit;
    background-color: var(--primary-color);
    color: #fff;
    transition: 100ms linear;
    cursor: pointer;
    over-flow: hidden;

    &:hover {
        background-color: var(--secondary-color);
    }
`;

const ChatBoxBody = ({ formData, errors, handleChangeFormData, handleConnectChat, handleSubmitSendMessage }) => {
    const { chatStatus, type, chatList } = useChat();
    const { isStarted, isConnecting, isConnected } = chatStatus;


    const isWelcomeVisible = !isStarted && !isConnected;
    const isSelectTypeVisible = isStarted && !isConnected && (!type || type === "REGISTERED");
    const isGuestCustomerLoginVisible = isStarted && !isConnected && type === "GUEST";
    const isConnectedVisible = isStarted && isConnected && type;

    return (
        <div className="chat-box_body"
            style={isConnected ?
                { height: 450, paddingInline: 0 }
                :
                { height: 300, paddingInline: 25 }}
        >

            {isWelcomeVisible && <Welcome />}

            {isSelectTypeVisible &&
                <SelectType isConnecting={isConnecting} />}

            {isGuestCustomerLoginVisible &&
                <GuestCustomerLogin
                    errors={errors}
                    formData={formData}
                    handleChangeFormData={handleChangeFormData}
                    handleConnectChat={handleConnectChat}
                    isConnecting={isConnecting}
                />}

            {isConnectedVisible &&
                <Connected
                    chatList={chatList}
                    formData={formData}
                    handleChangeFormData={handleChangeFormData}
                    handleSubmitSendMessage={handleSubmitSendMessage}
                />}
        </div>
    )
}

export default ChatBoxBody