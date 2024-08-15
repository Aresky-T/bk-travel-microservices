import React, { useCallback, useEffect, useState } from "react";
import ValidateUtils from "../../../utils/validate";
import { useAuth, useChat, useProfile } from "../../../redux/selector";
import CustomPopup from "../../../components/styled/popup/CustomPopup";
import ConversationHeader from "../../../components/chat/ConversationHeader";
import ConversationBody from "../../../components/chat/ConversationBody";
import toast from "react-hot-toast";
import { compareDates } from "../../../utils/compare";
import { useDispatch } from "react-redux";
import {
  updateChatCustomerInfo,
  updateChatFields,
  updateChatMessages,
  updateChatStaffInfo,
} from "../../../redux/slices/chat.slice";
import {
  connectToStaffApi,
  findOnlineStaffApi,
  getAllMessagesApi,
  registerCustomerApi,
} from "../../../api/chat/chat.api";
import {
  WS_CONNECT_TO_STAFF_ACTION,
  WS_DISCONNECT_TO_STAFF_ACTION,
  WS_PUBLISH_SEND_MESSAGE_TO_STAFF_ACTION,
  WS_SUBSCRIBE_CONVERSATION_ACTION,
  WS_SUBSCRIBE_STAFF_ACTION,
} from "../../../socket/actions";

const REGISTERED_CUSTOMER = "REGISTERED_CUSTOMER";
const GUEST_CUSTOMER = "GUEST_CUSTOMER";

