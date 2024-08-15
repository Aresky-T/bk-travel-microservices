import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getAllMarkedTourApi } from "../../api/user/marking.api";

const initialState = {
    markedTours: [],
}

export const fetchAllMarkedTourThunk = createAsyncThunk(
    "marking/fetchAllMarkedTours",
    async function (accountId, { rejectWithValue }) {
        try {
            const res = await getAllMarkedTourApi(accountId);
            return res.data;
        } catch (error) {
            return rejectWithValue("Failed to fetch all MarkedTours!")
        }
    }
)

const markingSlice = createSlice({
    name: "marking",
    initialState: initialState,
    reducers: {
        onMarkTour: (state, action) => {
            const tour = action.payload;
            state.markedTours.push(tour);
        },
        onUnmarkTour: (state, action) => {
            const tour = action.payload;
            state.markedTours = state.markedTours.filter(item => item.tourCode !== tour.tourCode);
        },
    },
    extraReducers: (builder) => {
        builder.addCase(fetchAllMarkedTourThunk.fulfilled, (state, action) => {
            state.markedTours = action.payload;
        });

        builder.addCase(fetchAllMarkedTourThunk.rejected, (state, action) => {
            state.markedTours = [];
        });
    }
})

export const { onMarkTour, onUnmarkTour } = markingSlice.actions;
export default markingSlice.reducer;