import {BsMessenger} from "react-icons/bs";

const ChatIcon = ({handleShowChat}) => {
    return (
        <div className="chat-icon" onClick={handleShowChat}>
            <div style={{ marginRight: '6px', marginLeft: '-3px' }}>
                <div style={{ display: "flex", alignItems: "center" }}>
                    <BsMessenger size={24} />
                </div>
            </div>
            <div className='chat-icon__text'>Chat</div>
        </div>
    )
}

export default ChatIcon;