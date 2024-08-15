import axios from "axios"
import { ACCOUNT_SERVICE_URL } from "."

export const getProfileApi = (accountId: number) => {
    return axios.get(`${ACCOUNT_SERVICE_URL}/profile`, { params: { accountId } })
}