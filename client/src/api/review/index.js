import axios from "axios"
import { configAPI } from ".."

const baseURL = "http://localhost:8080/api/v1/reviews"

export const reviewBookedTourApi = (comment, stars, tourId, accessToken) => {
    return axios.post(baseURL, { comment, stars, tourId }, configAPI(accessToken))
}

export const getReviewForTourApi = (tourId) => {
    return axios.get(`${baseURL}/tour/${tourId}`)
}

export const getReviewByTourAndAccount = (tourId, accessToken) => {
    return axios.get(`${baseURL}/get-by-tour-and-account`, {
        params: { tourId }, headers: configAPI(accessToken).headers,
    });
}