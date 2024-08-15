import React, { useDeferredValue, useEffect, useState } from "react";

const TouristFields = ({ label, tourist, updateTourist }) => {
  const [currentTourist, setCurrentTourist] = useState({
    fullName: "",
    birthDate: "",
    gender: "",
    ...tourist,
  });

  const deferredTourist = useDeferredValue(currentTourist);

  const handleChangeForm = (e) => {
    if (e.target) {
      const { name, value } = e.target;
      setCurrentTourist({
        ...currentTourist,
        [name]: value,
      });
    }
  };

  // useEffect(() => {
  //   if (tourist) {
  //     setCurrentTourist({
  //       ...tourist,
  //     });
  //   }
  //   // eslint-disable-next-line
  // }, [tourist]);

  useEffect(() => {
    updateTourist(deferredTourist);
    // eslint-disable-next-line
  }, [deferredTourist]);

  return (
    <div className="ci__tl__main__item">
      <label>{label}</label>
      <div className="ci__tl__main__fields">
        <div>
          <input
            type="text"
            name="fullName"
            placeholder="Họ tên"
            value={currentTourist.fullName || ""}
            onChange={handleChangeForm}
          />
        </div>
        <div>
          <input
            type="date"
            name="birthDate"
            placeholder="Ngày sinh"
            value={currentTourist.birthDate || new Date().toDateString()}
            onChange={handleChangeForm}
          />
        </div>
        <div>
          <select
            name="gender"
            onChange={handleChangeForm}
            value={currentTourist.gender || ""}
          >
            <option value="">Giới tính</option>
            <option value="MALE">Nam</option>
            <option value="FEMALE">Nữ</option>
            <option value="OTHER">Khác</option>
          </select>
        </div>
        {/* {formik.values.isRegister ?
                    <button type="button" className='tourist-info-btn--cancel'
                        onClick={handleCancelRegisterStatus}>Hủy đăng ký</button>
                    :
                    <button type='submit' className='tourist-info-btn--register'
                        onClick={formik.handleSubmit}>Đăng ký</button>
                } */}
      </div>
    </div>
  );
};

export default TouristFields;
