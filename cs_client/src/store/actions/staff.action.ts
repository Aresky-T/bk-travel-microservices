import { Dispatch } from "react";
import { ActionType } from "../../types/actions";
import { getStaffInfoApi } from "../../api/chat.api";
import { EActivationStatus } from "../../types/enum";

type GetStaffInfoType = (
    email: string,
    dispatch: Dispatch<ActionType>
) => any;
export const SetStaffInfoAction: GetStaffInfoType = async (email, dispatch) => {
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

type RemoveStaffInfoType = (dispatch: Dispatch<ActionType>) => any;
export const RemoveStaffInfoAction: RemoveStaffInfoType = (dispatch) => {
    dispatch({ type: "CHAT|REMOVE|STAFF_INFO" });
}

type SetStaffStatusType = (
    status: EActivationStatus,
    dispatch: Dispatch<ActionType>
) => any;
export const SetStaffStatusAction: SetStaffStatusType = (status, dispatch) => {
    dispatch({
        type: "CHAT|SET|STAFF_STATUS",
        payload: status,
    });
}