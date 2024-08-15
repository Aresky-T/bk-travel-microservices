import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import { IMail, IMailBox, IMailState } from "../../types/mail";
import { getAllMailBoxesApi } from "../../api/mail.api";

const initMailState: IMailState = {
    staff: null,
    mailBoxList: [],
    currentMailBox: null,
    currentMail: null,
}

type ReloadMailStateParams = {
    email: string, limit: number, offset: number
}
const reloadMailState = createAsyncThunk(
    "RELOAD|MAIL_BOXES_LIST",
    async ({ email, limit, offset }: ReloadMailStateParams, thunkApi) => {
        try {
            const res = await getAllMailBoxesApi(email, limit, offset);
            return res.data;
        } catch (error) {
            return thunkApi.rejectWithValue("reload mail context failed!");
        }
    }
)

const mailSlice = createSlice({
    name: "mail",
    initialState: initMailState,
    reducers: {
        SET_MAIL_BOXES_LIST: (state, action: PayloadAction<IMailBox[]>) => {
            state.mailBoxList = action.payload;
        },
        SET_CURRENT_MAIL_BOX: (state, action: PayloadAction<IMailBox>) => {
            if (!state.currentMailBox || state.currentMailBox.id !== action.payload.id) {
                state.currentMailBox = action.payload;
            }
        },
        SET_CURRENT_MAIL: (state, action: PayloadAction<IMail>) => {
            state.currentMail = action.payload
        },
        REMOVE_MAIL_BOXES_LIST: (state, action) => {
            state.mailBoxList = [];
        },
        REMOVE_CURRENT_MAIL_BOX: (state, action) => {
            state.currentMailBox = null;
        },
        REMOVE_CURRENT_MAIL: (state, action) => {
            state.currentMail = null;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(reloadMailState.fulfilled, (state, action) => {
            state.mailBoxList = action.payload;
        });
        builder.addCase(reloadMailState.rejected, (state, action) => {

        })
    }
})

export const {
    REMOVE_CURRENT_MAIL,
    REMOVE_CURRENT_MAIL_BOX,
    REMOVE_MAIL_BOXES_LIST,
    SET_CURRENT_MAIL,
    SET_CURRENT_MAIL_BOX,
    SET_MAIL_BOXES_LIST
} = mailSlice.actions;
export default mailSlice.reducer