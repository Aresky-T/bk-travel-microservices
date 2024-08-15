import ForgotPasswordContainer from "../../containers/global/Auth/ForgotPasswordContainer";
import HelmetTitle from "../../components/helmet/HelmetTitle";

const ForgotPasswordPage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel - Lấy lại mật khẩu"}
                metaName={"meta-forgot-password"}
            />
            <ForgotPasswordContainer />
        </>
    )
}

export default ForgotPasswordPage