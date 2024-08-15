import { createSlice } from "@reduxjs/toolkit";

const initMail = {
    fullName: '',
    email: '',
    title: '',
    content: '',
    sender: 'CUSTOMER',
    type: '',
    boxStatus: {
        isStarted: false,
        isConnecting: false,
        isConnected: false,
    },
    isShowMailBox: false,
}

const mailSlice = createSlice({
    name: "mail",
    initialState: initMail,
    reducers: {
        updateMailFields: (state, action) => {
            for (const key in action.payload) {
                if (Object.prototype.hasOwnProperty.call(state, key)) {
                    state[key] = action.payload[key];
                }
            }
        },
        updateMailField: (state, action) => {
            const { key, value } = action.payload;
            if (Object.prototype.hasOwnProperty.call(state, key)) {
                state[key] = value;
            }
        },
        resetMail: (state, action) => {
            state.boxStatus = {
                isStarted: false,
                isConnecting: false,
                isConnected: false,
            };
            state.content = "";
            state.title = "";
            state.email = "";
            state.fullName = "";
            state.type = "";
            state.isShowMailBox = false;
        },
    }
})

export const { resetMail, updateMailField, updateMailFields } = mailSlice.actions;
export default mailSlice.reducer