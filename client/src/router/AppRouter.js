import { useRoutes } from "react-router-dom";
import { routes } from "./routes";

const AppRouter = () => {
    const filterRoutes = routes.filter((route) => {
        if (route.is404) {
            return true;
        }

        if (route.isPrivate) {
            // return account.role && route.role.includes(account.role);
            return true;
        }

        return !route.isPrivate
    })
    return useRoutes(filterRoutes);
}

export default AppRouter