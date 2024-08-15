export const WS_SEND_MESSAGE_TO_CUSTOMER_MSG
    = (customerId: number) => `/app/chat/send-message-to-customer/${customerId}`;

export const WS_SEND_MESSAGE_TO_CONVERSATION_MSG
    = (conversationId: number) => `/app/chat/send-message-to-conversation/${conversationId}`;

export const WS_UPDATE_STAFF_STATUS_MSG
    = (staffId: number, status: string) => `/app/staff/${staffId}/update-status/${status}`

export const WS_READ_CUSTOMER_MESSAGES_MSG
    = (conversationId: number) => `/app/chat/read-customer-messages/conversation/${conversationId}`