const ConversationContainer = () => {
  const [message, setMessage] = useState({
    sender: "CUSTOMER",
    status: "SENDING",
    content: "",
    sentAt: new Date(),
  });

  const [formLogin, setFormLogin] = useState({
    fullName: "",
    email: "",
  });

  const [isConnecting, setIsConnecting] = useState(false);
  const [errors, setErrors] = useState(new Map());

  const auth = useAuth();
  const chat = useChat();
  const profile = useProfile();
  const dispatch = useDispatch();

  const conversationId = chat.conversationId;
  const customer = chat.customer;
  const staff = chat.staff;
  const isShowBox = chat.isShowBox;

  const handleChangeFormLogin = (name, value) => {
    setFormLogin((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  };

  const handleChangeMessage = (event) => {
    setMessage((prev) => ({ ...prev, content: event.target.value }));
  };

  const clearMessageContent = () => {
    setMessage((prev) => ({ ...prev, content: "" }));
  };

  const onValidateFormLogin = () => {
    return ValidateUtils({
      formData: formLogin,
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

  const handleClearErrors = () => setErrors(new Map());

  const handleResetForm = () => setFormLogin({ email: "", fullName: "" });

  const handleSubmitLogin = () => {
    const { isValid, errors } = onValidateFormLogin();
    if (!isValid) {
      setErrors(errors);
      return;
    }

    handleClearErrors();
    handleRegisterCustomer(GUEST_CUSTOMER);
  };

  const handleRegisterCustomer = async (type) => {
    setIsConnecting(true);
    try {
      if (type === REGISTERED_CUSTOMER) {
        const res = await handleRegister_RegisteredCustomer();
        dispatch(updateChatCustomerInfo(res.data));
      } else if (type === GUEST_CUSTOMER) {
        const res = await handleRegister_GuestCustomer();
        dispatch(updateChatCustomerInfo(res.data));
      } else {
        return;
      }
    } catch (err) {
      setTimeout(() => {
        toast.error("Không thể đăng ký thông tin của bạn!");
        setIsConnecting(false);
        handleResetForm();
      }, 1000);
    }
  };

  const handleRegister_GuestCustomer = () => {
    const { fullName, email } = formLogin;
    return registerCustomerApi(fullName, email);
  };

  const handleRegister_RegisteredCustomer = () => {
    if (!auth || !auth.email) {
      toast.error("Vui lòng đăng nhập trước khi tiếp tục");
      setIsConnecting(false);
      return;
    }

    if (!profile.fullName) {
      toast.error("Vui lòng cập nhật hồ sơ của bạn trước khi tiếp tục");
      setIsConnecting(false);
      return;
    }

    const { email } = auth;
    const { fullName } = profile;

    return registerCustomerApi(fullName, email);
  };

  const onFindOnlineStaff = async () => {
    try {
      const res = await findOnlineStaffApi();
      setTimeout(() => {
        setIsConnecting(false);
        handleResetForm();
        dispatch(updateChatStaffInfo(res.data));
      }, 500);
    } catch (error) {
      setTimeout(() => {
        toast.error("Không tìm thấy nhân viên nào đang trực tuyến!");
        setIsConnecting(false);
        dispatch(updateChatCustomerInfo(null));
      }, 1000);
    }
  };

  const handleConnectToStaff = async () => {
    setIsConnecting(true);
    try {
      const res = await connectToStaffApi(customer.id, staff.id);
      const conversation = res.data;

      setTimeout(() => {
        setIsConnecting(false);
        dispatch(
          updateChatFields({
            conversationId: conversation.id,
            isConnected: true,
          })
        );
      }, 500);
    } catch (error) {
      setTimeout(() => {
        setIsConnecting(false);
        toast.error("Kết nối cuộc trò truyện thất bại!");
      }, 1000);
    }
  };

  const handleDisconnectStaff = () => {
    if (customer && staff) {
      WS_DISCONNECT_TO_STAFF_ACTION(customer.id, staff.id);
    }
  };

  const handleSubmitSendMessage = (event) => {
    event.preventDefault();
    // dispatch(updateChatSendingMessage(message));

    const formData = {
      senderId: customer.id,
      sender: "CUSTOMER",
      content: message.content,
    };

    clearMessageContent();
    WS_PUBLISH_SEND_MESSAGE_TO_STAFF_ACTION(staff.id, formData);
  };

  const handleGetAllMessage = useCallback(async () => {
    if (conversationId) {
      try {
        const res = await getAllMessagesApi(conversationId, 20, 0);
        const data = res.data;
        data.sort((a, b) =>
          compareDates(new Date(a.sentAt), new Date(b.sentAt))
        );

        dispatch(updateChatMessages(data));
      } catch (error) {}
    }
    //eslint-disable-next-line
  }, [conversationId]);

  useEffect(() => {
    handleGetAllMessage();
  }, [handleGetAllMessage]);

  useEffect(() => {
    if (conversationId) {
      WS_SUBSCRIBE_CONVERSATION_ACTION(conversationId, dispatch);
    }
    //eslint-disable-next-line
  }, [conversationId]);

  useEffect(() => {
    if (customer && staff) {
      WS_CONNECT_TO_STAFF_ACTION(customer.id, staff.id);
    }
    //eslint-disable-next-line
  }, [customer, staff]);

  useEffect(() => {
    if (staff) {
      WS_SUBSCRIBE_STAFF_ACTION(staff.id, dispatch);
    }
    //eslint-disable-next-line
  }, [staff]);

  useEffect(() => {
    if (customer) {
      onFindOnlineStaff();
    }
    //eslint-disable-next-line
  }, [customer]);

  return (
    <CustomPopup className={isShowBox ? "chat-box active" : "chat-box"}>
      <ConversationHeader onDisconnectConversation={handleDisconnectStaff} />
      <ConversationBody
        isConnecting={isConnecting}
        message={message}
        errors={errors}
        formLogin={formLogin}
        onClearErrors={handleClearErrors}
        onResetForm={handleResetForm}
        handleChangeMessage={handleChangeMessage}
        handleChangeFormLogin={handleChangeFormLogin}
        handleSubmitLogin={handleSubmitLogin}
        handleSubmitSendMessage={handleSubmitSendMessage}
        onConnectToStaff={handleConnectToStaff}
        onRegisterCustomer={handleRegisterCustomer}
      />
    </CustomPopup>
  );
};

export default ConversationContainer;
