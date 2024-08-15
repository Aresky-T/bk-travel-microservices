import { createSlice } from "@reduxjs/toolkit"
import sessionStorageConfig, { getSessionStorage, removeSessionStorage } from "../../config/sessionStorageConfig";

const initBookingInfo = {
    selectedTour: null,
    representative: null,
    touristList: [],
    note: "",
};

const bookingStorage = getSessionStorage("bk_info");

const bookingSlice = createSlice({
    name: "booking",
    initialState: {
        bookingInfo: bookingStorage ?? initBookingInfo,
    },
    reducers: {
        addBookingInfo: (state, action) => {
            const { set } = sessionStorageConfig("bk_info");
            set("selectedTour", action.payload.selectedTour);
            set("representative", action.payload.representative);
            set("touristList", action.payload.touristList);
            set("note", action.payload.note);
            state.bookingInfo = { ...state.bookingInfo, ...action.payload };
        },
        updateBooingInfo: (state, action) => {
            state.bookingInfo = { ...state.bookingInfo, ...action.payload };
        },
        removeBookingInfo: (state, action) => {
            state.bookingInfo.representative = null;
            state.bookingInfo.note = "";
            state.bookingInfo.touristList = [];
            state.bookingInfo.selectedTour = null;
            removeSessionStorage("bk_info");
        },
    },
});

export const { addBookingInfo, removeBookingInfo, updateBooingInfo } = bookingSlice.actions;
export default bookingSlice.reducer;