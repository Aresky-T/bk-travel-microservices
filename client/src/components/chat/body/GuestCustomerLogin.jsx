import React from "react";
import TextField from "../../styled/TextField";
import { ChatButton } from "./ChatBoxBody";
import { GrFormPrevious } from "react-icons/gr";
import { updateChatCustomerType } from "../../../redux/slices/chat.slice";
import { useDispatch } from "react-redux";

const GuestCustomerLogin = ({
  formLogin,
  errors,
  isConnecting,
  handleChangeFormLogin,
  handleSubmitLogin,
  onClearErrors,
  onResetForm,
}) => {
  const dispatch = useDispatch();

  return (
    <div className="chat-box_body--customer-info">
      <div
        style={{ textAlign: "center", position: "relative" }}
        className="chat-box_body--customer-info_head flex-center"
      >
        <div
          className="chat-box__icon"
          style={{ position: "absolute", left: 0 }}
        >
          <button
            onClick={() => {
              onClearErrors();
              onResetForm();
              dispatch(updateChatCustomerType(null));
            }}
          >
            <GrFormPrevious />
          </button>
        </div>
        <strong style={{ justifySelf: "center", userSelect: "none" }}>
          Nhập thông tin
        </strong>
      </div>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleSubmitLogin();
        }}
      >
        <div>
          <TextField
            name="fullName"
            label={"Tên khách hàng"}
            value={formLogin.fullName}
            onChange={handleChangeFormLogin}
          />
          <div className="error-message">
            {errors?.get("fullName")?.message}
          </div>
        </div>
        <div>
          <TextField
            name="email"
            label={"Địa chỉ email"}
            value={formLogin.email}
            onChange={handleChangeFormLogin}
          />
          <div className="error-message">{errors?.get("email")?.message}</div>
        </div>
        <div>
          <ChatButton type="submit">
            {isConnecting ? "Đang kết nối..." : "Tiếp tục với vai trò khách"}
          </ChatButton>
        </div>
      </form>
    </div>
  );
};

export default GuestCustomerLogin;
