import { useEffect } from "react";
import { handleScrollToTop } from "./PublicRoute";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../redux/selector";
import { ROUTE } from "../constant/route";
const PrivateRoute = (props) => {
    const account = useAuth();
    const location = useLocation();
    const navigate = useNavigate();
    const role = props.role;

    useEffect(() => {
        if (role && !role.includes(account.role)) {
            if (role.includes("ADMIN")) {
                navigate(ROUTE.ADMIN_LOGIN)
            } else if (role.includes("USER")) {
                navigate(ROUTE.LOGIN)
            }
        }
    }, [role, account, navigate])

    useEffect(() => {
        handleScrollToTop();
    }, [location.pathname])

    return (
        <>
            {props.children}
        </>
    )
}

export default PrivateRoute;