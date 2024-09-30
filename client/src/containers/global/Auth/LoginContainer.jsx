import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useAuth } from "../../../redux/selector";
import { useNavigate, useLocation } from "react-router-dom";
import { ROUTE } from "../../../constant/route";
import Login from "../../../components/global/Auth/Login";
import { useFormik } from "formik";
import { loginUserApi } from "../../../api/global/auth.api";
import { saveAccountInfo } from "../../../redux/slices/auth.slice";
import { ROLE } from "../../../constant/role";
import {
  errorAlert,
  warningAlert,
  warningAlertNoCancel,
} from "../../../config/sweetAlertConfig";
import { validateLoginForm } from "../../../validation";
import { toast } from "react-hot-toast";

const LoginContainer = () => {
  const [activeError, setActiveError] = useState(null);

  const auth = useAuth();
  const { accessToken, role } = auth;
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const location = useLocation();

  const loginFormik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema: validateLoginForm,
    onSubmit: (values) => {
      handleLoginUser({
        username: values.username,
        password: values.password,
      });
    },
  });

  const handleLoginUser = (account) => {
    const loading = toast.loading("Đang xác thực...");
    loginUserApi(account)
      .then((res) => {
        const account = res.data;
        const { id, token, email, username, role, type, status } = res.data;

        if (!account.role || account.role !== ROLE.USER) {
          setTimeout(() => {
            toast.dismiss(loading);
            warningAlert(
              "Cảnh báo",
              "Tài khoản của bạn bị giới hạn quyền đăng nhập do vai trò không hợp lệ!",
              {
                cancelButtonText: "Đăng nhập lại",
                confirmButtonText: "Trang Chủ",
              }
            ).then((result) => {
              result.isConfirmed && navigate(ROUTE.HOME);
            });
          }, 1000);
          return;
        }

        setTimeout(() => {
          toast.dismiss(loading);
          if (status === "BLOCKED") {
            warningAlert("Cảnh báo", "Tài khoản của bạn đã bị khoá!");
          } else {
            toast.success("Xác thực thành công!", { duration: 1000 });
            dispatch(
              saveAccountInfo({
                id,
                token,
                email,
                username,
                role,
                type,
                status,
              })
            );
          }
        }, 1000);
      })
      .catch((err) => {
        const message = err?.response?.data?.message;
        setTimeout(() => {
          toast.dismiss(loading);
          if (message) {
            errorAlert("Đăng nhập thất bại", message, {
              cancelButtonText: "Đăng nhập lại",
              confirmButtonText: "Trang Chủ",
            }).then((result) => {
              result.isConfirmed && navigate(ROUTE.HOME);
            });
          }
        }, 1000);
      });
  };

  const handleIconMouseEnter = (index) => {
    setActiveError(index);
  };

  const handleIconMouseLeave = () => {
    setActiveError(null);
  };

  useEffect(() => {
    if (role === ROLE.USER && accessToken) {
      if (location.state) {
        navigate(location.state.prevPath);
      } else {
        navigate(ROUTE.HOME);
      }
    }

    if (role === ROLE.ADMIN && accessToken) {
      warningAlertNoCancel(
        "Cảnh báo đăng nhập",
        "Bạn đang đăng nhập với vai trò quản trị viên, hãy đăng xuất ở trang quản trị!",
        "Tới Trang Quản trị"
      )
        .then((result) => {
          if (result.isConfirmed) {
            navigate(ROUTE.ADMIN_HOME);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, [navigate, accessToken, role, location]);

  return (
    <Login
      activeError={activeError}
      handleIconMouseEnter={handleIconMouseEnter}
      handleIconMouseLeave={handleIconMouseLeave}
      loginFormik={loginFormik}
    />
  );
};

export default LoginContainer;
