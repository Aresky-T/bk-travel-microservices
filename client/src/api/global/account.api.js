import axios from "axios";
import { BASE_ENDPOINTS } from "..";

const baseUrl = BASE_ENDPOINTS.ACCOUNT;

export const updatePasswordApi = (formData, username) => {
    return axios.patch(`${baseUrl}/update-password`, formData, {
        params: {
            username: username
        }
    });
}