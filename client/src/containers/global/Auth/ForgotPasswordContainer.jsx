import ForgotPassword from "../../../components/global/Auth/ForgotPassword";
import { useState } from "react";
import * as yup from "yup";
import { CUSTOM_REGEX } from "../../../constant/regex";
import { ValidationError } from "yup";
import { forgotPasswordApi } from "../../../api/global/auth.api";
import { useDispatch } from "react-redux";
import { offLoading } from "../../../redux/slices/loading.slice";
import { customToast } from "../../../toaster";
import {
  successAlert,
  warningAlertNoCancel,
} from "../../../config/sweetAlertConfig";

const validateEmail = yup
  .string()
  .required("Yêu cầu nhập email!")
  .matches(CUSTOM_REGEX.EMAIL, "Email không đúng định dạng, hãy thử lại!");

const ForgotPasswordContainer = () => {
  const [email, setEmail] = useState("");
  const dispatch = useDispatch();
  const handleChangeEmail = (e) => {
    if (e.target) {
      setEmail(e.target.value);
    }
  };

  const handleForgotPassword = () => {
    validateEmail
      .validate(email)
      .then((data) => {
        forgotPasswordApi(data, dispatch)
          .then((res) => {
            dispatch(offLoading());
            successAlert(
              "Thành công",
              "Thành công, hãy kiểm tra mail của bạn để lấy lại mật khẩu!",
              "OK"
            );
            console.log(res);
          })
          .catch((err) => {
            dispatch(offLoading());
            warningAlertNoCancel(
              "Email không hợp lệ",
              "Có vẻ như email này chưa từng được đăng ký tài khoản!",
              "Thử lại"
            );
          });
      })
      .catch((err) => {
        if (err instanceof ValidationError) {
          customToast(err.message, "⚠️");
        }
      });
  };

  return (
    <ForgotPassword
      email={email}
      handleForgotPassword={handleForgotPassword}
      handleChangeEmail={handleChangeEmail}
    />
  );
};

export default ForgotPasswordContainer;
