import { useCallback, useEffect, useState } from "react";
import Sidebar from "../sidebar";
import ChatArea from "./chat_area";
import { useAppContext } from "../../store/context";
import { checkStaffPermissionApi } from "../../api/chat.api";
import Permission from "../../components/permission";
import {
  FetchAllConversationsAction,
  RemoveChatStaffInfoAction,
  SetChatStaffInfoAction,
} from "../../store/actions/chat.action";
import {
  WS_PUBLISH_STAFF_STATUS,
  WS_SUBSCRIBE_STAFF_ACTION,
  WS_SUBSCRIBE_STAFF_STATUS_ACTION,
} from "../../socket/actions";
import { EActivationStatus } from "../../types/enum";

const ChatContainer = () => {
  const { state, dispatch } = useAppContext();
  const auth = state.auth;
  const chat = state.chat;
  const email = auth.data.email;
  const staff = chat.staff;

  const [permission, setPermission] = useState<boolean>(false);

  const checkStaffPermission = useCallback(async () => {
    if (email) {
      try {
        const res = await checkStaffPermissionApi(email);
        const data = res.data;
        setPermission(data);
      } catch (err) {
        console.log(err);
      }
    }
  }, [email]);

  const getStaffInfo = useCallback(() => {
    if (!permission) {
      dispatch && RemoveChatStaffInfoAction(dispatch);
      return;
    }

    if (email) {
      dispatch && SetChatStaffInfoAction(email, dispatch);
    }

    //eslint-disable-next-line
  }, [email, permission]);

  const getAllConversations = useCallback(async () => {
    if (staff) {
      dispatch && FetchAllConversationsAction(dispatch, staff.id, 20, 0);
    }

    //eslint-disable-next-line
  }, [staff]);

  useEffect(() => {
    checkStaffPermission();
  }, [checkStaffPermission]);

  useEffect(() => {
    getStaffInfo();
  }, [getStaffInfo]);

  useEffect(() => {
    getAllConversations();
  }, [getAllConversations]);

  useEffect(() => {
    if (staff) {
      // subscribe status
      dispatch && WS_SUBSCRIBE_STAFF_STATUS_ACTION(staff.id, dispatch);

      // subscribe staff
      dispatch && WS_SUBSCRIBE_STAFF_ACTION(staff.id, dispatch);

      // online staff
      dispatch && WS_PUBLISH_STAFF_STATUS(staff.id, EActivationStatus.ONLINE);
    }

    // eslint-disable-next-line
  }, [staff]);

  return (
    <section className="chat-container main-section">
      {!permission && <Permission />}
      <Sidebar />
      <ChatArea />
    </section>
  );
};

export default ChatContainer;
