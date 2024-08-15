import React from 'react'
import LoginContainer from "../../containers/global/Auth/LoginContainer";
import HelmetTitle from "../../components/helmet/HelmetTitle";

const LoginPage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel - Đăng nhập"}
                metaName={"meta-login"}
            />
            <LoginContainer />
        </>
    )
}

export default LoginPage