import React from "react";
import { AiFillMinusCircle } from "react-icons/ai";
import { IoAddCircle } from "react-icons/io5";
import TouristFields from "./TouristField";
import { toast } from "react-hot-toast";

const initTourist = {
  fullName: "",
  birthDate: "",
  gender: "",
};

const CustomerInfo = ({
  representative,
  handleChangeRepresentative,
  note,
  handleChangeNote,
  touristList,
  setTouristList,
  currentTour,
  errors,
}) => {
  function renderRepresentativeError(key) {
    if (errors instanceof Map && errors.has(key)) {
      return <div className="error-message">{errors.get(key)?.message}</div>;
    }
  }

  function addNewTourist() {
    const arr = [...touristList];
    const availableSeats = currentTour.availableSeats;
    const length = arr.length;

    if (length === availableSeats) {
      toast.error(
        `Số lượng hành khách không được vượt quá ${availableSeats} chỗ trống!`
      );
      return;
    }

    arr.push({ ...initTourist, id: length });
    setTouristList(arr);
  }

  function updateTourist(value) {
    const arr = [...touristList].map((tourist) => {
      if (tourist.id === value.id) {
        return value;
      }
      return tourist;
    });
    setTouristList(arr);
  }

  function removeTourist() {
    const arr = [...touristList];
    if (arr.length === 0) {
      return;
    }

    arr.pop();
    setTouristList(arr);
  }

  const renderTouristList = () => {
    const list = [...touristList];

    if (list.length === 0) {
      return (
        <div className="ci__tl__main__empty-tourists">
          Danh sách hành khách trống!
        </div>
      );
    }

    return [...touristList].map((tourist, index) => (
      <TouristFields
        label={`Hành khách ${tourist.id + 1}`}
        key={index}
        tourist={tourist}
        updateTourist={updateTourist}
      />
    ));
  };

  return (
    <>
      <div className="ci__sub-title">
        <span className="subtitle">Thông tin khách hàng</span>
      </div>
      <div className="ci__representative">
        <h3 className="cir__title">Người đại diện</h3>
        <p className="ci__representative__note">
          (Hãy nhập thông tin của quý khách để chúng tôi có thể liên hệ tới quý
          khách sau khi đặt tour thành công!)
        </p>
        <div className="cir-info--fields">
          <div className="cir-info__item">
            <label>Họ tên: </label>
            <input
              type="text"
              name="fullName"
              value={representative.fullName}
              onChange={handleChangeRepresentative}
            />
            {renderRepresentativeError("fullName")}
          </div>
          <div className="cir-info__item">
            <label>Email: </label>
            <input
              type="text"
              name="email"
              value={representative.email}
              onChange={handleChangeRepresentative}
            />
            {renderRepresentativeError("email")}
          </div>
          <div className="cir-info__item">
            <label>Số điện thoại: </label>
            <input
              type="text"
              name="phone"
              value={representative.phone}
              onChange={handleChangeRepresentative}
            />
            {renderRepresentativeError("phone")}
          </div>
          <div className="cir-info__item">
            <label>Địa chỉ: </label>
            <input
              type="text"
              name="address"
              value={representative.address}
              onChange={handleChangeRepresentative}
            />
            {renderRepresentativeError("address")}
          </div>
        </div>
      </div>
      <div className="ci__tourist-list">
        <h3 className="cit__title">Danh sách Hành khách</h3>
        <p className="ci__tourist-note">
          (Quý khách có thể thay đổi số lượng hành khách tương ứng với{" "}
          <b>người lớn</b>, <b>trẻ em</b>, <b>em bé</b> tại đây! Sau đó quý
          khách hãy nhập thông tin hành khách ở bên dưới)
        </p>
        <div className="ct__tourist-type">
          <div className="cit-type__item">
            <h5 className="cit-type__item__title">Số lượng hành khách</h5>
            <span className="cit-type__item__note">
              (Thêm bớt hành khách tại đây)
            </span>
            <div>
              <span onClick={removeTourist}>
                <AiFillMinusCircle />
              </span>
              <p>{touristList.length}</p>
              <span onClick={addNewTourist}>
                <IoAddCircle />
              </span>
            </div>
          </div>
        </div>
        <div className="ci__tl__main">
          <h5 className="ci__tl__main__title">
            Nhập danh sách khách hàng tại đây:
          </h5>
          <p className="ci__tl__main__note">
            (Hãy nhập đầy đủ thông tin của từng hành khách bao gồm <b>họ tên</b>
            , <b>ngày sinh</b>, <b>giới tính</b>!)
          </p>
          {renderTouristList()}
        </div>
      </div>
      <div className="ci__note">
        <h4 className="ci__note__title">Lời nhắn nhủ: </h4>
        <textarea
          name="note"
          cols="30"
          rows="10"
          className="note-area"
          placeholder="Quý khách có gì thắc mắc muốn gửi tới chúng tôi?"
          value={note}
          onChange={handleChangeNote}
        ></textarea>
      </div>
    </>
  );
};

export default CustomerInfo;
