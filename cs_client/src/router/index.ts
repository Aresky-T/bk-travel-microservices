import { useRoutes } from "react-router-dom";
import { routes } from "./routes";
import { useAppContext } from "../store/context";

export default function AppRouter() {
    const { state } = useAppContext();
    const { auth } = state;
    const role = auth.data?.role;

    const filterRoutes = routes.filter(route => {
        if (route.is404) {
            return true;
        }

        if (route.isPrivate && role) {
            return route.role && route.role.includes(role);
        }

        return !route.isPrivate;
    })

    return useRoutes(filterRoutes)
}