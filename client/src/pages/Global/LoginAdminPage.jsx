import React from 'react';
import LoginAdminContainer from "../../containers/admin/Auth/LoginAdminContainer";
import HelmetTitle from "../../components/helmet/HelmetTitle";

const LoginAdminPage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel Admin - Đăng nhập"}
                metaName={"meta-login-admin"}
            />
            <LoginAdminContainer />
        </>
    )
}
export default LoginAdminPage