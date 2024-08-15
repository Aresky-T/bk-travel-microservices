import axios from "axios";

const tourAttURL = "http://localhost:8080/api/v1/tourist-attraction"

export const getAllTouristAttractionsApi = (fields) => {
    return axios.get(`${tourAttURL}`, {
        params: fields
    });
}

export const getDataBySearchApi = (fields) => {
    return axios.get(`${tourAttURL}/search`, {
        params: fields
    })
}

export const getTouristAttractionDetailsApi = (id) => {
    return axios.get(`${tourAttURL}/id/${id}`)
}

export const getLatestTouristAttractionsApi = (count) => {
    return axios.get(`${tourAttURL}/get-latest-tourist-attractions/${count}`)
}

export const getBlogDetailsApi = (blogId) => {
    return axios.get(`${tourAttURL}/blog/id/${blogId}`);
}