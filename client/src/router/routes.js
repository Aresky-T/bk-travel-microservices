import HomeAdminContainer from "../containers/admin/Home/HomeAdminContainer";
import TourManagerContainer from "../containers/admin/Tour/TourManagerContainer";
import LoginAdminPage from "../pages/Global/LoginAdminPage";
import Layout from "../components/layout/Layout";
import HomePage from "../pages/Global/HomePage";
import TourPage from "../pages/Global/ToursPage";
import TourDetailsPage from "../pages/Global/TourDetailsPage";
import LoginPage from "../pages/Global/LoginPage";
import RegisterPage from "../pages/Global/RegisterPage";
import NotFoundPage from "../pages/NotFoundPage/NotFoundPage";
import { ROLE } from "../constant/role";
import { ROUTE } from "../constant/route";
import PrivateRoute from "./PrivateRoute";
import PublicRoute from "./PublicRoute";
import ProfilePage from "../pages/User/ProfilePage";
import LayoutAdmin from "../components/layout/LayoutAdmin";
import TouristAttractionPage from "../pages/Global/TouristAttractionPage";
import TouristAttractionManagerContainer from "../containers/admin/TouristAttraction/TouristAttractionManagerContainer";
import BlogPage from "../pages/Global/BlogPage";
import BookingPage from "../pages/Global/BookingPage";
import ForgotPasswordPage from "../pages/Global/ForgotPasswordPage";
import TourDetailsAdminContainer from "../containers/admin/Tour/TourDetailsAdminContainer";
import TourGuideDetailsContainer from "../containers/admin/TourGuide/TourGuideDetailsContainer";
import TourGuideCreateContainer from "../containers/admin/TourGuide/TourGuideCreateContainer";
import AccountManagerContainer from "../containers/admin/Account/AccountManagerContainer";
import BookingManagerContainer from "../containers/admin/Booking/BookingManagerContainer";
import CheckoutPage from "../pages/User/CheckoutPage";
import StaffManagerContainer from "../containers/admin/Staff";
import TouristAttractionDetailsContainer from "../containers/global/TouristAttraction/TouristAttractionDetailsContainer";
import NotificationPage from "../pages/User/NotificationPage";
import PaymentPage from "../pages/User/PaymentPage";

export const routes = [
    {
        path: ROUTE.ADMIN_HOME,
        element: <LayoutAdmin />,
        title: 'Admin',
        isPrivate: true,
        roles: [ROLE.ADMIN],
        children: [
            { path: '', element: <HomeAdminContainer />, title: "Home" },
            { path: ROUTE.ADMIN_TOUR_MANAGER, element: <TourManagerContainer /> },
            { path: ROUTE.ADMIN_TOURIST_ATTRACTION_MANAGER, element: <TouristAttractionManagerContainer /> },
            { path: ROUTE.ADMIN_BOOKING_MANAGER, element: <BookingManagerContainer /> },
            { path: ROUTE.ADMIN_ACCOUNT_MANAGER, element: <AccountManagerContainer /> },
            // { path: ROUTE.ADMIN_TOURIST_ATTRACTION_DETAIL, element: <TouristAttractionDetailAdminContainer /> },
            // { path: ROUTE.ADMIN_TOURIST_ATTRACTION_CREATE, element: <TouristAttractionCreateContainer /> },
            { path: ROUTE.ADMIN_TOUR_DETAILS, element: <TourDetailsAdminContainer /> },
            // { path: ROUTE.TOUR_CREATE, element: <TourCreateContainer /> },
            // { path: ROUTE.ADMIN_STAFF_MANAGER, element: <TourGuideManagerContainer /> },
            { path: ROUTE.ADMIN_TOUR_GUIDE_DETAILS, element: <TourGuideDetailsContainer /> },
            { path: ROUTE.ADMIN_TOUR_GUIDE_CREATE, element: <TourGuideCreateContainer /> },
            { path: ROUTE.ADMIN_STAFF_MANAGER, element: <StaffManagerContainer /> }
        ],
    },
    {
        path: ROUTE.LAYOUT,
        element: <Layout />,
        isPrivate: false,
        children: [
            { path: ROUTE.HOME, element: <HomePage /> },
            { path: ROUTE.TOUR, element: <TourPage /> },
            { path: ROUTE.TOUR_DETAIL, element: <TourDetailsPage /> },
            { path: ROUTE.TOURIST_ATTRACTION, element: <TouristAttractionPage /> },
            { path: ROUTE.TOURIST_ATTRACTION_DETAIL, element: <TouristAttractionDetailsContainer /> },
            { path: ROUTE.TOURIST_ATTRACTION_BLOG, element: <BlogPage /> },
            { path: ROUTE.LOGIN, element: <LoginPage /> },
            { path: ROUTE.REGISTER, element: <RegisterPage /> },
            { path: ROUTE.BOOKING, element: <BookingPage /> },
            { path: ROUTE.FORGOT_PASSWORD, element: <ForgotPasswordPage /> },
            // { path: ROUTE.CONTACT, element: <ContactPage /> }
        ]
    },
    {
        path: ROUTE.LAYOUT,
        element: <Layout />,
        isPrivate: true,
        roles: [ROLE.USER],
        children: [
            { path: ROUTE.PROFILE, element: <ProfilePage /> },
            { path: ROUTE.CHECKOUT, element: <CheckoutPage /> },
            { path: ROUTE.NOTIFICATION, element: <NotificationPage /> },
            { path: ROUTE.PAYMENT, element: <PaymentPage /> }
        ]
    },
    { path: ROUTE.ADMIN_LOGIN, element: <LoginAdminPage />, isPrivate: false },
    { path: '*', element: <NotFoundPage />, title: '404', is404: true }
].map((route) => {
    if (route.isPrivate) {
        return {
            ...route,
            element: (
                <PrivateRoute roles={route.roles}>
                    {route.element}
                </PrivateRoute>
            )
        }
    }
    return {
        ...route,
        element: (
            <PublicRoute title={route.title}>
                {route.element}
            </PublicRoute>
        )
    }
})