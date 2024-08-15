import { useState } from "react";
import { ErrorsType, FormLogin } from "../../containers/login";
import { useAppContext } from "../../store/context";
import Box from "../styled/box";
import { SiStaffbase } from "react-icons/si"
import { BsEyeFill, BsEyeSlashFill } from "react-icons/bs"

type PropsType = {
    formLogin: FormLogin,
    handleSubmitLogin: (event: React.FormEvent<HTMLFormElement>) => void,
    handleChangeForm: (event: React.ChangeEvent<HTMLInputElement>) => void,
    renderError: (inputName: string, errors: ErrorsType) => string,
    errors: ErrorsType,
};

export default function Login({ formLogin, handleSubmitLogin, handleChangeForm, renderError, errors }: PropsType) {
    const { state } = useAppContext();
    const { isLoading } = state.auth;
    const [isShowPassword, setIsShowPassword] = useState(false);

    function renderEye() {
        if (isShowPassword) {
            return <BsEyeFill />
        }

        return <BsEyeSlashFill />
    }

    return (
        <section className="login-area">
            <Box
                className="login-area__logo"
                $height={50}
                $overflow="hidden"
                $userSelect="none"
            >
                <img src={require("../../imgs/logo/png/logo-no-background.png")} alt="" />
            </Box>
            <Box
                $display="flex"
                $alignItems="center"
                $boxShadow="0 0 10px 0 rgba(0, 0, 0, 0.4)"
                $background={"#fff"}
                $borderRadius={10}
                $padding={50}
                $width={"900px"}
            >
                <Box
                    $height={"fit-content"}
                    $width={"50%"}
                    $overflow="hidden"
                    $padding={20}
                    $userSelect="none"
                >
                    <img src="https://is.vnecdn.net/v113/86/69/46/4466986/assets/images/img-girl.png" alt=""
                        style={{
                            width: "100%",
                            height: "100%",
                            objectFit: "cover",
                        }}
                    />
                </Box>
                <Box
                    $borderLeft={"1px solid #ccc"}
                    $height={"fit-content"}
                    $width={"50%"}
                    $minWidth={300}
                    $maxWidth={400}
                    $display="flex"
                    $flexDirection="column"
                    $justifyContent="center"
                    $alignItems="flex-start"
                    $padding={"20px 0 20px 40px"}
                    $gap={50}
                >
                    <div className="login-area__header">
                        <strong><span>Đăng nhập</span> với vai trò nhân viên</strong>
                        <SiStaffbase color="var(--fourth-color)" />
                    </div>
                    <form className="login-form"
                        onSubmit={handleSubmitLogin}
                    >
                        <div className="login-form_item">
                            {/* <label className="login-form_item_label">Username:</label> */}
                            <input type="text"
                                name="username"
                                placeholder="Nhập tên tài khoản..."
                                value={formLogin.username}
                                onChange={handleChangeForm}
                            />
                            <div className="error-message">{renderError('username', errors)}</div>
                        </div>
                        <div className="login-form_item">
                            {/* <label className="login-form_item_label">Password:</label> */}
                            <input type={isShowPassword ? "text" : "password"}
                                name="password"
                                placeholder="Nhập mật khẩu..."
                                value={formLogin.password}
                                onChange={handleChangeForm}
                            />
                            <Box
                                $position="absolute"
                                $right={10}
                                $bottom={20}
                                $cursor="pointer"
                                className="show-password-btn"

                                onClick={() => {
                                    setIsShowPassword(!isShowPassword)
                                }}
                            >{renderEye()}
                            </Box>
                            <div className="error-message">{renderError('password', errors)}</div>
                        </div>
                        <div className="error-message">{state.auth.errorMessage}</div>
                        <div className="login-form_item">
                            <input type="submit" value={isLoading ? "Loading..." : "Đăng nhập"} />
                        </div>
                    </form>
                    <Box className="forgot-password"
                        $textAlign="center"
                        $fontSize={"0.7rem"}
                        $width={"100%"}
                    >
                        Quên mật khẩu?
                    </Box>
                </Box>
            </Box>
        </section >
    )
}