import React from "react";
import PersonalInfoContainer from "./personal_info";
import AvatarUpdateContainer from "./avatar_update";
import PasswordUpdateContainer from "./password_update";

const AccountInfoContainer = () => {
  return (
    <div className="profile-container__main_item profile--account-info">
      <div className="profile-item--account-info">
        <div className="profile-container__main__title-1">
          Thông tin cá nhân
        </div>
        <PersonalInfoContainer />
      </div>
      <div className="profile-item--account-info">
        <div className="profile-container__main__title-1">Ảnh đại diện</div>
        <AvatarUpdateContainer />
      </div>
      <div className="profile-item--account-info">
        <div className="profile-container__main__title-1">Mật khẩu</div>
        <PasswordUpdateContainer />
      </div>
    </div>
  );
};

export default AccountInfoContainer;
