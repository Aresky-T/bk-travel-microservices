import { createSlice } from "@reduxjs/toolkit";

const loadingSlice = createSlice({
    name: 'loading',
    initialState: {
        status: false,
        ellipsis: false,
    },
    reducers: {
        onLoading: (state) => {
            state.status = true;
        },
        offLoading: (state) => {
            state.status = false;
        },
        onEllipsis: (state) => {
            state.ellipsis = true;
        },
        offEllipsis: (state) => {
            state.ellipsis = false;
        }
    }
})

export const { offLoading, onLoading, offEllipsis, onEllipsis } = loadingSlice.actions;
export default loadingSlice.reducer;