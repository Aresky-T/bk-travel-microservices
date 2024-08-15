import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { useFormik } from "formik";
import { toast } from "react-hot-toast";
import { saveAccountInfo } from "../../../redux/slices/auth.slice";
import Login from "../../../components/admin/Login/Login";
import { useAuth } from "../../../redux/selector";
import { ROUTE } from "../../../constant/route";
import { ROLE } from "../../../constant/role";
import { loginAdminApi } from "../../../api/global/auth.api";

const LoginAdminContainer = () => {
  const yup = require("yup");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const accountInfo = useAuth();

  const authFormik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema: yup.object().shape({
      username: yup.string().required("Required"),
      password: yup.string().required("Required"),
    }),
    onSubmit: (values) => {
      handleSubmitForm(values);
    },
  });

  function handleSubmitForm(data) {
    const loading = toast.loading("Đang xử lý", { position: "top-center" });
    loginAdminApi(data)
      .then((res) => {
        setTimeout(() => {
          toast.remove(loading);
          if (res.data.role === "ADMIN") {
            toast.success("Thành công");
            const { id, token, email, username, role, type, status } = res.data;
            dispatch(
              saveAccountInfo({
                id,
                token,
                email,
                username,
                role,
                type,
                status,
              })
            );
          } else {
            toast.remove(loading);
            toast.error("Bạn không phải admin!", { duration: 1000 });
          }
        }, 500);
      })
      .catch((err) => {
        setTimeout(() => {
          toast.remove(loading);
          toast.error(
            err.response ? err.response.data.message : "Lỗi máy chủ",
            { duration: 1000 }
          );
        }, 1000);
      });
  }

  useEffect(() => {
    if (accountInfo.role === ROLE.ADMIN) {
      navigate(ROUTE.ADMIN_HOME);
    }
  }, [accountInfo, navigate]);

  return (
    <div className="admin-login-container">
      <Login authFormik={authFormik} />
    </div>
  );
};

export default LoginAdminContainer;
