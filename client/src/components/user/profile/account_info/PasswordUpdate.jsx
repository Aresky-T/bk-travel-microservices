import React from "react";

const PasswordUpdate = ({
  formData,
  isUpdate,
  handleChangeForm,
  handleStartUpdate,
  handleCancelUpdate,
  handleSubmitUpdatePassword,
  checkKeyInErrors,
  renderErrorMessage,
}) => {
  return (
    <div className="profile__password-update">
      <div className="profile__note">
        <span>
          Hãy nhập mật khẩu hiện tại và mật khẩu mới để cập nhật mật khẩu.
          <br />
          Mật khẩu mới phải chứa từ <b>8 đến 20 ký tự</b>, bao gồm{" "}
          <b>chữ in hoa</b>, <b>chữ in thường</b>, <b>số</b> và{" "}
          <b>ký tự đặc biệt</b>.
        </span>
      </div>
      <div className="profile__password-update__main">
        {isUpdate ? (
          <form
            className="profile__password-update__form"
            onSubmit={handleSubmitUpdatePassword}
            onReset={handleCancelUpdate}
          >
            <div className="profile__password-update__form__item">
              <input
                type="password"
                name="currentPassword"
                placeholder="Nhập mật khẩu hiện tại"
                className={`profile-form__field ${checkKeyInErrors(
                  "currentPassword"
                )}`}
                value={formData.currentPassword}
                onChange={handleChangeForm}
              />
              {renderErrorMessage("currentPassword")}
            </div>
            <div className="profile__password-update__form__item">
              <input
                type="password"
                name="newPassword"
                placeholder="Nhập mật khẩu mới"
                className={`profile-form__field ${checkKeyInErrors(
                  "newPassword"
                )}`}
                value={formData.newPassword}
                onChange={handleChangeForm}
              />
              {renderErrorMessage("newPassword")}
            </div>
            <div className="profile__password-update__form__item">
              <input
                type="password"
                name="confirmPassword"
                placeholder="Nhập lại mật khẩu mới"
                className={`profile-form__field ${checkKeyInErrors(
                  "confirmPassword"
                )}`}
                value={formData.confirmPassword}
                onChange={handleChangeForm}
              />
              {renderErrorMessage("confirmPassword")}
            </div>
            <div className="btn-area">
              <input
                type="submit"
                value="Xác nhận"
                className="profile-form__btn submit"
              />
              <input
                type="reset"
                value="Hủy cập nhật"
                className="profile-form__btn reset"
              />
            </div>
          </form>
        ) : (
          <button className="profile-btn normal" onClick={handleStartUpdate}>
            Cập nhật
          </button>
        )}
      </div>
    </div>
  );
};

export default PasswordUpdate;
