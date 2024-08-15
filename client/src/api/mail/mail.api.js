import axios from "axios"
import { BASE_ENDPOINTS } from "..";

const mailUrl = BASE_ENDPOINTS.MAIL;

export const sendMailToEmployeeApi = (form) => {
    return axios.post(`${mailUrl}/send`, form);
}

export const getAllMailsForCustomerApi = (customerEmail, page, size) => {
    return axios.get(`${mailUrl}/all-for-customer`, {
        params: {
            customerEmail: customerEmail,
            page: page,
            size: size
        }
    })
}

export const getMailDetailsApi = (customerEmail, mailId) => {
    return axios.get(`${mailUrl}/details-for-customer`, {
        params: {
            customerEmail: customerEmail,
            mailId: mailId
        }
    })
}