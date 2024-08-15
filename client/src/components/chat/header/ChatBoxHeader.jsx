import { IoMdClose } from "react-icons/io";
import { MdOutlineHorizontalRule } from "react-icons/md";
import { useChat } from "../../../redux/selector";
import CircleButton from "../../styled/button/CircleButton";

const ChatBoxHeader = ({ formData, handleHiddenChat, handleCancelChat }) => {
    const { chatStatus, customer, employee } = useChat();
    const isConnected = chatStatus.isConnected;
    return (
        <div className="chat-box_header"
            style={isConnected ? { borderBottom: "1px solid #e9e7e7" } : {}}
        >
            <div className="chat-box_head_logo">
                <b>BK</b>
            </div>
            {isConnected &&
                <div className="customer-info">
                    <div className='customer-name'>{customer?.fullName || formData?.fullName || "Khách hàng"}</div>
                    <div className={employee.status ? "connect-status" : "connect-status disconnect"}>
                        {employee?.status === "ONLINE" ? "Đã kết nối" : "Không có kết nối"}</div>
                </div>
            }
            <div className="chat-box_head_icons">

                <CircleButton
                    onClick={handleHiddenChat}
                    border={"none"}
                    outline={"none"}
                    title={"Ẩn chat"}
                >
                    <MdOutlineHorizontalRule size={21} />
                </CircleButton>
                {isConnected &&
                    <CircleButton
                        onClick={handleCancelChat}
                        border={"none"}
                        outline={"none"}
                        title={"Hủy chat"}
                    >
                        <IoMdClose size={21} />
                    </CircleButton>}
            </div>
        </div>
    )
}

export default ChatBoxHeader