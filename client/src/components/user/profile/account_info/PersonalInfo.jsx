import React from "react";

const PersonalInfo = ({
  profile,
  formData,
  isUpdate,
  onCancelUpdate,
  onStartUpdate,
  handleChangeForm,
  handleResetForm,
  handleSubmitForm,
  renderErrorMessage,
  checkKeyInErrors,
  renderGender,
  renderDateOfBirth,
}) => {
  return (
    <div className="profile__personal-info">
      <div className="profile__note">
        Thông tin cá nhân có thể được sử dụng để kết nối chat, mail, hoặc hiển
        thị trong các bài đánh giá. <br />
        Thông tin không được để trống và phải đúng định dạng.
      </div>
      <div className="profile__personal-info__main">
        <form
          className="profile__personal-info__form"
          onReset={handleResetForm}
        >
          <div className="profile__personal-info__form__item fullName">
            <label className="profile__personal-info__label">
              Tên đầy đủ:{" "}
              <strong>{profile.fullName || "Không xác định"}</strong>
            </label>
            {isUpdate && (
              <>
                <input
                  type="text"
                  name="fullName"
                  className={`profile-form__field ${checkKeyInErrors(
                    "fullName"
                  )}`}
                  placeholder="Không xác định"
                  value={formData.fullName}
                  onChange={handleChangeForm}
                />
                {renderErrorMessage("fullName")}
              </>
            )}
          </div>
          <div className="profile__personal-info__form__item dateOfBirth">
            <label className="profile__personal-info__label">
              Ngày sinh:{" "}
              <strong>{renderDateOfBirth(profile.dateOfBirth)}</strong>
            </label>
            {isUpdate && (
              <>
                <input
                  type="date"
                  name="dateOfBirth"
                  className={`profile-form__field ${checkKeyInErrors(
                    "dateOfBirth"
                  )}`}
                  placeholder="Không xác định"
                  value={formData.dateOfBirth}
                  onChange={handleChangeForm}
                />
                {renderErrorMessage("dateOfBirth")}
              </>
            )}
          </div>
          <div className="profile__personal-info__form__item address">
            <label className="profile__personal-info__label">
              Địa chỉ: <strong>{profile.address || "Không xác định"}</strong>
            </label>
            {isUpdate && (
              <>
                <input
                  type="text"
                  name="address"
                  className={`profile-form__field ${checkKeyInErrors(
                    "address"
                  )}`}
                  placeholder="Không xác định"
                  value={formData.address}
                  onChange={handleChangeForm}
                />
                {renderErrorMessage("address")}
              </>
            )}
          </div>
          <div className="profile__personal-info__form__item phone">
            <label className="profile__personal-info__label">
              Số điện thoại:{" "}
              <strong>{profile.phone || "Không xác định"}</strong>
            </label>
            {isUpdate && (
              <>
                <input
                  type="text"
                  name="phone"
                  className={`profile-form__field ${checkKeyInErrors("phone")}`}
                  placeholder="Không xác định"
                  value={formData.phone}
                  onChange={handleChangeForm}
                />
                {renderErrorMessage("phone")}
              </>
            )}
          </div>
          <div className="profile__personal-info__form__item gender">
            <label className="profile__personal-info__label">
              Giới tính: <strong>{renderGender(profile.gender)}</strong>
            </label>
            {isUpdate && (
              <>
                <select
                  name="gender"
                  className={`profile-form__field ${checkKeyInErrors(
                    "gender"
                  )}`}
                  value={formData.gender}
                  onChange={handleChangeForm}
                >
                  <option value="">Không xác định</option>
                  <option value="MALE">Nam</option>
                  <option value="FEMALE">Nữ</option>
                  <option value="OTHER">Khác</option>
                </select>
                {renderErrorMessage("gender")}
              </>
            )}
          </div>
          <div className="profile__personal-info__form__item btn-area">
            {isUpdate ? (
              <>
                <button
                  type="button"
                  className="profile-form__btn submit"
                  onClick={handleSubmitForm}
                >
                  Xác nhận
                </button>
                <button
                  type="reset"
                  className="profile-form__btn reset"
                  onClick={onCancelUpdate}
                >
                  Hủy cập nhật
                </button>
              </>
            ) : (
              <button
                type="button"
                className="profile-form__btn normal"
                onClick={onStartUpdate}
              >
                Cập nhật
              </button>
            )}
          </div>
        </form>
      </div>
    </div>
  );
};

export default PersonalInfo;
