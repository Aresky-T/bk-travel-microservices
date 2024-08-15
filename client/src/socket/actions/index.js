import { getStompClient } from '..';
import { addChatMessageItem } from '../../redux/slices/chat.slice';
import {
    WS_CONNECT_TO_STAFF_MSG,
    WS_DISCONNECT_TO_STAFF_MSG,
    WS_SEND_MESSAGE_TO_STAFF_MSG,
    WS_UPDATE_CUSTOMER_STATUS_MSG
} from '../messages';
import { WS_CONVERSATION_TOPIC, WS_STAFF_STATUS_TOPIC, WS_STAFF_TOPIC } from '../topics';

// Subscribe to received message
export const WS_SUBSCRIBE_CONVERSATION_ACTION = (conversationId, dispatch) => {
    getStompClient()
        .then(client => {
            const topic = WS_CONVERSATION_TOPIC(conversationId);
            client.subscribe(topic, (message) => {
                const { latestMessage } = JSON.parse(message.body);
                dispatch(addChatMessageItem(latestMessage));
            })
        })
        .catch(err => { })
}

// Subscribe to check whether new message has been sent successfully or failed
export const WS_SUBSCRIBE_STAFF_ACTION = (staffId, dispatch) => {
    getStompClient()
        .then(client => {
            const topic = WS_STAFF_TOPIC(staffId);
            client.subscribe(topic, (message) => {
                const { latestMessage } = JSON.parse(message.body);
                // dispatch(updateChatSendingMessage(null));
                dispatch(addChatMessageItem(latestMessage));
            })
        })
        .catch(err => { })
}

// Subscribe to check staff activation status
export const WS_SUBSCRIBE_STAFF_STATUS_ACTION = (staffId, dispatch) => {
    getStompClient()
        .then(client => {
            const topic = WS_STAFF_STATUS_TOPIC(staffId);
            client.subscribe(topic, (message) => {
                const data = JSON.parse(message.body);
                console.log(data);
            })
        })
        .catch(err => { })
}


export const WS_PUBLISH_CUSTOMER_STATUS_ACTION = (customerId, status) => {
    getStompClient()
        .then(client => {
            const message = WS_UPDATE_CUSTOMER_STATUS_MSG(customerId, status)
            client.publish({ destination: message })
        })
        .catch(err => { })
}

export const WS_PUBLISH_SEND_MESSAGE_TO_STAFF_ACTION = (staffId, message) => {
    getStompClient()
        .then(client => {
            client.publish({
                destination: WS_SEND_MESSAGE_TO_STAFF_MSG(staffId),
                body: JSON.stringify(message)
            })
        })
        .catch(err => { })
}

export const WS_CONNECT_TO_STAFF_ACTION = (customerId, staffId) => {
    getStompClient()
        .then(client => {
            client.publish({
                destination: WS_CONNECT_TO_STAFF_MSG(customerId, staffId)
            })
        })
        .catch(err => { })
}

export const WS_DISCONNECT_TO_STAFF_ACTION = (customerId, staffId) => {
    getStompClient()
        .then(client => {
            client.publish({
                destination: WS_DISCONNECT_TO_STAFF_MSG(customerId, staffId)
            })
        })
        .catch(err => { })
}