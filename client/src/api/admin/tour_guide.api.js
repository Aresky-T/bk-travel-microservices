import axios from "axios";
import { configAPI } from "..";

const tourGuideUrl = "http://localhost:8080/api/v1/tour_guide";

export const getAllTourGuidesApi = (token, fields) => {
    return axios.get(tourGuideUrl, {
        params: fields,
        headers: configAPI(token)["headers"],
    });
}

export const getAllAvailableTourGuides = (token) => {
    return axios.get(`${tourGuideUrl}/available`, configAPI(token));
}
export const getTourGuideDetailApi = (id, token) => {
    return axios.get(`${tourGuideUrl}/${id}`, configAPI(token));
}

export const createTourGuideApi = (form, token) => {
    return axios.post(tourGuideUrl, form, configAPI(token));
}

export const updateTourGuideApi = (id, form, token) => {
    return axios.patch(`${tourGuideUrl}/${id}`, form, configAPI(token));
}

export const deleteTourGuideApi = (id, token) => {
    return axios.delete(`${tourGuideUrl}/${id}`, configAPI(token));
}