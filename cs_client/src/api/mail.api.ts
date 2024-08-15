import axios from "axios"
import { configApi } from "./config";
import { IMail, IMailBox, IMailDetails, IMailForm } from "../types/mail";
import { MAIL_SERVICE_URL } from ".";

const mailUrl = MAIL_SERVICE_URL;

export const getAllMailBoxesApi = (email: string, limit: number, offset: number) => {
    const params = {
        staffEmail: email,
        limit: limit,
        offset: offset
    }

    return axios.get<IMailBox[]>(`${mailUrl}/boxes`, { params })
}

export const getAllMailsApi = (boxId: number, limit: number, offset: number) => {
    const params = {
        boxId: boxId,
        limit: limit,
        offset: offset
    }
    return axios.get<IMail[]>(`${mailUrl}/all`, { params })
}

export const getMailBoxByIdApi = (token: string, id: number) => {
    return axios.get(`${mailUrl}/mail-box/${id}`, configApi(token));
}

export const sendMailReplyToCustomerApi = (mail: IMailForm, token: string) => {
    return axios.post(`${mailUrl}/send-to-customer`, mail, configApi(token));
}

export const getDetailsMailApi = (staffEmail: string, mailId: number) => {
    return axios.get<IMailDetails>(`${mailUrl}/details-for-staff`, {
        params: {
            staffEmail: staffEmail,
            mailId: mailId
        }
    });
}

export const readMailApi = (staffEmail: string, mailId: number) => {
    return axios.patch(`${mailUrl}/read`, null, {
        params: {
            staffEmail: staffEmail,
            mailId: mailId
        }
    })
}

export const replyMailApi = (formData: IMailForm) => {
    return axios.post(`${mailUrl}/reply`, formData)
}

export const deleteMailByIdApi = (token: string, mailId: number) => {
    return axios.delete(`${mailUrl}/${mailId}`, configApi(token));
}

export const deleteMailBoxByIdApi = (mailBoxId: number) => {
    return axios.delete(`${mailUrl}/box`, {
        params: {
            boxId: mailBoxId
        }
    })
}

export const checkStaffPermissionApi = (email: string) => {
    return axios.get(`${mailUrl}/staff/permission`, {
        params: { email: email }
    })
}

export const getStaffInfoApi = (email: string) => {
    return axios.get(`${mailUrl}/staff/details`, {
        params: { email: email }
    })
}