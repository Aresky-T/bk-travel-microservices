export const BROKER_EMPLOYEE_STATUS = (employee_id: number) => (`/topic/chat/employee-status/${employee_id}`);

export const BROKER_CHAT_CONVERSATION = (conversation_id:number) => (`/topic/employee/chat/conversation/${conversation_id}`);
export const BROKER_CHAT_CONVERSATIONS_LIST =  (employee_id: number) => (`/topic/chat/conversations/${employee_id}`);

export const BROKER_MAIL_BOXES_LIST = (employee_id:number) => (`/topic/mail/employee-mailboxes/${employee_id}`);
export const BROKER_MAIL_BOX = (mailbox_id:number) => (`/topic/mail/employee-mailbox/${mailbox_id}`);