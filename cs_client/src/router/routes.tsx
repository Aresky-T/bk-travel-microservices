import { RouteObject } from "react-router-dom";
import AppLayout from "../layout";
import ChatPage from "../pages/ChatPage";
import LoginPage from "../pages/LoginPage";
import MailPage from "../pages/MailPage";
import NotFoundPage from "../pages/NotFoundPage";
import PrivateRoute from "./PrivateRoute";
import PublicRoute from "./PublicRoute";
import { PATHS } from "../constants/paths";

const ROLES = {
  EMPLOYEE: "EMPLOYEE",
  STAFF: "STAFF",
};

type ExtendedRouteObject = RouteObject &
  Partial<{
    isPrivate: boolean;
    is404: boolean;
    role: string[];
    title: string;
  }>;

export const routes: ExtendedRouteObject[] = [
  {
    path: PATHS.LOGIN,
    element: <LoginPage />,
    isPrivate: false,
    title: "Customer Support | Login",
  },
  {
    element: <AppLayout />,
    isPrivate: true,
    role: [ROLES.STAFF],
    title: "Chăm sóc khách hàng | Chat",
    children: [{ path: PATHS.CHAT, element: <ChatPage /> }],
  },
  {
    element: <AppLayout />,
    isPrivate: true,
    role: [ROLES.STAFF],
    title: "Chăm sóc khách hàng | Mail",
    children: [{ path: PATHS.MAIL, element: <MailPage /> }],
  },
  {
    path: "*",
    element: <NotFoundPage />,
    is404: true,
  },
].map((route) => {
  if (route.isPrivate) {
    return {
      ...route,
      element: <PrivateRoute title={route.title}>{route.element}</PrivateRoute>,
    };
  }

  return {
    ...route,
    element: <PublicRoute title={route.title}>{route.element}</PublicRoute>,
  };
});
