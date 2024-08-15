import axios from "axios";
import { BASE_ENDPOINTS } from "..";

const accountURL = BASE_ENDPOINTS.ACCOUNT;

export const getAllAccountsApi = (fields) => {
    return axios.get(accountURL, {
        params: fields
    });
}

export const deleteAccountApi = (accountId) => {
    return axios.delete(accountURL, {
        params: {
            id: accountId
        }
    });
}

export const lockAccountUserApi = (accountId) => {
    return axios.patch(`${accountURL}/activation-status/lock`, null, {
        params: { id: accountId }
    });
}

export const activateAccountUserApi = (accountId) => {
    return axios.patch(`${accountURL}/activation-status/activate`, null, {
        params: { id: accountId }
    });
}

export const updateAccountApi = (accountId, formData) => {
    return axios.patch(accountURL, formData, {
        params: {
            id: accountId
        }
    })
}
