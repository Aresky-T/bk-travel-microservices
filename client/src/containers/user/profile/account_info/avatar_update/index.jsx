import React, { useState } from "react";
import { useProfile } from "../../../../../redux/selector";
import UpdateAvatarModal from "../../../../../components/user/profile/account_info/UpdateAvatarModal";
import userImage from "../../../../../assets/image/avatar.jpg";

const AvatarUpdateContainer = () => {
  const [isShowModal, setIsShowModal] = useState(false);
  const profile = useProfile();

  const handleShowModal = () => {
    !isShowModal && setIsShowModal(true);
  };

  const handleCloseModal = () => {
    setIsShowModal(false);
  };
  return (
    <div className="profile__avatar-update">
      <div className="profile__note">
        Ảnh đại điện được hiển thị trong hồ sơ, các bài đánh giá,...và có thể
        cập nhật ở dưới đây.
      </div>
      <div className="profile__avatar-image">
        <img src={profile?.avatarUrl ?? userImage} alt="" loading="lazy" />
      </div>
      <div className="update-btn">
        <button onClick={handleShowModal} className="profile-btn normal">
          {profile.avatarUrl ? "Thay đổi" : "Cập nhật"}
        </button>
        {isShowModal && (
          <UpdateAvatarModal handleCloseModal={handleCloseModal} />
        )}
      </div>
    </div>
  );
};

export default AvatarUpdateContainer;
