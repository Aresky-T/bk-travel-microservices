import React, { useCallback, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import AppRouter from "./router";
import "react-toastify/dist/ReactToastify.css";
import { useAppContext } from "./store/context";
import { validateTokenApi } from "./api/auth.api";
import { PATHS } from "./constants/paths";
// import { IMailBox } from "./types/mail";
import "sweetalert2/src/sweetalert2.scss";
// import { IConversation } from "./types/chat";
// import { getAllConversationsForEmployeeApi } from './api/chat.api';
// import { Client } from "@stomp/stompjs";
// import {
//   BROKER_CHAT_CONVERSATIONS_LIST,
//   BROKER_MAIL_BOXES_LIST,
// } from "./constants/brokers";
import { ROLES } from "./constants/roles";
import { getProfileApi } from "./api/account.api";
import { SetProfileStateDataAction } from "./store/actions/profile.action";

function App() {
  const { state, dispatch } = useAppContext();
  const auth = state.auth;
  const account = auth.data;
  // const { currentMailBox, mailBoxList } = state.mail;
  // const { currentConversation, conversations } = state.chat;
  const navigate = useNavigate();

  const handleGetProfile = useCallback(() => {
    const accountId = account.id;
    if (!accountId) return;

    getProfileApi(accountId)
      .then((res) => {
        dispatch && SetProfileStateDataAction(dispatch, res.data);
      })
      .catch((err) => {});
  }, [account, dispatch]);

  // const handleGetDetailsEmployee = async (token: string) => {
  //   try {
  //     const response = await getDetailsEmployeeApi(token);
  //     const employeeInfo = response.data;
  //     dispatch &&
  //       dispatch({ type: "EMPLOYEE|ADD_DATA", payload: employeeInfo });
  //   } catch (error) {}
  // };

  const handleValidateToken = async (token: string) => {
    try {
      const res = await validateTokenApi(token);
      const isValid = res.data;
      if (!isValid) {
        localStorage.removeItem("acc_info");
        dispatch && dispatch({ type: "AUTH|HANDLE_LOGOUT" });
        navigate(PATHS.LOGIN);
      }
    } catch (err) {}
  };

  // const onSubscribeConversationsList = (
  //   client: Client,
  //   employee_id: number
  // ) => {
  //   client.subscribe(BROKER_CHAT_CONVERSATIONS_LIST(employee_id), (message) => {
  //     const data = JSON.parse(message.body) as IConversation[];
  //     dispatch &&
  //       dispatch({
  //         type: "CHAT|SET|CONVERSATION_LIST",
  //         payload: data,
  //       });
  //   });
  // };

  // const onSubscribeMailBoxesList = (client: Client, employee_id: number) => {
  //   client.subscribe(BROKER_MAIL_BOXES_LIST(employee_id), (message) => {
  //     const data = JSON.parse(message.body) as IMailBox[];

  //     // if (currentMailBox) {
  //     //   client.publish({
  //     //     destination: `/app/mail/load.mailbox.of.employee/${currentMailBox.id}`
  //     //   })
  //     // }

  //     dispatch &&
  //       dispatch({
  //         type: "MAIL|SET|MAIL_BOXES_LIST",
  //         payload: data,
  //       });
  //   });
  // };

  useEffect(() => {
    if (account.token && account.role === ROLES.STAFF) {
      handleValidateToken(account.token);
    }
    // eslint-disable-next-line
  }, [account]);

  // useEffect(() => {
  //   const connectSocket = async (employeeId: number) => {
  //     try {
  //       // get stomp client
  //       const client = await getStompClient();
  //       onSubscribeConversationsList(client, employeeId);
  //       onSubscribeMailBoxesList(client, employeeId);
  //       // send message
  //       client.publish({
  //         destination: `/app/mail/load.mailboxes.of.employee/${employeeId}`,
  //       });
  //       client.publish({
  //         destination: `/app/chat/get-conversations/${employeeId}`,
  //       });
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   };

  //   if (employee.data) {
  //     connectSocket(employee.data.id);
  //   }
  //   // eslint-disable-next-line
  // }, [employee.data, dispatch]);

  // useEffect(() => {
  //   if (currentMailBox && mailBoxList.length) {
  //     mailBoxList.forEach((item) => {
  //       if (item.id === currentMailBox.id) {
  //         dispatch &&
  //           dispatch({
  //             type: "MAIL|UPDATE|CURRENT_MAIL_BOX",
  //             payload: { ...item },
  //           });
  //       }
  //     });
  //   }
  //   // eslint-disable-next-line
  // }, [mailBoxList]);

  useEffect(() => {
    handleGetProfile();
  }, [handleGetProfile]);

  return (
    <>
      <AppRouter />
    </>
  );
}

export default App;
