import { IAuth } from "../auth";
import { IConversation } from "../chat";
import { IEmployee } from "../employee";
import { EActivationStatus } from "../enum";
import { IMail, IMailBox, IMailForm } from "../mail";
import { IProfile } from "../profile";
import IStaff from "../staff";

export type MailAction = {
    type: "MAIL|SET|STAFF_INFO",
    payload: IStaff
} | {
    type: "MAIL|SET|MAIL_BOXES_LIST",
    payload: IMailBox[]
} | {
    type: "MAIL|RELOAD|MAIL_BOXES_LIST",
    payload: IMailBox[]
} | {
    type: "MAIL|SET|CURRENT_MAIL_BOX",
    payload: IMailBox
} | {
    type: "MAIL|UPDATE|CURRENT_MAIL_BOX",
    payload: Partial<IMailBox>
} | {
    type: "MAIL|SET|CURRENT_MAIL",
    payload: IMail
} | {
    type: "MAIL|UPDATE|MAIL_FORM",
    payload: Partial<IMailForm>
} | {
    type: "MAIL|RESET|MAIL_STATE"
} | {
    type: "MAIL|RESET|MAIL_FORM",
} | {
    type: "MAIL|REMOVE|STAFF_INFO"
} | {
    type: "MAIL|REMOVE|MAIL_BOXES_LIST",
} | {
    type: "MAIL|REMOVE|CURRENT_MAIL_BOX",
} | {
    type: "MAIL|REMOVE|CURRENT_MAIL",
} | {
    type: "MAIL|CLEAR|ALL"
};

export type ChatAction = {
    type: "CHAT|SET|STAFF_INFO",
    payload: IStaff
} | {
    type: "CHAT|SET|STAFF_STATUS",
    payload: EActivationStatus
} | {
    type: "CHAT|SET|CONVERSATION_LIST",
    payload: IConversation[]
} | {
    type: "CHAT|UPDATE|CONVERSATION_LIST|ONE",
    payload: IConversation
} | {
    type: "CHAT|UPDATE|CONVERSATION_LIST|MULTIPLE",
    payload: IConversation[]
} | {
    type: "CHAT|SET|CURRENT_CONVERSATION",
    payload: IConversation
} | {
    type: "CHAT|UPDATE|CURRENT_CONVERSATION",
    payload: IConversation
} | {
    type: "CHAT|CLEAR|CONVERSATION_LIST"
} | {
    type: "CHAT|REMOVE|CURRENT_CONVERSATION"
} | {
    type: "CHAT|REMOVE|STAFF_INFO"
} | {
    type: "CHAT|CLEAR|ALL"
};

export type AuthAction = {
    type: "AUTH|HANDLE_START_LOGIN"
} | {
    type: "AUTH|HANDLE_SUCCESS_LOGIN",
    payload: IAuth
} | {
    type: "AUTH|HANDLE_FAILED_LOGIN",
    payload: string
} | {
    type: "AUTH|HANDLE_LOGOUT"
};

export type EmployeeAction = {
    type: "EMPLOYEE|ADD_DATA",
    payload: IEmployee,
} | {
    type: "EMPLOYEE|UPDATE_FIELD",
    payload: Partial<IEmployee>
} | {
    type: "EMPLOYEE|REMOVE_DATA"
} | {
    type: "EMPLOYEE|HANDLE_CONNECT_SOCKET"
} | {
    type: "EMPLOYEE|HANDLE_DISCONNECT_SOCKET"
};

export type ProfileAction = {
    type: "PROFILE|ADD_DATA",
    payload: IProfile,
} | {
    type: "PROFILE|REMOVE_DATA"
}

export type ActionType = MailAction | ChatAction | AuthAction | ProfileAction | EmployeeAction