import { useCallback, useEffect, useState } from "react";
import TouristAttractionManager from "../../../components/admin/TouristAttraction/TouristAttractionManager";
import TouristAttractionCreate from "../../../components/admin/TouristAttraction/TouristAttractionCreate";
import {
  createTouristAttractionApi,
  getAllTouristAttractionsApi,
} from "../../../api/admin/tourist_attraction.api";
import ValidateUtils from "../../../utils/validate";
import { toast } from "react-toastify";
import { successAlert } from "../../../config/sweetAlertConfig";
import { useAdmin } from "../../../redux/selector";
import TouristAttractionDetailAdminContainer from "./TouristAttractionDetailAdminContainer";

const initTouristAttractionForm = {
  name: "",
  imageUrl: "",
  introduction: "",
};

const TouristAttractionManagerContainer = () => {
  const [data, setData] = useState();
  const [fields, setFields] = useState({
    size: 6,
    page: 0,
  });
  const [formData, setFormData] = useState(initTouristAttractionForm);
  const [isCreate, setIsCreate] = useState(false);
  const [isRefetch, setIsRefetch] = useState(false);
  const { touristAttractionManager } = useAdmin();
  const { touristAttraction } = touristAttractionManager;

  const handleChangeCurrentPage = (page) => {
    setFields({
      ...fields,
      page: page,
    });
  };

  const handleChangeFormData = (event) => {
    if (event && event.target) {
      const { name, value } = event.target;
      setFormData((prevForm) => ({
        ...prevForm,
        [name]: value,
      }));
    }
  };

  const handleResetForm = () => setFormData(initTouristAttractionForm);

  const handleShowCreate = () => setIsCreate(true);

  const handleHiddenCreate = () => setIsCreate(false);

  const handleCancelCreate = () => {
    setFormData(initTouristAttractionForm);
    handleHiddenCreate();
  };

  const validateForm = (form) => {
    return ValidateUtils({
      formData: form,
      rules: {
        name: {
          required: true,
        },
        imageUrl: {
          required: true,
        },
        introduction: {
          required: true,
        },
      },
    });
  };

  const handleSubmitCreate = (event) => {
    event.preventDefault();
    const { isValid } = validateForm(formData);
    if (!isValid) {
      toast.warning("Dữ liệu không hợp lệ!");
      return;
    }

    handleHiddenCreate();
    const loading = toast.loading("Đang tạo", { position: "top-center" });
    createTouristAttractionApi(formData)
      .then((res) => {
        setTimeout(() => {
          toast.dismiss(loading);
          setIsRefetch(true);
          handleResetForm();
          successAlert(
            "Thành công!",
            "Địa điểm mới đã được tạo thành công!",
            "ok"
          );
        }, 1000);
      })
      .catch((err) => {
        toast.dismiss(loading);
        toast.error("Tạo mới thất bại!");
        handleResetForm();
        console.log(err);
      });
  };

  const fetchAllTouristAttraction = useCallback(() => {
    getAllTouristAttractionsApi(fields)
      .then((res) => {
        setData(res.data);
        if (isRefetch) {
          setIsRefetch(false);
        }
      })
      .catch((err) => {});
  }, [fields, isRefetch]);

  useEffect(() => {
    fetchAllTouristAttraction();
  }, [fetchAllTouristAttraction]);

  return (
    <div className="administrator-manager-container ">
      {touristAttraction ? (
        <TouristAttractionDetailAdminContainer />
      ) : (
        <TouristAttractionManager
          data={data}
          fields={fields}
          handleChangeCurrentPage={handleChangeCurrentPage}
          handleShowCreate={handleShowCreate}
        />
      )}
      <TouristAttractionCreate
        isOpen={isCreate}
        formData={formData}
        handleSubmit={handleSubmitCreate}
        handleChange={handleChangeFormData}
        handleHidden={handleHiddenCreate}
        handleCancel={handleCancelCreate}
      />
    </div>
  );
};

export default TouristAttractionManagerContainer;
