import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getProfileApi } from "../../api/global/profile.api";

const initProfile = {
    address: null,
    avatarUrl: null,
    dateOfBirth: null,
    fullName: null,
    gender: null,
    id: null,
    phone: null,
    account: {}
}

export const getProfileThunk = createAsyncThunk(
    'profile/getData',
    // handleGetProfileData,
    async (accountId, { rejectWithValue }) => {
        try {
            const response = await getProfileApi(accountId);
            return response.data;
        } catch (error) {
            return rejectWithValue("error");
        }
    }
)

const profileSlice = createSlice({
    name: 'profile',
    initialState: initProfile,
    reducers: {
        setProfile: (state, action) => {
            for (const key in state) {
                if (Object.prototype.hasOwnProperty.call(action.payload, key)) {
                    state[key] = action.payload[key];
                }
            }
        },
        removeProfile: (state, action) => {
            state = initProfile;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(getProfileThunk.fulfilled, (state, action) => {
            for (const key in state) {
                if (Object.prototype.hasOwnProperty.call(action.payload, key)) {
                    state[key] = action.payload[key];
                }
            }
        });
        builder.addCase(getProfileThunk.rejected, (state, action) => {
        })
    }
})

export const {
    setProfile,
    removeProfile,
} = profileSlice.actions;
export default profileSlice.reducer;