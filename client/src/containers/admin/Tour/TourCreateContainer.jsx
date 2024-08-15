import React, { useState } from "react";
import TourCreate from "../../../components/admin/Tour/TourCreate";
import { createTourApi } from "../../../api/admin/tour.api";
import { offLoading, onLoading } from "../../../redux/slices/loading.slice";
import { useDispatch } from "react-redux";
import { useFormik } from "formik";
import { validateTour } from "../../../validation";
import { toast } from "react-toastify";
import { useAdmin } from "../../../redux/selector";
import { fetchAllToursThunk } from "../../../redux/slices/admin.slice";

const TourCreateContainer = ({ isShowCreateTour, handleHiddenCreateTour }) => {
  const formik = useFormik({
    initialValues: {
      title: "",
      image1: "",
      image2: "",
      image3: "",
      image4: "",
      destinations: "",
      duration: "",
      departureLocation: "",
      schedules: "",
      vehicle: "",
      totalSeats: 0,
      adultPrice: 0,
      childrenPrice: 0,
      babyPrice: 0,
    },
    onSubmit: (values) => {
      validateTour
        .validate(values, { abortEarly: true })
        .then((data) => {
          if (selectedFiles.length < 4) {
            toast.warning("Bạn chưa chọn ảnh, hãy chọn đủ 4 file ảnh!");
            return;
          }
          handleSubmit(data);
        })
        .catch((err) => {
          toast.warning("Hãy điền đầy đủ thông tin", {
            position: "top-right",
            theme: "colored",
          });
        });
    },
  });

  const [selectedFiles, setSelectedFiles] = useState([]);
  const dispatch = useDispatch();
  const { tourManager } = useAdmin();
  const page = tourManager.pageNumber;

  const setEmptySelectedFiles = () => {
    setSelectedFiles([]);
  };

  const handleChangeFiles = (e) => {
    const files = e.target.files;
    setSelectedFiles(Array.from(files));
  };

  const handleResetFormik = () => {
    formik.handleReset();
    setEmptySelectedFiles();
  };

  const handleSubmit = (form) => {
    const formData = new FormData();
    formData.append("title", form.title);
    formData.append("destinations", form.destinations);
    formData.append("duration", form.duration);
    formData.append("departureLocation", form.departureLocation);
    formData.append("schedules", form.schedules);
    formData.append("vehicle", form.vehicle);
    formData.append("totalSeats", form.totalSeats);
    formData.append("adultPrice", form.adultPrice);
    formData.append("childrenPrice", form.childrenPrice);
    formData.append("babyPrice", form.babyPrice);
    formData.append("image1", selectedFiles[0]);
    formData.append("image2", selectedFiles[1]);
    formData.append("image3", selectedFiles[2]);
    formData.append("image4", selectedFiles[3]);

    dispatch(onLoading());
    createTourApi(formData)
      .then((res) => {
        dispatch(offLoading());
        dispatch(
          fetchAllToursThunk({
            page,
            size: 5,
          })
        );
        handleHiddenCreateTour();
        toast.success("Tour mới đã được tạo thành công!");
      })
      .catch((err) => {
        toast.error("Tạo tour không thành công, hãy thử lại!");
        dispatch(offLoading());
      });
  };

  return (
    <TourCreate
      formik={formik}
      handleChangeFiles={handleChangeFiles}
      handleResetForm={handleResetFormik}
      selectedFiles={selectedFiles}
      setEmptySelectedFiles={setEmptySelectedFiles}
      isShowCreateTour={isShowCreateTour}
      handleHiddenForm={handleHiddenCreateTour}
    />
  );
};

export default TourCreateContainer;
