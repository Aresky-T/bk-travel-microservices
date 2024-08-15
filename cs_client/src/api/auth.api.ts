import axios from "axios"
import { API_GATEWAY } from "."

const authUrl = `${API_GATEWAY}/api/v1/auth`

export const loginEmployeeApi = (formData: { username: string, password: string }) => {
    return axios.post(`${authUrl}/login`, formData)
}

export const validateTokenApi = (token: string) => {
    return axios.get(`${authUrl}/validate-token`, {
        params: {
            accessToken: token
        }
    })
}