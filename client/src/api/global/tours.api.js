import axios from 'axios'
import { API_GATEWAY_URL } from '..'

const tourServiceUrl = API_GATEWAY_URL + "/api/v1/tours";

export const getLatestToursApi = (count) => {
    return axios.get(`${tourServiceUrl}/get-latest-tours/${count}`)
}

export const getAllToursByFilterApi = (fields) => {
    return axios.get(`${tourServiceUrl}/sub-tour/filter`, {
        params: fields
    })
}

export const getTourByIdApi = (id) => {
    return axios.get(`${tourServiceUrl}/id/${id}`);
}

export const getDetailsSubTourByIdApi = (id) => {
    return axios.get(`${tourServiceUrl}/sub-tour/id/${id}`);
}

export const getDetailsSubTourByTourCodeApi = (touCode) => {
    return axios.get(`${tourServiceUrl}/sub-tour/tour-code/${touCode}`);
}
