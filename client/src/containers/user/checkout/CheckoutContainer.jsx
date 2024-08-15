import React, { useCallback, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import vnpayLogo from "../../../assets/image/vnpay_logo.png";
import { useAuth, useBooking } from "../../../redux/selector";
import { removeBookingInfo } from "../../../redux/slices/booking.slice";
import { differenceInYears } from "date-fns";
import { useFormik } from "formik";
import {
  bookTourAndPaymentWithVNPayApi,
  bookTourForUserApi,
} from "../../../api/user/booking.api";

import { offLoading } from "../../../redux/slices/loading.slice";
import { successAlert, warningAlert } from "../../../config/sweetAlertConfig";
import { ROUTE } from "../../../constant/route";
import { AxiosError } from "axios";
import { useDispatch } from "react-redux";

const CheckoutContainer = () => {
  const navigate = useNavigate();
  const auth = useAuth();
  const accountId = auth.id;
  const { bookingInfo } = useBooking();
  const tour = bookingInfo.selectedTour;
  const representative = bookingInfo.representative;
  const touristList = bookingInfo.touristList;
  const note = bookingInfo.note;
  const dispatch = useDispatch();

  const formik = useFormik({
    initialValues: {
      amount: 0,
      adultNumber: 0,
      childrenNumber: 0,
      babyNumber: 0,
      touristList: [],
    },
  });

  const onReturnToBookingPage = () => {
    navigate(`/booking/${tour.tourCode}`);
  };

  const handleCancelBooking = () => {
    dispatch(removeBookingInfo());
  };

  function calculateAgeOfTourist(tourist) {
    const birthDate = new Date(tourist.birthDate);
    const currentDate = new Date();
    const age = differenceInYears(currentDate, birthDate);
    return age;
  }

  const manageBookingAndCalculatePrice = useCallback(() => {
    if (!tour || !touristList || touristList.length === 0) {
      return;
    }

    let amount = 0;
    let adultNumber = 0;
    let childrenNumber = 0;
    let babyNumber = 0;

    const adultPrice = tour?.adultPrice;
    const childPrice = tour?.childrenPrice;
    const babyPrice = tour?.babyPrice;

    const newList = touristList.map((tourist) => {
      const age = calculateAgeOfTourist(tourist);
      if (age >= 12) {
        amount += adultPrice;
        adultNumber++;
      } else if (age >= 2 && age <= 11) {
        amount += childPrice;
        childrenNumber++;
      } else {
        amount += babyPrice;
        babyNumber++;
      }
      return tourist;
    });

    formik.setValues({
      amount,
      adultNumber,
      childrenNumber,
      babyNumber,
      touristList: newList,
    });

    // eslint-disable-next-line
  }, [tour, touristList]);

  const renderPrice = (price) => {
    if (price === 0) {
      return <b>miễn phí</b>;
    }

    if (typeof price === "number") {
      return (
        <>
          <b>{price.toLocaleString("vi-VN")}</b> vnd - 1 hành khách
        </>
      );
    }
  };

  const renderGender = (gender) => {
    switch (gender) {
      case "MALE":
        return "Nam";
      case "FEMALE":
        return "Nữ";
      case "OTHER":
        return "Không xác định";
      default:
    }
  };

  const renderBirthDate = (birthDate) => {
    return birthDate.toLocaleString("vi-VN");
  };

  const handleSubmitBooking = (type) => {
    const { adultNumber, babyNumber, childrenNumber, amount } = formik.values;
    const bookingMap = new Map();
    bookingMap.set("subTourId", tour.id);
    bookingMap.set("accountId", accountId);
    bookingMap.set("fullName", representative.fullName);
    bookingMap.set("email", representative.email);
    bookingMap.set("phone", representative.phone);
    bookingMap.set("address", representative.address);
    bookingMap.set("note", note);
    bookingMap.set("touristList", touristList);
    bookingMap.set("adultNumber", adultNumber);
    bookingMap.set("childrenNumber", childrenNumber);
    bookingMap.set("babyNumber", babyNumber);
    bookingMap.set("amount", amount);

    const formData = {};
    bookingMap.entries();
    bookingMap.forEach((value, key) => {
      formData[key] = value;
    });

    switch (type) {
      case "VNPAY":
        bookTourAndPaymentWithVNPayApi(formData)
          .then((res) => {
            dispatch(offLoading());
            const returnURL = res.data;
            window.open(
              returnURL,
              "_blank"
              // "width=1000,height=1000,scrollbars=yes"
            );
          })
          .catch((err) => {
            if (err instanceof AxiosError) {
              const message =
                err.response?.data?.message ??
                "Không thể đặt tour, vui lòng kiểm tra lại!";
              handlePaymentError(message);
            }
          });

        break;
      case "PAY_LATER":
        bookTourForUserApi(formData)
          .then((res) => {
            // dispatch(offLoading());
            successAlert(
              "Chúc mừng",
              "Đặt tour thành công, cảm ơn quý khách đã lựa chọn dịch vụ của BK Travel!",
              "Trang chủ"
            ).then((res) => {
              if (res.isConfirmed) {
                dispatch(removeBookingInfo());
                navigate(ROUTE.HOME);
              }
            });
          })
          .catch((err) => {
            dispatch(offLoading());
            if (err instanceof AxiosError) {
              const message =
                err.response?.data?.message ??
                "Không thể đặt tour, vui lòng kiểm tra lại!";
              handlePaymentError(message);
            }
          });
        break;
      default:
        break;
    }
  };

  const handlePaymentError = (message) => {
    warningAlert("Cảnh báo", message, {
      confirmButtonText: "OK",
    }).then((result) => {
      if (result.isConfirmed) {
        dispatch(removeBookingInfo());
        // navigate(ROUTE.HOME);
      }
    });
  };

  const renderTouristType = (type) => {
    switch (type) {
      case "ADULT":
        return "Người lớn";
      case "CHILDREN":
        return "Trẻ em";
      case "BABY":
        return "Em bé";
      default:
        return "";
    }
  };

  const renderTourists = useCallback(() => {
    return formik.values.touristList.map((tourist) => (
      <tr key={tourist.fullName}>
        <td>{tourist.id + 1}</td>
        <td>{tourist.fullName}</td>
        <td>{renderBirthDate(tourist.birthDate)}</td>
        <td>{tourist?.age ?? "Không xác định"}</td>
        <td>{renderGender(tourist.gender)}</td>
        <td>{renderTouristType(tourist?.type) || "Không xác định"}</td>
      </tr>
    ));
  }, [formik.values.touristList]);

  useEffect(() => {
    if (!tour || !accountId) {
      navigate("/");
    }
  }, [tour, accountId, navigate]);

  useEffect(() => {
    manageBookingAndCalculatePrice();
  }, [manageBookingAndCalculatePrice]);

  return (
    <div className="checkout-container main-session">
      <div className="checkout-area">
        <div className="checkout-area__left">
          <div className="checkout-container__title-1">Thủ tục thanh toán</div>
          <div className="checkout-container__back-to-booking">
            <button onClick={onReturnToBookingPage}>Quay lại</button>
          </div>
          <div className="representative">
            <div className="checkout-container__title-2">▶ Người đại diện</div>
            <ol className="representative__info">
              <li>
                <span>Họ tên:</span>
                <span>{representative?.fullName || "Không xác định"}</span>
              </li>
              <li>
                <span>Địa chỉ:</span>
                <span>{representative?.address || "Không xác định"}</span>
              </li>
              <li>
                <span>Số điện thoại:</span>
                <span>{representative?.phone || "Không xác định"}</span>
              </li>
              <li>
                <span>Email:</span>
                <span>{representative?.email || "Không xác định"}</span>
              </li>
            </ol>
          </div>
          <div className="tourist-list">
            <div className="checkout-container__title-2">
              ▶ Danh sách hành khách
            </div>
            <table className="tourist-list__info">
              <thead>
                <tr>
                  <th>STT</th>
                  <th>Họ Tên</th>
                  <th>Ngày Sinh</th>
                  <th>Tuổi</th>
                  <th>Giới Tính</th>
                  <th>Loại</th>
                </tr>
              </thead>
              <tbody>{renderTourists()}</tbody>
              <tfoot>
                <tr>
                  <td colSpan={6}>
                    Tổng số hành khách: {touristList.length} (
                    <span>{formik.values.adultNumber} người lớn</span>
                    <span>, {formik.values.childrenNumber} trẻ em</span>
                    <span>, {formik.values.babyNumber} em bé</span>)
                  </td>
                </tr>
              </tfoot>
            </table>
          </div>
          <div className="note">
            <p className="note__item">(*) Lưu ý:</p>
            <p className="note__item">
              Quý khách vui lòng kiểm tra lại toàn bộ thông tin bên trên trước
              khi thực hiện thanh toán. Nếu có thông tin sai sót, vui lòng bấm "
              <strong>quay lại</strong>" để cập nhật thông tin!
            </p>
          </div>
          <div className="total-price">
            <div className="total-price__title">Tổng chi phí:</div>
            <div className="total-price__number">
              {Number(formik.values.amount).toLocaleString("vi-VN")} VND
            </div>
          </div>
        </div>
        <div className="checkout-area__right">
          <div className="selected-tour">
            <div className="selected-tour__item title">
              <div className="selected-tour__image">
                <img src={tour?.image1} alt="" />
              </div>
              <div className="selected-tour__title">
                <span>{tour?.title || "Không xác định"}</span>
              </div>
            </div>
            <div className="selected-tour__item">
              <span>▶ Mã Tour:</span>
              <span>{tour?.tourCode || "Không xác định"}</span>
            </div>
            <div className="selected-tour__item">
              <span>▶ Thời gian khởi hành:</span>
              <span>
                {new Date(tour?.departureTime).toLocaleString("vi-VN", {
                  dateStyle: "short",
                  timeStyle: "short",
                })}
              </span>
            </div>
            <div className="selected-tour__item">
              <span>▶ Địa điểm xuất phát:</span>
              <span>{tour?.departureLocation || "Không xác định"}</span>
            </div>
            <div className="selected-tour__item">
              <span>▶ Tổng thời gian:</span>
              <span>{tour?.duration || "Không xác định"}</span>
            </div>
            <div className="selected-tour__item prices">
              <div>▶ Chi phí:</div>
              <ul>
                <li>
                  <span>Người lớn:</span>
                  <span>{renderPrice(tour?.adultPrice)}</span>
                </li>
                <li>
                  <span>Trẻ em:</span>
                  <span>{renderPrice(tour?.childrenPrice)}</span>
                </li>
                <li>
                  <span>Em bé:</span>
                  <span>{renderPrice(tour?.babyPrice)}</span>
                </li>
              </ul>
            </div>
          </div>
          <div className="buttons-area">
            <div className="buttons-area--submit">
              <button
                className="vnpay"
                onClick={() => {
                  handleSubmitBooking("VNPAY");
                }}
              >
                <img src={vnpayLogo} alt="" />
              </button>
              <button
                className="cash-payment"
                onClick={() => {
                  handleSubmitBooking("PAY_LATER");
                }}
              >
                Thanh toán sau
              </button>
            </div>
            <div className="buttons-area--cancel">
              <button onClick={handleCancelBooking}>Hủy bỏ đặt tour</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CheckoutContainer;
