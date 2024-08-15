import { createSlice } from "@reduxjs/toolkit";

const initChatState = {
    isShowBox: false,
    isStarted: false,
    isConnected: false,
    conversationId: null,
    sendingMessage: null,
    latestStaffMessage: null,
    newStaffMessageCount: 0,
    staff: null,
    customer: null,
    customerType: null,
    messages: [],
}

const chatSlice = createSlice({
    name: 'chat',
    initialState: initChatState,
    reducers: {
        onShowChatBox: (state, action) => {
            state.isShowBox = true;
        },
        onHiddenChatBox: (state, action) => {
            state.isShowBox = false;
        },
        updateChatIsConnected: (state, action) => {
            state.isConnected = action.payload;
        },
        updateChatIsStarted: (state, action) => {
            state.isStarted = action.payload;
        },
        updateChatConversationId: (state, action) => {
            state.conversationId = action.payload;
        },
        updateChatCustomerType: (state, action) => {
            state.customerType = action.payload;
        },
        updateChatSendingMessage: (state, action) => {
            state.sendingMessage = action.payload;
        },
        updateChatLatestStaffMessage: (state, action) => {
            state.latestStaffMessage = action.payload;
        },
        updateChatNewStaffMessageCount: (state, action) => {
            state.newStaffMessageCount = action.payload;
        },
        updateChatStaffInfo: (state, action) => {
            state.staff = action.payload;
        },
        updateChatCustomerInfo: (state, action) => {
            state.customer = action.payload;
        },
        updateChatMessages: (state, action) => {
            state.messages = action.payload;
        },
        addChatMessageItem: (state, action) => {
            const newMessage = action.payload;
            const messageMap = new Map(state.messages.map(item => [item.id, item]));
            if (!messageMap.has(newMessage.id)) {
                state.messages.push(action.payload);
            }
        },
        updateChatField: (state, action) => {
            const { key, value } = action.payload;
            if (Object.prototype.hasOwnProperty.call(state, key)) {
                state[key] = value;
            }
        },
        updateChatFields: (state, action) => {
            for (const key in action.payload) {
                if (Object.hasOwnProperty.call(state, key)) {
                    state[key] = action.payload[key]
                }
            }
        },
        onCancelChat: (state, action) => {
            state.conversationId = null;
            state.customer = null;
            state.customerType = null;
            state.staff = null;
            state.messages = [];
            state.sendingMessage = null;
            state.newStaffMessageCount = 0;
            state.latestStaffMessage = null;
            state.isStarted = false;
            state.isShowBox = false;
            state.isConnected = false;
        }
    }
})

export const {
    addChatMessageItem,
    onCancelChat,
    onHiddenChatBox,
    onShowChatBox,
    updateChatConversationId,
    updateChatCustomerInfo,
    updateChatCustomerType,
    updateChatField,
    updateChatFields,
    updateChatIsConnected,
    updateChatIsStarted,
    updateChatSendingMessage,
    updateChatLatestStaffMessage,
    updateChatMessages,
    updateChatNewStaffMessageCount,
    updateChatStaffInfo,
} = chatSlice.actions;
export default chatSlice.reducer;