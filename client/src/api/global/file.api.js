import axios from "axios";

// const fileURL = "https://gr1travelbackend-production.up.railway.app/files";
const fileURL = "http://localhost:8080/api/v1/files"

export const uploadMultipartFileApi = (files, token) => {

    const config = {
        headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: `Bearer ${token}`
        }
    }
    return axios.post(`${fileURL}/cloudinary/upload`, files, config);
} 


export const uploadSingleFileApi = (file) => {
    const CLOUD_NAME = "dmdozqjdg"
    const FOLDER_NAME = 'bk_travel_image';
    const UPLOAD_PRESET = 'bk_travel_secret';

    const formData = new FormData();
    formData.append('folder', FOLDER_NAME);
    formData.append('upload_preset', UPLOAD_PRESET);
    formData.append('file', file);
    return axios.post(`https://api.cloudinary.com/v1_1/${CLOUD_NAME}/image/upload`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}