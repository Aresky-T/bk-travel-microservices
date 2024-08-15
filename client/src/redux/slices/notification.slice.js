import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getAllNotificationsForUserApi } from "../../api/notification/notification.api";

export const getNotificationsThunk = createAsyncThunk(
    "notification/getNotifications",
    async function ({ accountId, page, prevPage, size }, { rejectWithValue }) {
        if (page < 1) page = 1;

        if (page === prevPage) {
            return rejectWithValue("error");
        }

        try {
            const response = await getAllNotificationsForUserApi(accountId, page, size);
            return { ...response.data, prevPage: page };
        } catch (error) {
            return rejectWithValue("error");
        }
    }
)

const notificationSlice = createSlice({
    name: "notification",
    initialState: {
        newNotificationCount: 0,
        notifications: [],
        currentNotification: null,
        isShowPopup: false,
        counts: {
            ALL: 0,
            AUTH: 0,
            TOUR: 0,
            BOOKING: 0,
            PAYMENT: 0,
            MAIL: 0,
        },
        pageable: {
            page: 1,
            prevPage: 0,
            size: 10,
            totalPages: 1,
            totalElements: 0,
        }
    },
    reducers: {
        onShowNotificationPopup: (state, action) => {
            state.isShowPopup = true;
            state.newNotificationCount = 0;
        },
        onHiddenNotificationPopup: (state, action) => {
            state.isShowPopup = false;
        },
        onUpdateNewNotificationCount: (state, action) => {
            state.newNotificationCount = action.payload;
        },
        onIncreaseNotificationPage: (state, action) => {
            state.pageable.page++;
        },
        onDecreaseNotificationPage: (state, action) => {
            state.pageable.page--;
        },
        onUpdateNotificationPageable: (state, action) => {
            state.pageable.page = action.payload.page;
            state.pageable.totalPages = action.payload.totalPages;
            state.pageable.totalElements = action.payload.totalElements;
        },
        onSetCurrentNotification: (state, action) => {
            state.currentNotification = action.payload;
        },
        onRemoveCurrentNotification: (state, action) => {
            state.currentNotification = null;
        },
        onViewNotifications: (state, action) => {
            state.notifications.forEach(item => {
                item.isNew = false;
            })
        }
    },
    extraReducers: (builder) => {
        builder.addCase(getNotificationsThunk.fulfilled, (state, action) => {
            const data = action.payload;
            const ids = new Set(state.notifications.map(item => item.id));
            state.pageable.page = data.page;
            state.pageable.prevPage = data.prevPage;
            state.pageable.totalElements = data.totalElements;
            state.pageable.totalPages = data.totalPages;

            for (const key in data.counts) {
                if (Object.hasOwnProperty.call(state.counts, key)) {
                    state.counts[key] = data.counts[key];
                    state.counts.ALL += data.counts[key];
                }
            }

            data.notifications.forEach(item => {
                if (!ids.has(item.id)) {
                    state.notifications.push(item);
                }
            });
        });

        builder.addCase(getNotificationsThunk.rejected, (state, action) => {

        })
    }
})

export default notificationSlice.reducer;
export const {
    onHiddenNotificationPopup,
    onShowNotificationPopup,
    onUpdateNewNotificationCount,
    onDecreaseNotificationPage,
    onIncreaseNotificationPage,
    onUpdateNotificationPageable,
    onRemoveCurrentNotification,
    onSetCurrentNotification,
    onViewNotifications,
} = notificationSlice.actions;