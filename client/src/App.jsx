import React, { useCallback, useEffect } from "react";
import { Toaster } from "react-hot-toast";
import Loading from "./components/global/Loading/Loading";
import AppRouter from "./router/AppRouter";
import { useDispatch } from "react-redux";
import { useAuth } from "./redux/selector";
// import { logout } from "./redux/slices/auth.slice";
import { validateTokenApi } from "./api/global/auth.api";
// import { warningAlert } from "./config/sweetAlertConfig";
import { HelmetProvider } from "react-helmet-async";
import { getProfileThunk } from "./redux/slices/profile.slice";
import "react-toastify/dist/ReactToastify.css";
import { getNewNotificationCountApi } from "./api/notification/notification.api";
import { onUpdateNewNotificationCount } from "./redux/slices/notification.slice";
import { logout } from "./redux/slices/auth.slice";

function App() {
  const auth = useAuth();
  const dispatch = useDispatch();

  const handleValidateToken = useCallback(async () => {
    if (auth && auth.accessToken) {
      try {
        const token = auth.accessToken;
        const res = await validateTokenApi(token);
        const isValidToken = res.data;
        if (isValidToken) {
          dispatch(getProfileThunk(auth.id));
        } else {
          dispatch(logout());
        }
      } catch (e) {
        // const message =
        //   e?.response?.data?.message ||
        //   "Không thể xác thực thông tin người dùng!";
        // warningAlert("Cảnh báo", message);
        // warningAlertNoCancel("Cảnh báo", message)
        //   .then((result) => {
        //     if (result.isConfirmed) {
        //       navigate(ROUTE.LOGIN);
        //       dispatch(logout());
        //     }
        //   })
        //   .catch((err) => {});
        //handle catch error here
      }
    }
    // eslint-disable-next-line
  }, [auth]);

  const checkNotification = useCallback(() => {
    if (auth && auth.id) {
      getNewNotificationCountApi(auth.id)
        .then((res) => {
          if (res.data > 0) {
            setTimeout(() => {
              dispatch(onUpdateNewNotificationCount(res.data));
            }, 500);
          }
        })
        .catch((err) => {});
    }
    //eslint-disable-next-line
  }, [auth]);

  useEffect(() => {
    checkNotification();
  }, [checkNotification]);

  useEffect(() => {
    handleValidateToken();
    //eslint-disable-next-line
  }, [handleValidateToken]);

  return (
    <>
      <HelmetProvider>
        <AppRouter />
      </HelmetProvider>
      <Toaster position="top center" />
      <Loading />
    </>
  );
}

export default App;
