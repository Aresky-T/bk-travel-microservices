import { Client, StompConfig } from '@stomp/stompjs';
import { BROKER_CHAT_CONVERSATIONS_LIST, BROKER_MAIL_BOXES_LIST } from '../constants/brokers';
import { IConversation } from '../types/chat';
import { IMailBox } from '../types/mail';
// import * as Stomp from "@stomp/stompjs";

// interface ParamsType {
//     onConnect: (frame: Stomp.IFrame) => void,
//     onDisconnect: (frame?: Stomp.IFrame) => void,
//     onStompError: (frame?: Stomp.IFrame) => void,
//     onWebSocketError: (frame?: Stomp.IFrame) => void
// }

type ParamsType = StompConfig;

export function getStompClient(options: ParamsType) {
    return new Client({
        brokerURL: "ws://localhost:8080/ws",
        ...options
    })
}

export const onSubscribeConversationsList = (client: Client, employee_id: number) => {
    return new Promise<IConversation[]>((resolve, reject) => {
        client.subscribe(BROKER_CHAT_CONVERSATIONS_LIST(employee_id), (message) => {
            console.log({ client, employee_id })
            const data = JSON.parse(message.body) as IConversation[];
            console.log("conversations: ", data);
            resolve(data);
        })
    })
}

export const onSubscribeMailBoxesList = (client: Client, employee_id: number) => {
    return new Promise<IMailBox[]>((resolve, reject) => {
        client.subscribe(BROKER_MAIL_BOXES_LIST(employee_id), (message) => {
            const data = JSON.parse(message.body) as IMailBox[];
            console.log("mailboxes: ", data);
            resolve(data)
        })
    })
}