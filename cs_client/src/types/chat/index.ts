import ICustomer from "../customer"
import { EMessageSender, EMessageStatus } from "../enum"
import IStaff from "../staff"

export interface IChat {
    id: number,
    message: string,
    sender: 'EMPLOYEE' | 'CUSTOMER',
    sentAt: string,
    status: 'NEW' | 'SEEN'
}

export interface IMessage {
    id: number,
    content: string,
    sender: EMessageSender,
    status: EMessageStatus,
    sentAt: string
}

export interface IChatListFilter {
    date: Date,
    chats: IChat[]
}

export interface IMessageListFilter {
    date: Date,
    messages: IMessage[]
}

export interface IConversation {
    id: number,
    customer: ICustomer,
    newCustomerMessageCount: number,
    latestCustomerMessage: IMessage | null,
    createdAt: string,
    updatedAt: string,
    latestMessage: IMessage | null,
    messages: IMessage[],
}

export interface IChatState {
    staff: IStaff | null,
    conversations: IConversation[],
    currentConversation: IConversation | null,
}