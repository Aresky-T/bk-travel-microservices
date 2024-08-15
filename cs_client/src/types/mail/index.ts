import { EMailStatus } from "../enum"
import IStaff from "../staff"

export default interface ICustomer {
    id: number,
    email: string,
    fullName: string,
}

export interface IMailReply {
    id: number,
    mailId: number,
    subject: string,
    body: string,
    repliedAt: string,
}

export interface IMail {
    id: number,
    subject: string,
    body: string,
    sentAt: string,
    status: EMailStatus,
    isReplied: boolean,
}

export interface IMailDetails {
    id: number,
    boxId: number,
    subject: string,
    body: string,
    status: EMailStatus,
    sentAt: string,
    customer: ICustomer,
    reply: IMailReply
}

export interface IMailBox {
    id: number,
    customer: ICustomer,
    totalMail: number,
    unrepliedMailCount: number,
    mailList: IMail[],
    createdAt: string,
    updatedAt: string
}

export interface IMailBoxDetails {
    id: number,
    customer: ICustomer,
    totalMail: number,
    unrepliedMailCount: number,
    mailList: IMail[],
    createdAt: string,
    updatedAt: string
}

export interface IMailForm {
    subject: string,
    body: string,
    mailId: number
}

export interface IMailState {
    staff: IStaff | null,
    mailBoxList: IMailBox[],
    currentMailBox: IMailBox | null,
    currentMail: IMail | null,
}