import axios from "axios";
import { BASE_ENDPOINTS } from "..";

const baseUrl = BASE_ENDPOINTS.MARKING;

export const getAllMarkedTourApi = (accountId) => {
    return axios.get(`${baseUrl}/all`, {
        params: { accountId }
    })
}

export const checkMarkedTourApi = (accountId, subTourId) => {
    return axios.get(`${baseUrl}/check`, {
        params: {
            accountId, subTourId
        }
    });
}

export const markTourApi = (accountId, subTourId) => {
    return axios.post(baseUrl, {
        accountId, subTourId
    });
}

export const unmarkTourApi = (accountId, subTourId) => {
    return axios.delete(baseUrl, {
        params: {
            accountId, subTourId
        }
    })
}