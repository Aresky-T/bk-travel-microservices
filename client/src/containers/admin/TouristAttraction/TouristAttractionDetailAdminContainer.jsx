import React, { useCallback, useEffect, useState } from "react";
import { getTouristAttractionDetailsApi } from "../../../api/global/tourist_attraction.api";
import { useFormik } from "formik";
import TouristAttractionDetailAdmin from "../../../components/admin/TouristAttraction/TouristAttractionDetailAdmin";
import { validateTouristAttraction } from "../../../validation";
import { useDispatch } from "react-redux";
import { useAdmin } from "../../../redux/selector";
import {
  deleteTouristAttractionApi,
  updateTouristAttractionApi,
} from "../../../api/admin/tourist_attraction.api";
import {
  errorAlert,
  questionAlert,
  successAlert,
  warningAlert,
} from "../../../config/sweetAlertConfig";
import { offLoading, onLoading } from "../../../redux/slices/loading.slice";
import { onUpdateAdminTouristAttraction } from "../../../redux/slices/admin.slice";

const TouristAttractionDetailAdminContainer = () => {
  const dispatch = useDispatch();
  const [message, setMessage] = useState("");
  const { touristAttractionManager } = useAdmin();
  const { touristAttraction, isReloadTouristAttraction } =
    touristAttractionManager;

  const formik = useFormik({
    initialValues: {
      id: 0,
      name: "",
      imageUrl: "",
      introduction: "",
      blogs: [],
    },
    onSubmit: (values) => {
      validateTouristAttraction
        .validate(values)
        .then((data) => {
          const { name, imageUrl, introduction } = data;
          dispatch(onLoading());
          updateTouristAttractionApi(values.id, {
            name,
            imageUrl,
            introduction,
          })
            .then((res) => {
              dispatch(offLoading());
              successAlert(
                "Thành công",
                "Cập nhật Địa điểm du lịch thành công!",
                "OK"
              );
              setMessage((message) => message + "success");
            })
            .catch((err) => {
              dispatch(offLoading());
              errorAlert(
                "Thất bại",
                "Cập nhật không thành công, hãy kiểm tra lại!",
                "OK"
              );
              setMessage((message) => message + "failed");
            });
        })
        .catch((error) => {
          warningAlert(
            "Thông tin chưa hợp lệ",
            "Vui lòng kiểm tra lại đầy đủ thông tin trước khi bấm xác nhận!",
            {
              cancelButtonText: "Kiểm tra",
            }
          );
          console.log(error);
        });
    },
  });

  const handleRestoreOriginal = () => {
    setMessage((message) => message + "restore");
  };

  const handleDeleteTouristAttraction = () => {
    const id = touristAttraction.id;
    if (!id) {
      return;
    }

    questionAlert(
      "Yêu cầu xác nhận",
      "Tất cả dữ liệu về Địa điểm du lịch này và các bài viết liên quan sẽ bị xóa đi, bạn có chắc chắn?",
      "Chắc chắn, Xóa!",
      "Hủy bỏ"
    )
      .then((result) => {
        if (result.isConfirmed) {
          deleteTouristAttractionApi(id)
            .then((res) => {
              successAlert("Thành công", "Đã xóa thành công", "Tiếp tục");
              dispatch(onUpdateAdminTouristAttraction(null));
            })
            .catch((err) => {
              errorAlert(
                "Thất bại",
                "Xóa không thành công, vui lòng kiểm tra lại!"
              );
            });
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const fetchTouristAttractionDetails = useCallback(() => {
    if (touristAttraction) {
      getTouristAttractionDetailsApi(touristAttraction.id)
        .then((res) => {
          const data = res.data;
          formik.setValues({
            id: data.id,
            name: data.name,
            introduction: data.introduction,
            imageUrl: data.imageUrl,
            blogs: [...data.blogs],
          });
        })
        .catch((err) => {});
    }
    //eslint-disable-next-line
  }, [touristAttraction, message]);

  useEffect(() => {
    fetchTouristAttractionDetails();
  }, [fetchTouristAttractionDetails]);

  useEffect(() => {
    if (isReloadTouristAttraction) {
      fetchTouristAttractionDetails();
    }
    //eslint-disable-next-line
  }, [isReloadTouristAttraction]);

  return (
    <TouristAttractionDetailAdmin
      formik={formik}
      handleDeleteTouristAttraction={handleDeleteTouristAttraction}
      handleRestoreOriginal={handleRestoreOriginal}
    />
  );
};

export default TouristAttractionDetailAdminContainer;
