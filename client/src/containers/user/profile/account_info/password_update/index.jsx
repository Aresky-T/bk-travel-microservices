import React, { useState } from "react";
import PasswordUpdate from "../../../../../components/user/profile/account_info/PasswordUpdate";
import toast from "react-hot-toast";
import { AxiosError } from "axios";
import { logout } from "../../../../../redux/slices/auth.slice";
import { successAlert } from "../../../../../config/sweetAlertConfig";
import { updatePasswordApi } from "../../../../../api/global/account.api";
import ValidateUtils from "../../../../../utils/validate";
import { CUSTOM_REGEX } from "../../../../../constant/regex";
import { useAuth } from "../../../../../redux/selector";
import { useDispatch } from "react-redux";

const initErrors = new Map();
const initFormData = {
  currentPassword: "",
  newPassword: "",
  confirmPassword: "",
};

const PasswordUpdateContainer = () => {
  const [formData, setFormData] = useState(initFormData);
  const [errors, setErrors] = useState(initErrors);
  const [isUpdate, setIsUpdate] = useState(false);

  const auth = useAuth();
  const username = auth.username;
  const dispatch = useDispatch();

  const handleStartUpdate = () => setIsUpdate(true);
  const handleCancelUpdate = () => {
    handleResetForm();
    setIsUpdate(false);
  };

  const handleChangeForm = (event) => {
    removeErrors();
    if (event && event.target) {
      const { name, value } = event.target;
      setFormData((prevForm) => ({ ...prevForm, [name]: value }));
    }
  };

  const handleResetForm = () => {
    setFormData(initFormData);
    removeErrors();
  };

  const handleValidateFormData = () => {
    const regex_password = CUSTOM_REGEX.PASSWORD;
    return ValidateUtils({
      formData: formData,
      rules: {
        currentPassword: {
          required: true,
        },
        newPassword: {
          required: true,
          regex: regex_password,
        },
        confirmPassword: {
          required: true,
          equal_to: "newPassword",
        },
      },
      messages: {
        currentPassword_required: "Mật khẩu hiện tại không được trống",
        newPassword_required: "Mật khẩu mới không được trống",
        newPassword_regex: "Mật khẩu mới không đúng định dạng",
        confirmPassword_required: "Yêu cầu nhập lại mật khẩu mới",
        confirmPassword_equal_to:
          "Mật khẩu xác nhận không khớp với mật khẩu mới",
      },
    });
  };

  const renderErrorMessage = (fieldName) => {
    if (errors.has(fieldName)) {
      return (
        <div className="profile__error-message">
          {errors.get(fieldName)?.message}
        </div>
      );
    }
    return "";
  };

  const checkKeyInErrors = (key) => {
    return errors.has(key) ? "invalid" : "";
  };

  const removeErrors = () => {
    errors.size > 0 && setErrors(new Map());
  };

  const handleSubmitUpdatePassword = (event) => {
    event.preventDefault();
    const validateResult = handleValidateFormData();
    if (!validateResult.isValid) {
      setErrors(validateResult.errors);
      return;
    }

    const loadingToast = toast.loading("Đang cập nhật...");
    updatePasswordApi(formData, username)
      .then((res) => {
        setTimeout(() => {
          toast.dismiss(loadingToast);
          successAlert(
            "Thành công",
            "Đã cập nhật mật khẩu thành công, vui lòng đăng nhập lại!",
            "Đăng xuất"
          ).then((result) => {
            handleCancelUpdate();
            if (result.isConfirmed) {
              dispatch(logout());
            }
          });
        }, 500);
      })
      .catch((error) => {
        setTimeout(() => {
          toast.dismiss(loadingToast);
          if (error instanceof AxiosError) {
            const message = error.response.data.message
              ? "Bạn đã nhập sai mật khẩu!"
              : "Cập nhật mật khẩu thất bại!";
            toast.error(message, { duration: 1000 });
          }
        }, 500);
      });
  };

  return (
    <PasswordUpdate
      isUpdate={isUpdate}
      formData={formData}
      handleStartUpdate={handleStartUpdate}
      handleCancelUpdate={handleCancelUpdate}
      handleChangeForm={handleChangeForm}
      handleSubmitUpdatePassword={handleSubmitUpdatePassword}
      checkKeyInErrors={checkKeyInErrors}
      renderErrorMessage={renderErrorMessage}
    />
  );
};

export default PasswordUpdateContainer;
