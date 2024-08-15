import React from 'react'
import { IConversation } from '../../types/chat'
import Box from '../styled/box'
import Avatar from '../customer/Avatar'

interface PropsType {
    conversation: IConversation,
    deleteConversation: () => void
}

const ConversationInfo: React.FC<PropsType> = (props) => {
    const conversation = props.conversation;
    const customer = conversation.customer;
    const { email, fullName, avatarUrl } = customer;

    return (
        <Box
            className='chat-box__conversation-info'
            $display='flex'
            $flexDirection='column'
            $alignItems='center'
            $justifyContent='center'
            $gap={25}
            $padding={"15px"}
            $width={350}
            $borderLeft={"2px solid #ccc"}
            $backgroundColor='#fff'
        >
            <Box className='conversation-info__avatar'
                $width={"100px"}
                $height={"100px"}
            >
                {avatarUrl ?
                    <Avatar type='url' url={avatarUrl} /> :
                    <Avatar type='text' text={fullName} />}
            </Box>
            <Box
                $textAlign='center'
                className='conversation-info__customer'
            >
                <h3>Thông tin khách hàng</h3>
                <table>
                    <tr>
                        <td>Tên:</td>
                        <td>{fullName}</td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td>{email}</td>
                    </tr>
                </table>
            </Box>
            <Box className='conversation-info__actions'>
                <button onClick={props.deleteConversation}>
                    Xóa cuộc trò chuyện
                </button>
            </Box>
        </Box>
    )
}

export default ConversationInfo