import { Dispatch } from "react";
import { ActionType } from "../../types/actions";
import { IProfile } from "../../types/profile";

type SetProfileStateDataActionType = (
    dispatch: Dispatch<ActionType>,
    data: IProfile
) => any;
export const SetProfileStateDataAction: SetProfileStateDataActionType = (dispatch, data) => {
    const { id, avatarUrl, fullName, address, phone, dateOfBirth, gender } = data;
    dispatch({
        type: "PROFILE|ADD_DATA",
        payload: { id, avatarUrl, fullName, address, phone, dateOfBirth, gender }
    })
}


type ClearProfileStateDataActionType = (dispatch: Dispatch<ActionType>) => any;
export const ClearProfileStateDataAction: ClearProfileStateDataActionType = (dispatch) => {
    dispatch({ type: "PROFILE|REMOVE_DATA" })
}