import axios from "axios";
import { BASE_ENDPOINTS } from "..";

// const profileURL = "https://gr1travelbackend-production.up.railway.app/api/v1/profile";
const baseUrl = `${BASE_ENDPOINTS.ACCOUNT}/profile`;

export const getProfileApi = (accountId) => {
    return axios.get(baseUrl, {
        params: {
            accountId: accountId
        }
    });
}

export const updateProfileApi = (accountId, form) => {
    return axios.put(baseUrl, form, {
        params: {
            accountId: accountId
        }
    });
}

export const updateAvatarApi = (accountId, newAvatar) => {
    return axios.patch(`${baseUrl}/avatar`, null, {
        params: { accountId: accountId, newAvatar: newAvatar },
    })
}