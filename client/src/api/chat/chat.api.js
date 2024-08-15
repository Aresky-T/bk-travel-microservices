import axios from "axios"

const chatURL = "http://localhost:8080/api/v1/chat"

export const connectEmployeeForGuestCustomerApi = ({ fullName, email }) => {
    return axios.post(`${chatURL}/connect/guest`, { fullName, email });
}

export const connectEmployeeForRegisteredCustomerApi = (token) => {
    return axios.post(`${chatURL}/connect/registered`, null, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const registerCustomerApi = (fullName, email) => {
    return axios.post(`${chatURL}/register-customer`, {
        fullName: fullName,
        email: email
    })
}

export const removeCustomerApi = (customerId) => {
    return axios.delete(`${chatURL}/customer`, {
        params: { customerId: customerId }
    })
}

export const findOnlineStaffApi = () => {
    return axios.get(`${chatURL}/staff/online`)
}

export const connectToStaffApi = (customerId, staffId) => {
    return axios.post(`${chatURL}/customer/connect-to-staff`, {
        customerId: customerId,
        staffId: staffId
    })
}

export const getAllMessagesApi = (conversationId, limit, offset) => {
    return axios.get(
        `${chatURL}/messages`,
        {
            params: {
                conversationId: conversationId,
                limit: limit,
                offset: offset,
            },
        }
    );
}