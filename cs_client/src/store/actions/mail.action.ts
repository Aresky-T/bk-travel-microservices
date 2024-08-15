import { Dispatch } from "react";
import { ActionType } from "../../types/actions";
import IStaff from "../../types/staff";
import { IMailBox } from "../../types/mail";
import { getAllMailBoxesApi } from "../../api/mail.api";

type FetchAllMailBoxesActionType = (
    dispatch: Dispatch<ActionType>,
    email: string,
    limit?: number,
    offset?: number
) => any;
export const FetchAllMailBoxesAction: FetchAllMailBoxesActionType = (dispatch, email, limit, offset) => {
    getAllMailBoxesApi(email, limit || 10, offset || 0)
        .then((res) => {
            SetMailBoxesListAction(dispatch, res.data);
        })
        .catch((err) => { });
}

type SetMailStaffInfoActionType = (
    dispatch: Dispatch<ActionType>,
    data: IStaff
) => any;
export const SetMailStaffInfoAction: SetMailStaffInfoActionType = (dispatch, data) => {
    dispatch({
        type: "MAIL|SET|STAFF_INFO",
        payload: data
    })
}

type SetMailBoxListActionType = (
    dispatch: Dispatch<ActionType>,
    data: IMailBox[]
) => any;
export const SetMailBoxesListAction: SetMailBoxListActionType = (dispatch, data) => {
    dispatch({
        type: "MAIL|SET|MAIL_BOXES_LIST",
        payload: data
    })
}

type RemoveCurrentMailActionType = (dispatch: Dispatch<ActionType>) => any;
export const RemoveCurrentMailAction: RemoveCurrentMailActionType = (dispatch) => {
    dispatch({ type: "MAIL|REMOVE|CURRENT_MAIL" });
}

type RemoveCurrentMailBoxActionType = (dispatch: Dispatch<ActionType>) => any;
export const RemoveCurrentMailBoxAction: RemoveCurrentMailBoxActionType = (dispatch) => {
    dispatch({ type: "MAIL|REMOVE|CURRENT_MAIL_BOX" });
}

type ResetMailFormActionType = (dispatch: Dispatch<ActionType>) => any;
export const ResetMailFormAction: ResetMailFormActionType = (dispatch) => {
    dispatch({ type: "MAIL|RESET|MAIL_FORM" });
}

type ClearMailStateDataType = (dispatch: Dispatch<ActionType>) => any;
export const ClearMailStateDataAction: ClearMailStateDataType = (dispatch) => {
    dispatch({ type: "MAIL|CLEAR|ALL" });
}