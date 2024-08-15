import { useSelector } from "react-redux";

const profileSelector = (state) => state.profile;
const authSelector = (state) => state.auth;
const loadingSelector = (state) => state.loading.status;
const mailSelector = (state) => state.mail;
const chatSelector = (state) => state.chat;
const bookingSelector = (state) => state.booking;
const adminSelector = (state) => state.admin;
const notificationSelector = (state) => state.notification;
const markingSelector = (state) => state.marking;

export const useAuth = () => {
    return useSelector(authSelector);
}

export const useProfile = () => {
    return useSelector(profileSelector);
}

export const useLoading = () => {
    return useSelector(loadingSelector);
}

export const useMail = () => {
    return useSelector(mailSelector);
}

export const useChat = () => {
    return useSelector(chatSelector);
}

export const useBooking = () => {
    return useSelector(bookingSelector);
}

export const useAdmin = () => {
    return useSelector(adminSelector);
}

export const useNotification = () => {
    return useSelector(notificationSelector);
}

export const useMarking = () => {
    return useSelector(markingSelector);
}