import React, { useState } from "react";
import StaffCreateForm from "../../../components/admin/Staff/StaffCreateForm";
import { createStaffApi } from "../../../api/admin/staff.api";
import { toast } from "react-toastify";
import AdminModalWrapper from "../../../components/admin/Modal";
import { AxiosError } from "axios";
import ValidateUtils, { RULES } from "../../../utils/validate2";
import { format } from "date-fns";

const initStaffState = {
  firstName: "",
  lastName: "",
  email: "",
  phone: "",
  address: "",
  description: "",
  avatarUrl: "",
  gender: "",
  dateOfBirth: format(new Date(), "yyyy-MM-dd"),
  hireDate: format(new Date(), "yyyy-MM-dd"),
  contractUrl: "",
};

const StaffCreateContainer = ({
  isShowModal,
  handleRefetchStaffs,
  handleShowModal,
  handleCloseModal,
}) => {
  const [staffForm, setStaffForm] = useState(initStaffState);
  const [formErrors, setFormErrors] = useState(new Map());

  const handleChangeForm = (event) => {
    if (event && event.target) {
      const { name, value } = event.target;
      setStaffForm((prevForm) => ({
        ...prevForm,
        [name]: value,
      }));
    }
  };

  const handleValidateForm = (formData) => {
    const validator = ValidateUtils(formData);
    validator.addFieldRule("firstName", RULES.REQUIRED);
    validator.addFieldRule("firstName", RULES.NOT_EMPTY);

    validator.addFieldRule("lastName", RULES.REQUIRED);
    validator.addFieldRule("lastName", RULES.NOT_EMPTY);

    validator.addFieldRule("email", RULES.REQUIRED);
    validator.addFieldRule("email", RULES.EMAIL);

    validator.addFieldRule("phone", RULES.REQUIRED);
    validator.addFieldRule("phone", RULES.NOT_EMPTY);

    validator.addFieldRule("address", RULES.REQUIRED);
    validator.addFieldRule("address", RULES.NOT_EMPTY);

    validator.addFieldRule("description", RULES.REQUIRED);
    validator.addFieldRule("description", RULES.NOT_EMPTY);

    validator.addFieldRule("avatarUrl", RULES.REQUIRED);
    validator.addFieldRule("avatarUrl", RULES.NOT_EMPTY);

    validator.addFieldRule("gender", RULES.REQUIRED);

    validator.addFieldRule("dateOfBirth", RULES.REQUIRED);
    validator.addFieldRule("dateOfBirth", RULES.NOT_EMPTY);

    validator.addFieldRule("hireDate", RULES.REQUIRED);
    validator.addFieldRule("hireDate", RULES.NOT_EMPTY);

    validator.addFieldRule("contractUrl", RULES.REQUIRED);
    validator.addFieldRule("contractUrl", RULES.NOT_EMPTY);

    const { isValid, errors } = validator.validate();

    setFormErrors(errors);

    return isValid;
  };

  const handleResetForm = () => {
    setStaffForm(initStaffState);
    setFormErrors(new Map());
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const isValid = handleValidateForm(staffForm);

    if (!isValid) {
      toast.error("Thông tin chưa hợp lệ", { position: "top-center" });
      return;
    }

    handleCreateStaff(staffForm);
  };

  const handleCreateStaff = (formData) => {
    const loading = toast.loading("Đang xử lý");
    createStaffApi(formData)
      .then((res) => {
        handleRefetchStaffs();

        setTimeout(() => {
          toast.dismiss(loading);
          toast.success("Tạo nhân viên thành công");

          handleResetForm();
          handleCloseModal();
        }, 1500);
      })
      .catch((err) => {
        let message;

        if (err instanceof AxiosError) {
          message = err.response?.data?.message;
        }

        setTimeout(() => {
          toast.error(message ?? "Tạo nhân viên thất bại");
          toast.dismiss(loading);
        }, 1500);
      });
  };

  return (
    <AdminModalWrapper
      isOpen={isShowModal}
      title={"Tạo mới nhân viên"}
      handleOpen={handleShowModal}
      handleClose={handleCloseModal}
    >
      <StaffCreateForm
        formData={staffForm}
        formErrors={formErrors}
        handleChange={handleChangeForm}
        handleSubmit={handleSubmit}
        handleReset={handleResetForm}
      />
    </AdminModalWrapper>
  );
};

export default StaffCreateContainer;
