import axios from "axios";
import { BASE_ENDPOINTS } from "..";

const tourAttURL = BASE_ENDPOINTS.TOURIST_ATTRACTION;

export const getAllTouristAttractionsApi = (fields) => {
    return axios.get(tourAttURL, {
        params: fields
    });
}

export const getAllTouristAttractionsWithSearchApi = (search, page, size) => {
    const params = new URLSearchParams();
    params.set("page", page || 0);
    params.set("size", size || 5);

    if (search && search.trim() !== "") params.set("search", search);

    return axios.get(`${tourAttURL}?${params.toString()}`)
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