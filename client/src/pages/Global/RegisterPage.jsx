import React from 'react'
import RegisterContainer from '../../containers/global/Auth/RegisterContainer'
import HelmetTitle from "../../components/helmet/HelmetTitle";

const RegisterPage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel - Đăng ký"}
                metaName={"meta-register"}
            />
            <RegisterContainer />
        </>
    )
}

export default RegisterPage