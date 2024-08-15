import React, { useEffect, useState } from "react";
import Register from "../../../components/global/Auth/Register";
import { useFormik } from "formik";
import { registerUserApi } from "../../../api/global/auth.api";
import { customToast } from "../../../toaster";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { offLoading } from "../../../redux/slices/loading.slice";
import { useAuth } from "../../../redux/selector";
import { ROUTE } from "../../../constant/route";
import { ROLE } from "../../../constant/role";
import { validateRegisterForm } from "../../../validation";

const RegisterContainer = () => {
  const [activeError, setActiveError] = useState(null);
  const accountInfo = useAuth();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const registerFormik = useFormik({
    initialValues: {
      email: "",
      username: "",
      password: "",
      confirmPassword: "",
    },
    validationSchema: validateRegisterForm,
    onSubmit: (values) => {
      const { email, username, password } = values;
      registerUserApi({ email, username, password }, dispatch)
        .then((res) => {
          dispatch(offLoading());
          showSwalWithLink();
        })
        .catch((err) => {
          dispatch(offLoading());
          const message = err.response.data?.message;
          customToast(message, "❌");
        });
    },
  });

  const showSwalWithLink = () => {
    Swal.fire({
      icon: "success",
      title: "Đăng ký tài khoản thành công!",
      confirmButtonText: "Đăng nhập",
      showCancelButton: true,
      cancelButtonText: "Thoát",
    }).then((result) => {
      if (result.isConfirmed) {
        navigate("/login");
      }
      if (result.isDismissed) {
        navigate("/");
      }
    });
  };

  const handleIconMouseEnter = (index) => {
    setActiveError(index);
  };

  const handleIconMouseLeave = () => {
    setActiveError(null);
  };

  useEffect(() => {
    if (accountInfo.role === ROLE.USER && accountInfo.accessToken) {
      navigate(ROUTE.HOME);
    }
  }, [navigate, accountInfo]);

  return (
    <Register
      activeError={activeError}
      handleIconMouseEnter={handleIconMouseEnter}
      handleIconMouseLeave={handleIconMouseLeave}
      registerFormik={registerFormik}
    />
  );
};

export default RegisterContainer;
