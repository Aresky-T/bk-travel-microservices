import { Reducer } from "react";
import { IChatState } from "../../types/chat";
import { ActionType } from "../../types/actions";
import { sortByTime } from "../../utils/sort";

const chatReducer: Reducer<IChatState, ActionType> = (state, action) => {
    switch (action.type) {
        case "CHAT|SET|STAFF_INFO":
            return { ...state, staff: action.payload }
        case "CHAT|SET|STAFF_STATUS":
            if (state.staff) {
                state.staff.status = action.payload;
                return { ...state };
            }
            break;
        case "CHAT|SET|CONVERSATION_LIST":
            return { ...state, conversations: action.payload }
        case "CHAT|UPDATE|CONVERSATION_LIST|ONE":
            {
                const data = action.payload;

                let newList = [];

                if (state.conversations.some(item => item.id === data.id)) {
                    newList = state.conversations.map(item => {
                        if (item.id === data.id) {
                            return {
                                ...item,
                                customer: data.customer ?? item.customer,
                                latestMessage: data.latestMessage,
                                newCustomerMessageCount: data.newCustomerMessageCount,
                                updatedAt: data.updatedAt
                            }
                        }

                        return item;
                    });
                    // return { ...state, conversations: newList }
                } else {
                    state.conversations.push(data);
                    newList = { ...state.conversations };
                }

                const sortedList = newList.sort((a, b) => sortByTime(b.updatedAt, a.updatedAt));

                return { ...state, conversations: sortedList };
            }
        case "CHAT|UPDATE|CONVERSATION_LIST|MULTIPLE":
            {
                const data = action.payload;
                if (data.length === 0) return state;

                const conversations = state.conversations;

                const dataMap = new Map(data.map(item => [item.id, item]));

                const newList = conversations.map(con => dataMap.get(con.id) || con);

                data.forEach(item => {
                    if (conversations.some(con => con.id === item.id)) {
                        newList.push(item);
                    }
                })

                return { ...state, conversations: newList };
            }
        case "CHAT|SET|CURRENT_CONVERSATION":
            return { ...state, currentConversation: action.payload }
        case "CHAT|UPDATE|CURRENT_CONVERSATION":
            {
                const data = action.payload;
                if (!state.currentConversation || state.currentConversation.id !== data.id)
                    return state;

                return {
                    ...state, currentConversation: {
                        ...state.currentConversation,
                        customer: data.customer ?? state.currentConversation.customer,
                        latestMessage: data.latestMessage,
                        newCustomerMessageCount: data.newCustomerMessageCount,
                        updatedAt: data.updatedAt,
                    }
                }
            }
        case "CHAT|CLEAR|CONVERSATION_LIST":
            return { ...state, conversations: [] }
        case "CHAT|REMOVE|CURRENT_CONVERSATION":
            return { ...state, currentConversation: null };
        case "CHAT|REMOVE|STAFF_INFO":
            return { ...state, staff: null };
        case "CHAT|CLEAR|ALL":
            return {
                ...state,
                staff: null,
                conversations: [],
                currentConversation: null
            }
        default:
            break;
    }

    return state;
}

export default chatReducer;