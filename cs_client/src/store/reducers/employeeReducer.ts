import { Reducer } from "react";
import { IEmployeeState } from "../../types/employee";
import { ActionType } from "../../types/actions";

const employeeReducer: Reducer<IEmployeeState, ActionType> = (state, action) => {
    switch (action.type) {
        case "EMPLOYEE|ADD_DATA":
            return { ...state, data: action.payload }
        case "EMPLOYEE|UPDATE_FIELD":
            let data = state.data;
            if (data) {
                data = { ...data, ...action.payload };
            }
            return { ...state, data };
        case "EMPLOYEE|REMOVE_DATA":
            return { ...state, data: null }
        case "EMPLOYEE|HANDLE_CONNECT_SOCKET":
            return { ...state, isConnected: true }
        case "EMPLOYEE|HANDLE_DISCONNECT_SOCKET":
            return { ...state, isConnected: false }
        default:
            return state;
    }
}

export default employeeReducer