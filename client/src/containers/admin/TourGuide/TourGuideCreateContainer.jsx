import TourGuideCreate from "../../../components/admin/TourGuide/TourGuideCreate";
import { useFormik } from "formik";
import { validateTourGuide } from "../../../validation";
import { useDispatch } from "react-redux";
import { useAuth } from "../../../redux/selector";
import { createTourGuideApi } from "../../../api/admin/tour_guide.api";
import {
  errorAlert,
  successAlert,
  warningAlertNoCancel,
} from "../../../config/sweetAlertConfig";
import { useNavigate } from "react-router-dom";
import { ROUTE } from "../../../constant/route";
import { offLoading, onLoading } from "../../../redux/slices/loading.slice";

const TourGuideCreateContainer = () => {
  const account = useAuth();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const formik = useFormik({
    initialValues: {
      fullName: "",
      birthDate: new Date(),
      gender: "",
      phone: "",
      address: "",
      avatarUrl: "",
      description: "",
    },
    onSubmit: (values) => {
      validateTourGuide
        .validate(values)
        .then((data) => {
          dispatch(onLoading());
          account.accessToken &&
            createTourGuideApi(data, account.accessToken)
              .then((res) => {
                dispatch(offLoading());
                successAlert("Thành công", "Đã thêm mới", "OK").then(
                  (result) => {
                    if (result.isConfirmed) {
                      navigate(ROUTE.STAFF_MANAGER);
                    }
                  }
                );
              })
              .catch((err) => {
                console.log(err);
                dispatch(offLoading());
                errorAlert(
                  "Thất bại",
                  "Thêm mới không thành công, hãy kiểm tra lại!",
                  "OK"
                );
              });
        })
        .catch((err) => {
          console.log(err);
          warningAlertNoCancel(
            "Cảnh báo",
            "Dữ liệu đã nhập chưa đầy đủ hoặc không hợp lệ, hãy kiểm tra lại!",
            "OK"
          );
        });
    },
  });

  return <TourGuideCreate formik={formik} />;
};

export default TourGuideCreateContainer;
