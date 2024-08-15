import axios from "axios";
import { BASE_ENDPOINTS } from "..";

const baseUrl = BASE_ENDPOINTS.REVIEW;

export const reviewApi = (formData) => {
    return axios.post(baseUrl, formData);
}

export const checkReviewApi = (accountId, subTourId) => {
    return axios.get(`${baseUrl}/check`, {
        params: {
            accountId: accountId,
            subTourId: subTourId
        }
    })
}

export const getReviewApi = (accountId, subTourId) => {
    return axios.get(`${baseUrl}/one-for-customer`, {
        params: {
            accountId: accountId,
            subTourId: subTourId
        }
    })
}

export const getReviewForTourApi = (tourId, page, size) => {
    const offset = page * size;
    return axios.get(`${baseUrl}/all-reviews-with-statistic-for-tour`, {
        params: {
            tourId: tourId,
            limit: size,
            offset: offset
        }
    })
}