import React from "react";
import Welcome from "./body/Welcome";
import SelectType from "./body/SelectType";
import GuestCustomerLogin from "./body/GuestCustomerLogin";
import Connected from "./body/Connected";
import { useChat } from "../../redux/selector";

const ConversationBody = ({
  isConnecting,
  message,
  handleChangeMessage,
  errors,
  formLogin,
  onClearErrors,
  onResetForm,
  handleChangeFormLogin,
  handleSubmitLogin,
  handleSubmitSendMessage,
  onConnectToStaff,
  onRegisterCustomer,
}) => {
  const chat = useChat();
  const staff = chat.staff;
  const isStarted = chat.isStarted;
  const isConnected = chat.isConnected;
  const customerType = chat.customerType;

  return (
    <div
      className="chat-box_body"
      style={
        isConnected
          ? { height: 450, paddingInline: 0 }
          : { height: 300, paddingInline: 25 }
      }
    >
      {isConnected ? (
        <Connected
          message={message}
          handleChangeMessage={handleChangeMessage}
          handleSubmitSendMessage={handleSubmitSendMessage}
        />
      ) : (
        <>
          {staff ? (
            <div className="chat-box_body--staff-info">
              <div className="staff-avatar">
                <img src={staff?.avatarUrl} alt="" />
              </div>
              <div className="staff-name">
                <span>
                  <b>Nhân viên tư vấn: </b>
                  {staff?.fullName}
                </span>
              </div>
              <div className="chat-button">
                <button type="button" onClick={onConnectToStaff}>
                  {isConnecting ? "Đang kết nối..." : "Bắt đầu cuộc trò chuyện"}
                </button>
              </div>
            </div>
          ) : (
            <>
              {!isStarted ? (
                <Welcome />
              ) : (
                <>
                  {!customerType ? (
                    <SelectType
                      isConnecting={isConnecting}
                      onRegisterCustomer={onRegisterCustomer}
                    />
                  ) : (
                    <>
                      {customerType === "GUEST_CUSTOMER" && (
                        <GuestCustomerLogin
                          errors={errors}
                          formLogin={formLogin}
                          handleChangeFormLogin={handleChangeFormLogin}
                          isConnecting={isConnecting}
                          handleSubmitLogin={handleSubmitLogin}
                          onClearErrors={onClearErrors}
                          onResetForm={onResetForm}
                        />
                      )}
                    </>
                  )}
                </>
              )}
            </>
          )}
        </>
      )}
    </div>
  );
};

export default ConversationBody;
