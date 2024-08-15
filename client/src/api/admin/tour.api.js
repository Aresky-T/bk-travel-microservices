import axios from "axios"
import { BASE_ENDPOINTS } from ".."

const tourURL = BASE_ENDPOINTS.TOUR;

export const getAllToursForAdminApi = (fields) => {
    return axios.get(tourURL, {
        params: fields,
    })
}

export const getAllSubToursForAdminApi = (tourId) => {
    return axios.get(`${tourURL}/sub-tour/tour-id/${tourId}`)
}

export const createTourApi = (data) => {
    return axios.post(tourURL, data);
}

export const createSubTourApi = (tourId, form) => {
    return axios.post(`${tourURL}/sub-tour`, form, {
        params: { tourId: tourId }
    });
}

export const updateTourWithPatchMethodApi = (tourId, fields) => {
    return axios.patch(tourURL, fields, {
        params: { tourId: tourId },
    })
}

export const deleteTourByIdApi = (tourId) => {
    return axios.delete(`${tourURL}/${tourId}`);
}

export const updateSubTourApi = (subTourId, formData) => {
    return axios.patch(`${tourURL}/sub-tour`, formData, {
        params: {
            subTourId: subTourId
        }
    })
}

export const deleteSubTourByIdApi = (subTourId) => {
    return axios.delete(`${tourURL}/sub-tour/${subTourId}`);
}