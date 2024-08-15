import { createSlice } from "@reduxjs/toolkit";
import { createCustomStorage, removeLocalStorage } from "../../config/localStorageConfig";

const account = JSON.parse(localStorage.getItem("accountInfo"));

const initState = {
    id: account?.id,
    accessToken: account?.token,
    type: account?.type,
    username: account?.username,
    email: account?.email,
    role: account?.role,
    status: account?.status
};

const authSlice = createSlice({
    name: "auth",
    initialState: initState,
    reducers: {
        saveAccountInfo: (state, action) => {
            const payload = action.payload;
            const { id, token, type, username, email, role, status } = payload;

            // update auth state
            state.id = id;
            state.accessToken = token;
            state.type = type;
            state.username = username;
            state.email = email;
            state.status = status;
            state.role = role;

            // update data of localStorage in browser
            const storage = createCustomStorage("accountInfo");
            storage.set("id", id);
            storage.set("token", token);
            storage.set("type", type);
            storage.set("username", username);
            storage.set("email", email);
            storage.set("role", role);
            storage.set("status", status);
        },
        logout: (state) => {
            removeLocalStorage("accountInfo");
            sessionStorage.clear();
            state.id = null;
            state.accessToken = null;
            state.role = null;
            state.type = null;
            state.username = null;
            state.email = null;
            state.status = null;
        }
    }
})

export const {
    saveAccountInfo,
    logout
} = authSlice.actions;
export default authSlice.reducer;