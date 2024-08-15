import { Dispatch } from "react";
import { ActionType } from "../../types/actions";
import { IAuth } from "../../types/auth";
import { localStorageConfig } from "../../config/localStorageConfig";

const storage = localStorageConfig("acc_info");

type StartLoginType = (dispatch: Dispatch<ActionType>) => any;
export const StartLoginAction: StartLoginType = (dispatch) => {
    dispatch({ type: "AUTH|HANDLE_START_LOGIN" })
}

type LoginSuccessType = (
    dispatch: Dispatch<ActionType>,
    data: IAuth
) => any;
export const LoginSuccessAction: LoginSuccessType = (dispatch, data) => {
    const { id, token, type, username, email, role, status } = data;
    // update browser localStorage data
    storage.setData = { id, token, type, username, email, role, status };

    dispatch({
        type: "AUTH|HANDLE_SUCCESS_LOGIN",
        payload: { id, token, type, username, email, role, status },
    });
}

type LoginFailedType = (
    dispatch: Dispatch<ActionType>,
    errorMessage: string
) => any
export const LoginFailedAction: LoginFailedType = (dispatch, errorMessage) => {
    dispatch({
        type: "AUTH|HANDLE_FAILED_LOGIN",
        payload: errorMessage || "Đăng nhập thất bại",
    });
}

type ClearAuthStateDataActionType = (dispatch: Dispatch<ActionType>) => any;
export const ClearAuthStateDataAction: ClearAuthStateDataActionType = (dispatch) => {
    localStorage.clear();
    dispatch({ type: "AUTH|HANDLE_LOGOUT" })
}