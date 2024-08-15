import React, { useEffect, useState } from "react";
import SubTourFormData from "../../../../components/admin/Tour/subtour/SubTourFormData";
import {
  createSubTourApi,
  updateSubTourApi,
} from "../../../../api/admin/tour.api";
import { warningAlert } from "../../../../config/sweetAlertConfig";
// import { compareDates } from "../../../../utils/compare";
import { useAdmin } from "../../../../redux/selector";
import { AxiosError } from "axios";
import ValidateUtils from "../../../../utils/validate";
import { format } from "date-fns";
import { toast } from "react-toastify";

const initFormData = {
  id: null,
  tourCode: null,
  title: "",
  departureTime: new Date(),
  availableSeats: 0,
  status: null,
  tourGuideId: null,
  createdTime: null,
};

const SubTourFormContainer = ({
  subTour,
  isShowForm,
  handleHiddenForm,
  handleRefetchSubTours,
}) => {
  const [formData, setFormData] = useState({ ...initFormData });
  const [errors, setErrors] = useState(new Map());

  const { tourManager } = useAdmin();
  const tourId = tourManager.selectedTourId;

  const handleChange = (event) => {
    if (event.target) {
      const { name, value } = event.target;
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  const handleClearErrors = () => setErrors(new Map());

  const handleResetFormData = () => {
    setFormData({ ...initFormData });
  };

  const handleChangeMultiFields = (subTour) => {
    const newForm = {};

    for (const key in subTour) {
      if (Object.hasOwnProperty.call(formData, key)) {
        newForm[key] = subTour[key];
      }
    }

    setFormData(newForm);
  };

  const handleCancel = () => {
    handleClearErrors();
    handleResetFormData();
    handleHiddenForm();
  };

  const handleValidateForm = (form) => {
    const { isValid, errors } = ValidateUtils({
      formData: form,
      rules: {
        title: {
          required: true,
        },
        departureTime: {
          required: true,
        },
      },
      messages: {
        title_required: "Tiêu đề không được để trống",
        departureTime_required: "Thời gian khởi hành không hợp lệ",
      },
    });

    if (!isValid) {
      setErrors(errors);
    } else {
      setErrors(new Map());
    }

    return isValid;
  };

  const handleSubmitCreate = async () => {
    const form = {
      title: formData.title,
      departureTime: formData.departureTime,
    };

    const isValid = handleValidateForm(form);

    if (!isValid) {
      toast.warning("Dữ liệu không hợp lệ, vui lòng thử lại", {
        position: "top-right",
      });
      return;
    }

    warningAlert("Cảnh báo", "Bạn có chắc chắn muốn tạo tour phụ mới không?", {
      confirmButtonText: "Xác nhận",
      cancelButtonText: "Huỷ bỏ",
    })
      .then((selectedBtn) => {
        if (selectedBtn.isConfirmed) {
          createSubTourApi(tourId, form)
            .then((res) => {
              handleHiddenForm();
              handleRefetchSubTours(tourId);
              toast.success("Tạo Tour phụ thành công", {
                position: "top-right",
              });
            })
            .catch((err) => {
              toast.error("Tạo Tour phụ thất bại, vui lòng thử lại", {
                position: "top-right",
              });
            });
        }
      })
      .catch((err) => {});
  };

  const handleSubmitUpdate = async () => {
    const fields = { ...formData };
    for (let key in fields) {
      if (formData[key] === subTour[key]) {
        delete fields[key];
      }

      if (key === "departureTime" && fields.departureTime) {
        fields.departureTime = format(
          fields.departureTime,
          "yyyy-MM-dd HH:mm:ss"
        );
      }
    }

    if (JSON.stringify(fields) === "{}") {
      toast.warning("Không có giá trị nào bị thay đổi", {
        position: "top-right",
        autoClose: 1000,
      });
      return;
    }

    try {
      const selectedBtn = await warningAlert(
        "Cảnh báo",
        "Bạn có chắc chắn muốn cập nhật không?",
        {
          confirmButtonText: "Xác nhận",
          cancelButtonText: "Huỷ",
        }
      );

      if (!selectedBtn.isConfirmed) {
        return;
      }

      const res = await updateSubTourApi(subTour.id, fields);
      if (res.data) {
        toast.success("Cập nhật thành công!", { position: "top-right" });
        handleRefetchSubTours();
      }
    } catch (error) {
      let message;
      if (error instanceof AxiosError) {
        message = error.response.data?.message;
      }
      toast.error(message || "Cập nhật thất bại!", { position: "top-right" });
    }
  };

  useEffect(() => {
    if (subTour) {
      handleChangeMultiFields(subTour);
    }

    //eslint-disable-next-line
  }, [subTour]);

  return (
    <div className={`sub-tour-form-wrapper${isShowForm ? " active" : ""}`}>
      <SubTourFormData
        selectedSubTour={subTour}
        isShowForm={isShowForm}
        formData={formData}
        errorsMap={errors}
        handleCancel={handleCancel}
        handleChange={handleChange}
        handleSubmitCreate={handleSubmitCreate}
        handleSubmitUpdate={handleSubmitUpdate}
      />
    </div>
  );
};

export default SubTourFormContainer;
