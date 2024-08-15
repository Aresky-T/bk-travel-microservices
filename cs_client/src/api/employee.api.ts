import axios from "axios";
import { configApi } from "./config";
import { API_GATEWAY } from ".";

const employeeUrl = `${API_GATEWAY}/api/v1/employee`

export const getDetailsEmployeeApi = (token: string) => {
    return axios.get(`${employeeUrl}`, configApi(token))
}