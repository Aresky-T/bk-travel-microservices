import axios from "axios";
import { BASE_ENDPOINTS, configAPI } from ".."

const baseURL = BASE_ENDPOINTS.BOOKING;

export const bookTourForUserApi = (formData) => {
    return axios.post(baseURL, formData, {
        params: {
            type: "PAY_LATER"
        }
    });
}

export const bookTourAndPaymentWithVNPayApi = (formData) => {
    return axios.post(`${baseURL}`, formData, {
        params: {
            type: "VNPAY_ON_WEBSITE"
        }
    });
}

export const getAllBookedToursApi = (accountId) => {
    return axios.get(`${baseURL}/account`, {
        params: {
            accountId: accountId,
        }
    });
}

export const getDetailsBookingApi = (bookingId) => {
    return axios.get(`${baseURL}/id/${bookingId}`);
}

export const getBookedTourForUserApi = (tourId, token) => {
    const config = configAPI(token);
    return axios.get(`${baseURL}/get-by-tour/${tourId}`, config);
}

export const getCancellationBookingRequestApi = (bookingId) => {
    return axios.get(`${baseURL}/cancellation-requested`, { params: { bookingId: bookingId } });
}

export const sendCancellationBookingRequestApi = (accountId, formData) => {
    return axios.post(`${baseURL}/cancellation-requested`, formData, {
        params: {
            accountId: accountId
        }
    });
}

export const deleteCancellationBookingRequestApi = (bookingId) => {
    return axios.delete(`${baseURL}/cancellation-requested`, {
        params: {
            bookingId: bookingId
        }
    });
}

export const checkTourIsBookedByUser = (accountId, subTourId) => {
    const params = new URLSearchParams();
    params.set("accountId", accountId);
    params.set("subTourId", subTourId);
    return axios.get(`${baseURL}/check-booked-tour?${params}`);
}