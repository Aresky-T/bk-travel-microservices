import { WS_READ_CUSTOMER_MESSAGES_MSG } from './../messages/index';
import { WS_CONVERSATION_TOPIC, WS_STAFF_STATUS_TOPIC, WS_STAFF_TOPIC } from './../topics/index';
import { getStompClient } from "../../config/stompJsConfig"
import { EActivationStatus } from "../../types/enum"
import { Dispatch } from 'react';
import { ActionType } from '../../types/actions';
import { SetStaffStatusAction } from '../../store/actions/staff.action';
import { WS_SEND_MESSAGE_TO_CONVERSATION_MSG, WS_UPDATE_STAFF_STATUS_MSG } from '../messages';
import { UpdateConversationListAction, UpdateCurrentConversationAction } from '../../store/actions/chat.action';

export const WS_SUBSCRIBE_CONVERSATION_ACTION = (conversationId: number, dispatch: Dispatch<ActionType>) => {
    getStompClient()
        .then(client => {
            client.subscribe(
                WS_CONVERSATION_TOPIC(conversationId),
                (message) => {
                    const data = JSON.parse(message.body);
                    UpdateCurrentConversationAction(data, dispatch);
                    UpdateConversationListAction(data, dispatch);
                }
            )
        })
        .catch(err => { })
}

export const WS_SUBSCRIBE_STAFF_ACTION = (staffId: number, dispatch: Dispatch<ActionType>) => {
    getStompClient()
        .then(client => {
            const topic = WS_STAFF_TOPIC(staffId);
            client.subscribe(topic, (message) => {
                const data = JSON.parse(message.body);
                UpdateCurrentConversationAction(data, dispatch);
                UpdateConversationListAction(data, dispatch);
            });
        })
        .catch(err => { })
}

export const WS_SUBSCRIBE_STAFF_STATUS_ACTION = (staffId: number, dispatch: Dispatch<ActionType>) => {
    getStompClient()
        .then(client => {
            const topic = WS_STAFF_STATUS_TOPIC(staffId);
            client.subscribe(
                topic, (message) => {
                    const status: EActivationStatus =
                        JSON.parse(message.body) === "ONLINE"
                            ? EActivationStatus.ONLINE
                            : EActivationStatus.OFFLINE;

                    SetStaffStatusAction(status, dispatch);
                }
            );
        })
        .catch(err => { })
}

export const WS_PUBLISH_STAFF_STATUS = (staffId: number, status: EActivationStatus) => {
    getStompClient()
        .then(client => {
            const message = WS_UPDATE_STAFF_STATUS_MSG(staffId, status)
            client.publish({ destination: message })
        })
        .catch(err => { })
}

export const WS_PUBLISH_SEND_MESSAGE_TO_CONVERSATION_ACTION = (conversationId: number, message: any) => {
    getStompClient()
        .then(client => {
            client.publish({
                destination: WS_SEND_MESSAGE_TO_CONVERSATION_MSG(conversationId),
                body: JSON.stringify(message)
            })
        })
        .catch(err => { })
}

type ReadCustomerMessagesType = (conversationId: number) => any;
export const WS_READ_CUSTOMER_MESSAGES_ACTION: ReadCustomerMessagesType = (conversationId) => {
    getStompClient()
        .then(client => {
            client.publish({
                destination: WS_READ_CUSTOMER_MESSAGES_MSG(conversationId)
            })
        })
        .catch(err => { })
}