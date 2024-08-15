import React, { useEffect } from "react";
import TouristAttractionCreate from "../../../components/admin/TouristAttraction/TouristAttractionCreate";
import { useFormik } from "formik";
import { validateTouristAttraction } from "../../../validation";
import { createTouristAttractionApi } from "../../../api/admin/tourist_attraction.api";
import { errorAlert, successAlert } from "../../../config/sweetAlertConfig";
import { useNavigate } from "react-router-dom";
import { ROUTE } from "../../../constant/route";
import { useAuth } from "../../../redux/selector";

const initBlogContent = {
  subTitle: "",
  image: "",
  content: "",
};

const TouristAttractionCreateContainer = () => {
  const account = useAuth();
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      name: "",
      title: "",
      imageUrl: "",
      intro: "",
      listContents: [],
    },
    onSubmit: (values) => {
      validateTouristAttraction
        .validate(values, { abortEarly: true })
        .then((data) => {
          console.log(data);
          createTouristAttractionApi(data, account.accessToken)
            .then((res) => {
              successAlert(
                "Thành công",
                "Địa điểm du lịch mới đã được tạo thành công!",
                "OK"
              )
                .then((result) => {
                  if (result.isConfirmed) {
                    navigate(ROUTE.TOURIST_ATTRACTION_MANAGER);
                  }
                })
                .catch((err) => {
                  console.log(err);
                });
            })
            .catch((err) => {
              errorAlert(
                "Thất bại",
                "Tạo mới không thành công, vui lòng thử lại!"
              );
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err.message);
        });
    },
  });

  const addBlogContent = () => {
    const contents = formik.values.listContents;
    contents.push({ ...initBlogContent });
    formik.setFieldValue("listContents", contents);
  };

  const removeBlogContent = (content) => {
    const contents = formik.values.listContents;
    if (contents.length <= 1) {
      return;
    }
    const index = contents.indexOf(content);
    contents.splice(index, 1);
    formik.setFieldValue("listContents", contents);
  };

  useEffect(() => {
    const contents = formik.values.listContents;
    if (contents.length < 1) {
      contents.push({ ...initBlogContent });
    }
    formik.setFieldValue("listContents", contents);
    //eslint-disable-next-line
  }, []);

  return (
    <TouristAttractionCreate
      addBlogContent={addBlogContent}
      formik={formik}
      removeBlogContent={removeBlogContent}
    />
  );
};

export default TouristAttractionCreateContainer;
