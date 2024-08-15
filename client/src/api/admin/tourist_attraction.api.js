import axios from "axios"

const baseUrl = "http://localhost:8080/api/v1/tourist-attraction"

export const getAllTouristAttractionsApi = (pagination) => {
    return axios.get(baseUrl, {
        params: pagination
    });
}

export const getDetailsBlogApi = (blogId) => {
    return axios.get(`${baseUrl}/blog/id/${blogId}`);
}

export const createTouristAttractionApi = (form) => {
    return axios.post(baseUrl, form);
}

export const updateTouristAttractionApi = (touristAttractionId, formData) => {
    return axios.put(baseUrl, formData, {
        params: {
            id: touristAttractionId
        }
    });
}

export const deleteTouristAttractionApi = (touristAttractionId) => {
    return axios.delete(baseUrl, {
        params: {
            id: touristAttractionId,
        }
    })
}

export const createTouristAttractionBlogApi = (formData) => {
    return axios.post(`${baseUrl}/blog`, formData);
}

export const updateTouristAttractionBlogApi = (blogId, formData) => {
    return axios.put(`${baseUrl}/blog`, formData, {
        params: { blogId }
    });
}

export const updateTouristAttractionBlogItemApi = (blogItemId, formData) => {
    return axios.put(`${baseUrl}/blog`, formData, {
        params: { blogItemId }
    });
}