import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../redux/selector";
import { ROUTE } from "../constant/route";
import { ROLE } from "../constant/role";
const PrivateRoute = (props) => {
    const account = useAuth();
    const navigate = useNavigate();
    const roles = props.roles;

    useEffect(() => {
        if (roles && !roles.includes(account.role)) {
            if (roles.includes(ROLE.ADMIN)) {
                navigate(ROUTE.ADMIN_LOGIN)
            } else if (roles.includes(ROLE.USER)) {
                navigate(ROUTE.LOGIN)
            }
        }
    }, [roles, account, navigate])

    return (
        <>
            {props.children}
        </>
    )
}

export default PrivateRoute;