import React from "react"
import { LuMousePointerClick } from "react-icons/lu"
import ChatBoxContainer from "../chat_box"
import Box from "../../../components/styled/box"
import { useAppContext } from "../../../store/context"

const ChatArea = () => {
    const { state } = useAppContext();
    const conversation = state.chat.currentConversation;

    return (
        <section className="chat-area flex-center">
            {conversation ?
                <ChatBoxContainer conversation={conversation} />
                :
                <Box
                    $height="100%"
                    $display="flex"
                    $flex-direction="column"
                    $alignItems="center"
                    $justifyContent="center"
                    $color="var(--primary-color)"
                    $fontWeight={600}
                    $fontSize='1.5rem'
                >
                    <div>
                        <LuMousePointerClick size={100} />
                    </div>
                    <Box
                        $fontFamily='inherit'
                        $marginTop={16}
                        $lineHeight='2rem'
                    >
                        Không có cuộc trò chuyện nào được chọn
                    </Box>
                </Box>
            }
        </section >
    )
}

export default ChatArea