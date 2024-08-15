import { useEffect, useState } from "react";
import { useAppContext } from "../../store/context";
import ValidateUtils from "../../utils/validate";
import { useNavigate } from "react-router-dom";
import { loginEmployeeApi } from "../../api/auth.api";
import { AxiosError } from "axios";
import Login from "../../components/login";
import { PATHS } from "../../constants/paths";
import { ROLES } from "../../constants/roles";
import { EAccountStatus } from "../../types/enum";
import {
  LoginFailedAction,
  LoginSuccessAction,
  StartLoginAction,
} from "../../store/actions/auth.action";

export type FormLogin = {
  username: string;
  password: string;
};

export type ErrorsType = Map<string, { message: string }>;

const LoginContainer = () => {
  const { state, dispatch } = useAppContext();

  const [formLogin, setFormLogin] = useState<FormLogin>({
    username: "",
    password: "",
  });
  const [errors, setErrors] = useState<ErrorsType>(new Map());
  const navigate = useNavigate();

  const validate = (form: FormLogin) => {
    return ValidateUtils({
      form: form,
      rules: {
        username: {
          required: true,
          minLength: 5,
        },
        password: {
          required: true,
          minLength: 8,
          maxLength: 20,
        },
      },
      messages: {
        username_required: "Tên tài khoản không được để trống",
        username_minLength: "Tên tài khoản phải chứa ít nhất 5 ký tự",
        password_required: "Mật khẩu không được để trống",
        password_minLength: "Mật khẩu phải chứa tối thiểu 8 ký tự",
        password_maxLength: "Mật khẩu không được vượt quá 20 ký tự",
      },
    });
  };
  const handleChangeForm = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target) {
      const { name, value } = event.target;
      setFormLogin({
        ...formLogin,
        [name]: value,
      });
    }
  };
  const handleSubmitLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    resetErrors();
    const { isValid, errors } = validate(formLogin);
    if (isValid) {
      try {
        dispatch && StartLoginAction(dispatch);
        const res = await loginEmployeeApi(formLogin);
        const { role, status } = res.data;

        if (role !== ROLES.STAFF) {
          const message = "Bạn không phải nhân viên";
          errors.set("loginError", { message });
          setErrors(new Map(errors));
          dispatch && LoginFailedAction(dispatch, message);
          return;
        }

        if (status !== EAccountStatus.ACTIVE) {
          const message = "Tài khoản không được kích hoạt";
          errors.set("loginError", { message });
          setErrors(new Map(errors));
          dispatch && LoginFailedAction(dispatch, message);
          return;
        }

        dispatch && LoginSuccessAction(dispatch, res.data);
      } catch (error) {
        if (error instanceof AxiosError) {
          const message =
            error.response?.data?.message ||
            "Tài khoản hoặc mật khẩu không đúng";
          errors.set("loginError", { message });
          setErrors(new Map(errors));
          dispatch && LoginFailedAction(dispatch, message);
        }
      }
    } else {
      setErrors(errors);
    }
  };
  const renderError = (inputName: string, errors: ErrorsType) => {
    const error = errors.get(inputName);
    return error?.message || "";
  };
  const resetErrors = () => {
    setErrors(new Map());
  };

  useEffect(() => {
    const { data } = state.auth;
    if (data && data?.role === "STAFF") {
      navigate(PATHS.CHAT);
    }
    //eslint-disable-next-line
  }, [state.auth.data]);

  return (
    <div className="login-container main-session">
      <Login
        formLogin={formLogin}
        errors={errors}
        handleChangeForm={handleChangeForm}
        handleSubmitLogin={handleSubmitLogin}
        renderError={renderError}
      />
    </div>
  );
};

export default LoginContainer;
