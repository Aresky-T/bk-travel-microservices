import axios from "axios"
import { BASE_ENDPOINTS } from ".."

const bookingURL = BASE_ENDPOINTS.BOOKING;

export const getAllBookedToursApi = (page, size, sort) => {
    return axios.get(bookingURL, {
        params: {
            page: page,
            size: size,
            sort: sort
        }
    });
}

export const getAllBookingsApi = (page, size, sort) => {
    return axios.get(bookingURL, {
        params: {
            page: page,
            size: size,
            sort: sort
        }
    });
}

export const getBookingDetailsByIdApi = (bookingId) => {
    return axios.get(`${bookingURL}/id/${bookingId}`);
}

export const updateBookingStatusApi = (bookingId, formData) => {
    return axios.patch(`${bookingURL}/status`, formData, {
        params: { bookingId: bookingId }
    })
}

export const getCancellationRequestedApi = (bookingId) => {
    return axios.get(`${bookingURL}/cancellation-requested`, {
        params: { bookingId: bookingId }
    })
}

export const approveCancellationRequestedApi = (bookingId) => {
    return axios.patch(`${bookingURL}/cancellation-requested/approve`, null, {
        params: { bookingId: bookingId }
    });
}

export const rejectCancellationRequestedApi = (bookingId) => {
    return axios.patch(`${bookingURL}/cancellation-requested/reject`, null, {
        params: { bookingId: bookingId }
    });
}

export const changeStatusBookedTourApi = (form) => {
    return axios.put(`${bookingURL}/change-status`, form);
}