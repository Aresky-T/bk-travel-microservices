import axios from "axios"
import { CHAT_SERVICE_URL } from "."
// Rest apis
const chatURL = CHAT_SERVICE_URL

export const checkStaffPermissionApi = (email: string) => {
    return axios.get(`${chatURL}/staff/check`, {
        params: { email: email }
    })
}

export const getStaffInfoApi = (email: string) => {
    return axios.get(`${chatURL}/staff`, {
        params: { email: email }
    })
}

export const getAllConversationsForStaffApi = (staffId: number, limit: number, offset: number) => {
    return axios.get(`${chatURL}/conversations`, {
        params: {
            staffId: staffId,
            limit: limit,
            offset: offset
        }
    })
}

export const getAllConversationsForEmployeeApi = (token: string) => {
    return axios.get(`${chatURL}/conversations/employee`, {
        headers: {
            Authorization: `Bearer ${token}`,
        }
    })
}

export const getAllMessagesForConversationApi = (conversationId: number, limit: number, offset: number) => {
    return axios.get(`${chatURL}/messages`, {
        params: {
            conversationId: conversationId,
            limit: limit,
            offset: offset
        }
    })
}

export const deleteConversationApi = (conversationId: number, token: string) => {
    return axios.delete(`${chatURL}/conversation/${conversationId}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        }
    })
}