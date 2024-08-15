import React, { useCallback, useEffect, useState } from "react";
import TourDetailsAdmin from "../../../components/admin/Tour/TourDetailsAdmin";
import { getTourByIdApi } from "../../../api/global/tours.api";
import { useFormik } from "formik";
import { validateTour } from "../../../validation";
import {
  deleteTourByIdApi,
  updateTourWithPatchMethodApi,
} from "../../../api/admin/tour.api";
import { useDispatch } from "react-redux";
import { useAdmin } from "../../../redux/selector";
import {
  questionAlert,
  warningAlertNoCancel,
} from "../../../config/sweetAlertConfig";
import { onUpdateAdminSelectedTourId } from "../../../redux/slices/admin.slice";
import { offLoading, onLoading } from "../../../redux/slices/loading.slice";
import { toast } from "react-toastify";

const initTourDetails = {
  id: null,
  title: "",
  departureLocation: "",
  duration: "",
  destinations: "",
  totalSeats: 0,
  vehicle: "",
  schedules: "",
  image1: "",
  image2: "",
  image3: "",
  image4: "",
  adultPrice: 0,
  childrenPrice: 0,
  babyPrice: 0,
};

const TourDetailsAdminContainer = () => {
  const [isShowCreateSubTour, setIsShowCreateSubTour] = useState(false);
  const [isRefetchTour, setIsRefetchTour] = useState(false);
  const [tourDetails, setTourDetails] = useState(initTourDetails);

  const { tourManager } = useAdmin();
  const { selectedTourId } = tourManager;

  const dispatch = useDispatch();

  const tourFormik = useFormik({
    initialValues: initTourDetails,
    onSubmit: (values) => {
      validateTour
        .isValid(values)
        .then((isValid) => {
          if (isValid) {
            handleUpdateTour(values);
          } else {
            warningAlertNoCancel(
              "Cảnh báo",
              "Tồn tại dữ liệu không hợp lệ hoặc bị thiếu, hãy nhập đầy đủ!",
              "OK"
            );
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  });

  const handleUpdateTour = async (formData) => {
    try {
      const fields = {};
      for (const key in formData) {
        if (formData[key] !== tourDetails[key]) {
          fields[key] = formData[key];
        }
      }

      if (JSON.stringify(fields) === "{}") {
        toast("Không có giá trị nào bị thay đổi!", {
          type: "warning",
          autoClose: 2000,
          closeButton: false,
        });
        return;
      }

      const selectedBtn = await questionAlert(
        "Cảnh báo",
        "Bạn có chắc chắn muốn cập nhật Tour không?",
        "Cập nhật",
        "Huỷ bỏ"
      );

      if (!selectedBtn.isConfirmed) {
        return;
      }

      dispatch(onLoading());
      const response = await updateTourWithPatchMethodApi(
        selectedTourId,
        formData
      );

      if (response) {
        dispatch(offLoading());
        handleRefetchTour();
        toast.success("Cập nhật tour thành công");
      }
    } catch (error) {
      dispatch(offLoading());
      toast.error("Cập nhật tour thất bại, hãy kiểm tra lại");
    }
  };

  const handleDeleteTour = () => {
    if (!selectedTourId) {
      return;
    }

    questionAlert(
      "Yêu cầu xác nhận",
      "Tất cả dữ liệu về Tour du lịch này sẽ bị xóa đi, bạn có chắc chắn muốn xóa không?",
      "Tôi chắc chắn, Xóa!",
      "Hủy bỏ"
    )
      .then((result) => {
        if (!result.isConfirmed) {
          return;
        }

        deleteTourByIdApi(selectedTourId)
          .then((res) => {
            toast("Đã xóa tour thành công", {
              type: "warning",
              autoClose: 1500,
            });
            dispatch(onUpdateAdminSelectedTourId(null));
          })
          .catch((err) => {
            toast.error("Xóa Tour không thành công, vui lòng kiểm tra lại!", {
              position: "top-right",
            });
          });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleRefetchTour = () => setIsRefetchTour(true);

  const fetchTourDetails = useCallback(() => {
    if (!selectedTourId) return;

    getTourByIdApi(selectedTourId)
      .then((res) => {
        const tour = res.data;
        setTourDetails(tour);
        if (isRefetchTour) {
          setIsRefetchTour(false);
        }
      })
      .catch((err) => {});
    //eslint-disable-next-line
  }, [selectedTourId]);

  useEffect(() => {
    fetchTourDetails();
  }, [fetchTourDetails]);

  useEffect(() => {
    if (isRefetchTour) {
      fetchTourDetails();
    }
    //eslint-disable-next-line
  }, [isRefetchTour]);

  useEffect(() => {
    if (tourDetails) {
      const obj = { ...tourFormik.values };
      for (const key in tourDetails) {
        if (Object.hasOwnProperty.call(tourFormik.values, key)) {
          obj[key] = tourDetails[key];
        }
      }
      tourFormik.setValues(obj);
    }
    //eslint-disable-next-line
  }, [tourDetails]);

  return (
    <TourDetailsAdmin
      tourFormik={tourFormik}
      handleDeleteTour={handleDeleteTour}
      isShowCreateSubTour={isShowCreateSubTour}
      setIsShowCreateSubTour={setIsShowCreateSubTour}
    />
  );
};

export default TourDetailsAdminContainer;
