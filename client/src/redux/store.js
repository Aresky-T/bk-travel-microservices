import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./slices/auth.slice"
import profileReduce from "./slices/profile.slice"
import loadingReducer from "./slices/loading.slice"
import mailReducer from "./slices/mail.slice"
import chatReducer from "./slices/chat.slice"
import bookingReducer from "./slices/booking.slice"
import adminReducer from "./slices/admin.slice";
import notificationReducer from "./slices/notification.slice";
import markingReducer from "./slices/marking.slice";

const rootReducer = {
    auth: authReducer,
    profile: profileReduce,
    loading: loadingReducer,
    mail: mailReducer,
    chat: chatReducer,
    booking: bookingReducer,
    admin: adminReducer,
    notification: notificationReducer,
    marking: markingReducer,
}

const store = configureStore({
    reducer: rootReducer,
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware({
            serializableCheck: false
        })
})

export default store;