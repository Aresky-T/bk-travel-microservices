import ChatBoxHeader from "../../../components/chat/header/ChatBoxHeader";
import { useDispatch } from "react-redux";
import { useAuth, useChat } from "../../../redux/selector";
import { useEffect, useState } from "react";
import ChatBoxBody from "../../../components/chat/body/ChatBoxBody";
import toast from "react-hot-toast";
import ValidateUtils from "../../../utils/validate";
import {
  connectEmployeeForGuestCustomerApi,
  connectEmployeeForRegisteredCustomerApi,
} from "../../../api/chat/chat.api";
import { getStompClient2 } from "../../../config/webSocketConfig";
import {
  onCancelChat,
  updateChatField,
  updateChatFields,
  updateChatFormField,
  updateChatStatusField,
  updateChatStatusMultipartFields,
} from "../../../redux/slices/chat.slice";
import CustomPopup from "../../../components/styled/popup/CustomPopup";

const ChatBoxContainer2 = ({ isShowChat, handleHiddenChat }) => {
  const account = useAuth();
  const chat = useChat();
  const { chatForm, employee, customer, id } = chat;
  const dispatch = useDispatch();
  const [errors, setErrors] = useState(new Map());

  const handleChangeFormData = (name, value) => {
    setErrors(undefined);
    dispatch(
      updateChatFormField({
        key: name,
        value,
      })
    );
  };

  const validateForm = () => {
    return ValidateUtils({
      formData: chatForm,
      rules: {
        fullName: {
          required: true,
        },
        email: {
          required: true,
          regex: /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/,
        },
      },
      messages: {
        fullName_required: "Tên không được để trống",
        email_required: "Email không được để trống",
        email_regex: "Email không đúng định dạng",
      },
    });
  };

  function updateStatus(client, status) {
    client.publish({
      destination: `/app/chat/update-status/customer/${customer.id}/${status}`,
    });
  }

  async function handleCancelChat() {
    const client = await getStompClient2();
    updateStatus(client, "OFFLINE");
    setTimeout(() => {
      client.publish({
        destination: `/app/chat/update-conversations/${employee.id}`,
      });
      dispatch(onCancelChat());
    }, 50);
    dispatch(onCancelChat());
  }

  async function handleSubmitSendMessage(e) {
    e.preventDefault();
    if (chatForm.message === "") return;
    try {
      const client = await getStompClient2();
      client.publish({
        // destination: `/app/chat/send.to.employee/conversation/${id}`,
        destination: `/app/chat/send.to.employee/${employee?.id}/${id}`,
        body: JSON.stringify({ message: chatForm.message, sender: "CUSTOMER" }),
      });
      setTimeout(() => {
        // client.publish({
        //     destination: `/app/chat/update-conversations/${employee?.id}`
        // })
        client.publish({
          destination: `/app/chat/refresh.conversation.for.customer/${id}`,
        });
        handleChangeFormData("message", "");
      }, 100);
    } catch (error) {}
  }

  async function handleGuestChat() {
    const { isValid, errors } = validateForm();
    if (!isValid) {
      setErrors(errors);
      return;
    }
    try {
      dispatch(updateChatStatusField({ key: "isConnecting", value: true }));

      const res = await connectEmployeeForGuestCustomerApi({
        fullName: chatForm.fullName,
        email: chatForm.email,
      });

      handleConnectionSuccess(res.data);
    } catch (error) {
      handleConnectionError();
    }
  }

  async function handleRegisteredChat() {
    if (!account.accessToken) {
      toast.error("Vui lòng đăng nhập trước khi tiếp tục!");
      dispatch(updateChatField({ key: "type", value: null }));
      return;
    }

    try {
      dispatch(updateChatStatusField({ key: "isConnecting", value: true }));
      const res = await connectEmployeeForRegisteredCustomerApi(
        account.accessToken
      );
      handleConnectionSuccess(res.data);
    } catch (error) {
      handleConnectionError();
    }
  }

  function handleConnectionSuccess(data) {
    const employeeId = data.employee.id;

    getStompClient2()
      .then((client) => {
        updateStatus(client, "ONLINE");
        setTimeout(() => {
          client.publish({
            destination: `/app/chat/update-conversations/${employeeId}`,
          });

          dispatch(
            updateChatStatusMultipartFields({
              isConnecting: false,
              isConnected: true,
            })
          );

          dispatch(updateChatFields(data));
        });
      })
      .catch((err) => {});
  }

  function handleConnectionError() {
    setTimeout(() => {
      toast.error("Kết nối thất bại!");
      dispatch(updateChatStatusField({ key: "isConnecting", value: false }));
      dispatch(updateChatField({ key: "type", value: null }));
    }, 500);
  }

  async function onConnectChat() {
    switch (chat.type) {
      case "GUEST":
        await handleGuestChat();
        break;
      case "REGISTERED":
        await handleRegisteredChat();
        break;
      default:
        break;
    }
  }

  useEffect(() => {
    if (chat.type === "REGISTERED") {
      onConnectChat();
    }
    //eslint-disable-next-line
  }, [chat.type]);

  useEffect(() => {
    if (id) {
      getStompClient2().then((client) => {
        client.subscribe(
          `/topic/customer/chat/conversation/${id}`,
          (message) => {
            const room = JSON.parse(message.body);
            const chatList = room.chatList;
            dispatch(updateChatFields({ chatList }));
          }
        );
      });
    }
  }, [id, dispatch]);

  useEffect(() => {
    return () => {
      if (employee.id) {
        getStompClient2().then((client) => {
          updateStatus(client, "OFFLINE");
          // client.publish({ destination: `/app/chat/update-conversations/${employee.id}` });
        });
      }
    };
    // eslint-disable-next-line
  }, []);

  return (
    <CustomPopup className={isShowChat ? "chat-box active" : "chat-box"}>
      <ChatBoxHeader
        formData={chatForm}
        handleHiddenChat={handleHiddenChat}
        handleCancelChat={handleCancelChat}
      />
      <ChatBoxBody
        chat={chat}
        formData={chatForm}
        errors={errors}
        handleChangeFormData={handleChangeFormData}
        handleConnectChat={onConnectChat}
        handleSubmitSendMessage={handleSubmitSendMessage}
      />
    </CustomPopup>
  );
};

export default ChatBoxContainer2;
