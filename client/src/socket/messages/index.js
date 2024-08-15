export const WS_SEND_MESSAGE_TO_STAFF_MSG
    = (staffId) => `/app/chat/send-message-to-staff/${staffId}`;

export const WS_UPDATE_CUSTOMER_STATUS_MSG
    = (customerId, status) => `/app/customer/${customerId}/update-status/${status}`;

export const WS_CONNECT_TO_STAFF_MSG
    = (customerId, staffId) => `/app/customer/${customerId}/staff/${staffId}/connect`;

export const WS_DISCONNECT_TO_STAFF_MSG
    = (customerId, staffId) => `/app/customer/${customerId}/staff/${staffId}/disconnect`;