import axios from "axios";
import { onLoading } from "../../redux/slices/loading.slice";
import { BASE_ENDPOINTS, configAPI } from "..";

// const authURL = 'https://gr1travelbackend-production.up.railway.app/api/v1/auth'
const baseUrl = BASE_ENDPOINTS.AUTH;

export const loginUserApi = (data) => {
    return axios.post(`${baseUrl}/login`, data);
}

export const loginAdminApi = (data) => {
    return axios.post(`${baseUrl}/login`, data);
}

export const registerUserApi = (data, dispatch) => {
    dispatch(onLoading());
    return axios.post(`${baseUrl}/signup`, data);
}

export const validateTokenApi = (accessToken) => {
    return axios.get(`${baseUrl}/validate-token`, {
        params: {
            accessToken: accessToken
        }
    })
}

export const validateAccountApi = (accessToken) => {
    return axios.get(`${baseUrl}/validate-account`, configAPI(accessToken));
}

export const forgotPasswordApi = (email, dispatch) => {
    dispatch(onLoading())
    return axios.patch(`${baseUrl}/forgot-password`, null, {
        params: {
            email: email
        }
    })
}