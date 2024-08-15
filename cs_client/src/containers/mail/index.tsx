import { useCallback, useEffect, useState } from "react";
import Sidebar from "../sidebar";
import MailArea from "./mail_area";
import Permission from "../../components/permission";
import { useAppContext } from "../../store/context";
import { checkStaffPermissionApi, getStaffInfoApi } from "../../api/mail.api";
import {
  FetchAllMailBoxesAction,
  SetMailStaffInfoAction,
} from "../../store/actions/mail.action";
import { EActivationStatus } from "../../types/enum";

const MailContainer = () => {
  const [permission, setPermission] = useState<boolean>(false);
  const { state, dispatch } = useAppContext();
  const auth = state.auth;
  const email = auth.data.email;

  const handleCheckStaffPermission = useCallback(() => {
    if (!email) return;

    checkStaffPermissionApi(email)
      .then((res) => {
        setPermission(res.data);
      })
      .catch((err) => {});
  }, [email]);

  const handleGetStaffInfo = useCallback(() => {
    if (email && permission) {
      getStaffInfoApi(email)
        .then((res) => {
          const { id, fullName, email, avatarUrl } = res.data;
          dispatch &&
            SetMailStaffInfoAction(dispatch, {
              id,
              fullName,
              email,
              avatarUrl,
              status: EActivationStatus.ONLINE,
            });
        })
        .catch((err) => {});
    }
  }, [email, permission, dispatch]);

  const getAllMailBoxes = useCallback(() => {
    if (!email || !permission) return;
    dispatch && FetchAllMailBoxesAction(dispatch, email);
  }, [email, permission, dispatch]);

  useEffect(() => {
    getAllMailBoxes();
  }, [getAllMailBoxes]);

  useEffect(() => {
    handleGetStaffInfo();
  }, [handleGetStaffInfo]);

  useEffect(() => {
    handleCheckStaffPermission();
  }, [handleCheckStaffPermission]);

  return (
    <section className="mail-container main-section">
      {!permission && <Permission />}
      <Sidebar />
      <MailArea />
    </section>
  );
};

export default MailContainer;
