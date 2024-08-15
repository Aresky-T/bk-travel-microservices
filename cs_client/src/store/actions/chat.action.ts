import { Dispatch } from "react";
import { ActionType } from "../../types/actions";
import { getAllConversationsForStaffApi, getStaffInfoApi } from "../../api/chat.api";
import { IConversation } from "../../types/chat";
import { EActivationStatus } from "../../types/enum";


type FetchAllConversationsType = (
    dispatch: Dispatch<ActionType>,
    staffId: number,
    limit: number,
    offset: number
) => any

export const FetchAllConversationsAction: FetchAllConversationsType = async (dispatch, staffId, limit, offset) => {
    try {
        const res = await getAllConversationsForStaffApi(staffId, limit, offset);
        const conversations: IConversation[] = res.data.map((item: any) => {
            const conversation: IConversation = {
                id: item.id,
                latestCustomerMessage: item.latestCustomerMessage,
                newCustomerMessageCount: item.newCustomerMessageCount,
                createdAt: item.createdAt,
                updatedAt: item.updatedAt,
                customer: item.customer,
                messages: [],
                latestMessage: item.latestMessage,
            };

            return conversation;
        });

        dispatch &&
            dispatch({
                type: "CHAT|SET|CONVERSATION_LIST",
                payload: conversations,
            });
    } catch (error) {
        console.log(error)
    }
}

type UpdateCurrentConversationActionType = (
    data: IConversation,
    dispatch: Dispatch<ActionType>
) => any;
export const UpdateCurrentConversationAction: UpdateCurrentConversationActionType = (data, dispatch) => {
    dispatch({
        type: "CHAT|UPDATE|CURRENT_CONVERSATION",
        payload: data
    })
}

type UpdateConversationListActionType = (
    data: IConversation,
    dispatch: Dispatch<ActionType>
) => any;

export const UpdateConversationListAction: UpdateConversationListActionType = (data, dispatch) => {
    dispatch({
        type: "CHAT|UPDATE|CONVERSATION_LIST|ONE",
        payload: data
    })
}

type ClearChatStateDataActionType = (dispatch: Dispatch<ActionType>) => any;
export const ClearChatStateDataAction: ClearChatStateDataActionType = (dispatch) => {
    dispatch({ type: "CHAT|CLEAR|ALL" })
}

type SetChatStaffInfoActionType = (
    email: string,
    dispatch: Dispatch<ActionType>
) => any;
export const SetChatStaffInfoAction: SetChatStaffInfoActionType = async (email, dispatch) => {
    try {
        const res = await getStaffInfoApi(email);
        const data = res.data;
        dispatch &&
            dispatch({
                type: "CHAT|SET|STAFF_INFO",
                payload: {
                    id: data.id,
                    email: data.email,
                    fullName: data.fullName,
                    avatarUrl: data.avatarUrl,
                    status:
                        data.status === "ONLINE"
                            ? EActivationStatus.ONLINE
                            : EActivationStatus.OFFLINE,
                },
            });
    } catch (error) { }
}

type RemoveChatStaffInfoActionType = (dispatch: Dispatch<ActionType>) => any;
export const RemoveChatStaffInfoAction: RemoveChatStaffInfoActionType = (dispatch) => {
    dispatch({ type: "CHAT|REMOVE|STAFF_INFO" });
}

type SetChatStaffStatusActionType = (
    status: EActivationStatus,
    dispatch: Dispatch<ActionType>
) => any;
export const SetChatStaffStatusAction: SetChatStaffStatusActionType = (status, dispatch) => {
    dispatch({
        type: "CHAT|SET|STAFF_STATUS",
        payload: status,
    });
}