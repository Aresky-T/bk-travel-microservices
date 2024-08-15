import { initMailState } from './../index';
import { IMailState } from "../../types/mail";
import { ActionType } from '../../types/actions';
import { Reducer } from 'react';

const mailReducer: Reducer<IMailState, ActionType> = (state, action) => {
    const { type } = action;
    switch (type) {
        case "MAIL|SET|STAFF_INFO":
            return { ...state, staff: action.payload }
        case "MAIL|SET|MAIL_BOXES_LIST":
            {
                const data = action.payload;
                state.mailBoxList = data;

                if (state.currentMailBox) {
                    const replaceBox = data.filter(box => box.id === state.currentMailBox?.id)[0];
                    state.currentMailBox = replaceBox;
                }

                return { ...state }
            }
        case "MAIL|RELOAD|MAIL_BOXES_LIST":
            return { ...state }
        case "MAIL|SET|CURRENT_MAIL_BOX":
            return { ...state, currentMailBox: action.payload }
        case "MAIL|UPDATE|CURRENT_MAIL_BOX":
            if (state.currentMailBox) {
                state.currentMailBox = { ...state.currentMailBox, ...action.payload }
            }
            return { ...state }
        case "MAIL|SET|CURRENT_MAIL":
            state.currentMail = action.payload
            return { ...state }
        case "MAIL|REMOVE|STAFF_INFO":
            return { ...state, staff: null }
        case "MAIL|REMOVE|MAIL_BOXES_LIST":
            state.mailBoxList = [];
            return { ...state }
        case "MAIL|REMOVE|CURRENT_MAIL_BOX":
            state.currentMailBox = null;
            return { ...state }
        case "MAIL|REMOVE|CURRENT_MAIL":
            state.currentMail = null;
            return { ...state }
        case "MAIL|RESET|MAIL_STATE":
            return { ...initMailState };
        case "MAIL|CLEAR|ALL":
            return { ...state, ...initMailState }
        default:
            return state;
    }
}

export default mailReducer;