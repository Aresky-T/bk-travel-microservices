import axios from "axios"
import { BASE_ENDPOINTS } from ".."

const baseUrl = BASE_ENDPOINTS.NOTIFICATION;

export const getAllNotificationsForUserApi = (accountId, page, size) => {
    return axios.get(baseUrl, {
        params: {
            userId: accountId,
            page: page,
            size: size
        }
    })
}

export const readNotificationApi = (notificationId) => {
    return axios.patch(`${baseUrl}/read`, null, {
        params: {
            notificationId: notificationId,
        }
    })
}

export const readAllNotificationsApi = (accountId) => {
    return axios.patch(`${baseUrl}/read/all`, null, {
        params: {
            userId: accountId,
        }
    })
}

export const getNewNotificationCountApi = (accountId) => {
    return axios.get(`${baseUrl}/new/count`, {
        params: {
            userId: accountId,
        }
    })
}

export const viewNotificationApi = (accountId) => {
    return axios.patch(`${baseUrl}/view`, null, {
        params: {
            userId: accountId,
        }
    })
}