import React, { useEffect, useState } from "react";
import TourGuideDetails from "../../../components/admin/TourGuide/TourGuideDetails";
import { useNavigate, useParams } from "react-router-dom";
import {
  deleteTourGuideApi,
  getTourGuideDetailApi,
  updateTourGuideApi,
} from "../../../api/admin/tour_guide.api";
import { useDispatch } from "react-redux";
import { useAuth } from "../../../redux/selector";
import { validateTourGuide } from "../../../validation";
import { useFormik } from "formik";
import { offLoading, onLoading } from "../../../redux/slices/loading.slice";
import {
  errorAlert,
  questionAlert,
  successAlert,
  warningAlertNoCancel,
} from "../../../config/sweetAlertConfig";
import { ROUTE } from "../../../constant/route";

const TourGuideDetailsContainer = () => {
  const param = useParams();
  const account = useAuth();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [data, setData] = useState({});
  const [fieldsChange, setFieldsChange] = useState({});
  const [message, setMessage] = useState("");

  const formik = useFormik({
    initialValues: {
      fullName: "",
      birthDate: new Date(),
      gender: "",
      phone: "",
      address: "",
      avatarUrl: "",
      description: "",
      status: "",
      tourId: null,
    },
    onSubmit: (values) => {
      if (Object.getOwnPropertyNames(fieldsChange).length === 0) {
        return;
      }
      validateTourGuide
        .isValid(values)
        .then((isValid) => {
          dispatch(onLoading());

          if (account.accessToken && isValid) {
            updateTourGuideApi(param.id, fieldsChange, account.accessToken)
              .then((res) => {
                dispatch(offLoading());
                successAlert("Thành công", "Cập nhật thành công!", "OK");
                setMessage((message) => message + "a");
              })
              .catch((err) => {
                console.log(err);
                dispatch(offLoading());
                errorAlert(
                  "Thất bại",
                  "Cập nhật không thành công, hãy kiểm tra lại!",
                  "OK"
                );
              });
          } else {
            warningAlertNoCancel(
              "Cảnh báo",
              "Dữ liệu đã nhập chưa đầy đủ hoặc không hợp lệ, hãy kiểm tra lại!",
              "OK"
            );
          }
        })
        .catch((err) => {
          console.log(err);
          // warningAlertNoCancel("Cảnh báo", "Dữ liệu đã nhập chưa đầy đủ hoặc không hợp lệ, hãy kiểm tra lại!", "OK");
        });
    },
  });

  const handleDeleteTourGuide = () => {
    const id = param.id;
    questionAlert(
      "Yêu cầu xác nhận",
      "Tất cả dữ liệu về Nhân viên này sẽ bị xóa đi, bạn có chắc chắn muốn xóa không?",
      "Tôi chắc chắn, Xóa!",
      "Hủy bỏ"
    )
      .then((result) => {
        if (result.isConfirmed) {
          deleteTourGuideApi(id, account.accessToken)
            .then((res) => {
              successAlert(
                "Thành công",
                "Đã xóa nhân viên thành công",
                "OK"
              ).then((result) => {
                if (result.isConfirmed) {
                  navigate(ROUTE.STAFF_MANAGER);
                }
              });
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

  useEffect(() => {
    const newData = formik.values;
    for (const key in newData) {
      if (Object.hasOwnProperty.call(data, key)) {
        if (newData[key] !== data[key]) {
          setFieldsChange({ ...fieldsChange, [key]: newData[key] });
        } else {
          delete fieldsChange[key];
        }
      }
    }
    //eslint-disable-next-line
  }, [formik.values]);

  useEffect(() => {
    const id = param.id;
    const accessToken = account.accessToken;
    if (id && accessToken) {
      getTourGuideDetailApi(id, accessToken)
        .then((res) => {
          const data = res.data;
          const obj = { ...formik.values };
          for (let key in data) {
            obj[key] = data[key];
          }
          setData(data);
          formik.setValues(obj);
        })
        .catch((err) => {
          console.log(err.response);
        });
    }
    //eslint-disable-next-line
  }, [param, account, message]);

  return (
    <TourGuideDetails
      formik={formik}
      handleDeleteTourGuide={handleDeleteTourGuide}
    />
  );
};

export default TourGuideDetailsContainer;
