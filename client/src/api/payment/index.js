import axios from "axios"
import { BASE_ENDPOINTS } from ".."

const baseURL = BASE_ENDPOINTS.PAYMENT;

export const paymentBookedTourWithVNPayApi = (bookingId) => {
    return axios.post(`${baseURL}/vnpay?bookingId=${bookingId}`);
}

export const handleVnpayReturnApi = (data) => {
    return axios.post(`${baseURL}/vnpay/return`, data);
}

export const checkExistVnpayTransactionInfoApi = (bookingId) => {
    return axios.get(`${baseURL}/vnpay/transaction/check?bookingId=${bookingId}`);
}

export const getVnpayTransactionInfoApi = (bookingId) => {
    return axios.get(`${baseURL}/vnpay/transaction`, { params: { bookingId: bookingId } })
}