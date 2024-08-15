import { logout } from "../redux/slices/auth.slice";
import { onCancelChat } from "../redux/slices/chat.slice";
import { removeProfile } from "../redux/slices/profile.slice";
import { ROUTE } from "./route";
import { IoMdHome } from "react-icons/io";
import NotificationPopupContainer from "../containers/notification/popup";
import NotificationIcon from "../components/notification/icon";

export const LINK_POSITIONS = {
    RIGHT: "right",
    CENTER: "center",
    LEFT: "left",
}

export const links = [
    {
        name: 'Trang Chủ',
        title: 'home',
        path: ROUTE.HOME,
        isPublic: true,
        requireLogin: false,
        align: LINK_POSITIONS.CENTER,
        class: 'home-link',
        icon: IoMdHome,
    },
    {
        name: 'Khám phá',
        title: 'tour',
        path: ROUTE.TOUR,
        isPublic: true,
        requireLogin: false,
        align: LINK_POSITIONS.CENTER,
        class: 'tour-link'
    },
    {
        name: 'Tham quan',
        title: 'tourist-attraction',
        path: ROUTE.TOURIST_ATTRACTION,
        isPublic: true,
        requireLogin: false,
        align: LINK_POSITIONS.CENTER,
        class: 'tourist-attraction-link'
    },
    // {
    //     name: 'Liên hệ',
    //     title: 'contact',
    //     path: ROUTE.CONTACT,
    //     isPublic: true,
    //     align: 'center',
    //     class: 'contact-link'
    // },
    {
        name: 'Đăng nhập',
        title: 'login',
        path: ROUTE.LOGIN,
        isPublic: true,
        requireLogin: false,
        align: LINK_POSITIONS.RIGHT,
        class: 'login-link'
    },
    {
        name: 'Đăng ký',
        title: 'register',
        path: ROUTE.REGISTER,
        isPublic: true,
        requireLogin: false,
        align: LINK_POSITIONS.RIGHT,
        class: 'register-link link-btn'
    },
    {
        name: 'Hồ sơ',
        title: 'profile',
        path: ROUTE.PROFILE,
        isPublic: false,
        requireLogin: true,
        align: LINK_POSITIONS.RIGHT,
        class: 'profile-link'
    },
    {
        name: 'Thông báo',
        title: 'notification',
        isPublic: false,
        isDropdown: true,
        requireLogin: true,
        showIcon: true,
        align: LINK_POSITIONS.RIGHT,
        class: 'notification-link',
        icon: NotificationIcon,
        dropdownComponent: NotificationPopupContainer,
    },
    {
        name: 'Đăng xuất',
        title: 'logout',
        isPublic: false,
        align: LINK_POSITIONS.RIGHT,
        requireLogin: true,
        class: 'link-btn',
        action: (dispatch) => {
            dispatch(removeProfile());
            dispatch(onCancelChat());
            dispatch(logout());
        }
    },
]