import React, { useEffect, useState } from "react";
import { useAuth, useProfile } from "../../../../../redux/selector";
import { useDispatch } from "react-redux";
import ValidateUtils from "../../../../../utils/validate";
import { CUSTOM_REGEX } from "../../../../../constant/regex";
import toast from "react-hot-toast";
import { updateProfileApi } from "../../../../../api/global/profile.api";
import { successAlert } from "../../../../../config/sweetAlertConfig";
import { getProfileThunk } from "../../../../../redux/slices/profile.slice";
import { AxiosError } from "axios";
import PersonalInfo from "../../../../../components/user/profile/account_info/PersonalInfo";

const initFormData = {
  fullName: "",
  dateOfBirth: "",
  address: "",
  phone: "",
  gender: "",
};

const initErrors = new Map();

const PersonalInfoContainer = () => {
  const profile = useProfile();
  const [formData, setFormData] = useState(initFormData);
  const [errors, setErrors] = useState(initErrors);
  const [isUpdate, setIsUpdate] = useState(false);
  const auth = useAuth();
  const accountId = auth.id;
  const dispatch = useDispatch();

  const onStartUpdate = () => setIsUpdate(true);
  const onCancelUpdate = () => {
    handleResetForm();
    setIsUpdate(false);
  };

  const checkKeyInErrors = (key) => {
    return errors.has(key) ? "invalid" : "";
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

  const removeErrors = () => {
    errors.size > 0 && setErrors(initErrors);
  };

  const handleChangeForm = (event) => {
    removeErrors();
    if (event && event.target) {
      const { name, value } = event.target;
      setFormData((prevForm) => ({ ...prevForm, [name]: value }));
    }
  };

  const handleValidateForm = () => {
    const { isValid, errors } = ValidateUtils({
      formData: formData,
      rules: {
        fullName: { required: true },
        dateOfBirth: { required: true },
        address: { required: true },
        phone: { required: true, regex: CUSTOM_REGEX.PHONE },
        gender: { required: true },
      },
      messages: {
        fullName_required: "Họ tên không được để trống",
        dateOfBirth_required: "Ngày sinh không được để trống",
        address_required: "Địa chỉ không được để trống",
        phone_required: "Số điện thoại không được để trống",
        phone_regex: "Số điện thoại không đúng định dạng",
        gender_required: "Giới tính không được để trống",
      },
    });

    return { isValid, errors };
  };

  const handleSubmitForm = (event) => {
    event.preventDefault();
    const validate = handleValidateForm();
    if (!validate.isValid) {
      setErrors(validate.errors);
      return;
    }

    const loadingToast = toast.loading("Đang cập nhật...");
    updateProfileApi(accountId, formData)
      .then((res) => {
        setTimeout(() => {
          toast.dismiss(loadingToast);
          successAlert(
            "Thành công",
            "Đã cập nhật hồ sơ thành công!",
            "OK"
          ).then((result) => {
            onCancelUpdate();
            dispatch(getProfileThunk(accountId));
          });
        }, 1000);
      })
      .catch((error) => {
        setTimeout(() => {
          toast.dismiss(loadingToast);
          if (error instanceof AxiosError) {
            const message = "Cập nhật thất bại, vui lòng thử lại!";
            toast.error(message, { duration: 1000 });
          }
        }, 500);
      });
  };

  const handleResetForm = () => {
    removeErrors();
    for (const key in formData) {
      if (Object.hasOwnProperty.call(profile, key)) {
        setFormData((prevForm) => ({ ...prevForm, [key]: profile[key] ?? "" }));
      }
    }
  };

  const renderDateOfBirth = (date) => {
    if (!date) {
      return "Không xác định";
    }

    return new Date(date).toLocaleDateString("vi-VN", { dateStyle: "long" });
  };

  const renderGender = (gender) => {
    switch (gender) {
      case "MALE":
        return "Nam";
      case "FEMALE":
        return "Nữ";
      case "OTHER":
        return "Khác";
      default:
        return "Không xác định";
    }
  };

  useEffect(() => {
    for (const key in formData) {
      if (Object.hasOwnProperty.call(profile, key) && profile[key]) {
        setFormData((prevForm) => ({ ...prevForm, [key]: profile[key] }));
      }
    }
    // eslint-disable-next-line
  }, [profile]);
  return (
    <PersonalInfo
      profile={profile}
      isUpdate={isUpdate}
      formData={formData}
      onStartUpdate={onStartUpdate}
      onCancelUpdate={onCancelUpdate}
      checkKeyInErrors={checkKeyInErrors}
      handleChangeForm={handleChangeForm}
      handleResetForm={handleResetForm}
      handleSubmitForm={handleSubmitForm}
      renderDateOfBirth={renderDateOfBirth}
      renderGender={renderGender}
      renderErrorMessage={renderErrorMessage}
    />
  );
};

export default PersonalInfoContainer;
