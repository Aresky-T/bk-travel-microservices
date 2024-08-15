import React, { useCallback, useEffect, useState } from "react";
import CustomerInfo from "../../../components/global/BookingVer2/CustomerInfo";
import { useAuth, useBooking } from "../../../redux/selector";
import { useNavigate, useParams } from "react-router-dom";
import { useDispatch } from "react-redux";
import { getDetailsSubTourByTourCodeApi } from "../../../api/global/tours.api";
import { checkTourIsBookedByUser } from "../../../api/user/booking.api";
import { warningAlertNoCancel } from "../../../config/sweetAlertConfig";
import { ROUTE } from "../../../constant/route";
import TourInfo from "../../../components/global/BookingVer2/TourInfo";
import ValidateUtils from "../../../utils/validate";
import { CUSTOM_REGEX } from "../../../constant/regex";
import { addBookingInfo } from "../../../redux/slices/booking.slice";
import { toast } from "react-hot-toast";
import { differenceInYears } from "date-fns";

const initRepresentative = {
  fullName: "",
  email: "",
  phone: "",
  address: "",
};

const BookingContainer = () => {
  const [tour, setTour] = useState();
  const param = useParams();
  const auth = useAuth();
  const accountId = auth.id;
  const { bookingInfo } = useBooking();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [representative, setRepresentative] = useState(initRepresentative);
  const [note, setNote] = useState("");
  const [touristList, setTouristList] = useState([]);
  const [errors, setErrors] = useState(new Map());

  function handleChangeRepresentative(event) {
    if (event && event.target) {
      const { name, value } = event.target;
      setRepresentative((prevRepresentative) => ({
        ...prevRepresentative,
        [name]: value,
      }));
    }
  }

  function handleChangeNote(event) {
    if (event && event.target) {
      setNote(event.target.value);
    }
  }

  function handleValidateRepresentative() {
    return new Promise((resolve, reject) => {
      const { isValid, errors } = ValidateUtils({
        formData: representative,
        messages: {
          fullName_required: "Họ tên không được để trống!",
          email_required: "Email không được để trống!",
          email_regex: "Email không đúng định dạng!",
          phone_required: "Số điện thoại không được để trống!",
          address_required: "Địa chỉ không được để trống!",
        },
        rules: {
          fullName: {
            required: true,
          },
          email: {
            required: true,
            regex: CUSTOM_REGEX.EMAIL,
          },
          phone: {
            required: true,
          },
          address: {
            required: true,
          },
        },
      });
      if (isValid) {
        resolve({ isValid });
      } else {
        reject(errors);
      }
    });
  }

  function validateTourist(tourist) {
    const { isValid, errors } = ValidateUtils({
      formData: tourist,
      rules: {
        fullName: {
          required: true,
        },
        birthDate: {
          required: true,
        },
        gender: {
          required: true,
        },
      },
      messages: {
        fullName_required: "Họ tên không được để trống!",
        birthDate_required: "Email không được để trống!",
        gender_required: "Số điện thoại không được để trống!",
      },
    });
    return { isValid, errors };
  }

  function handleValidateTouristList() {
    function calculateAgeOfTourist(tourist) {
      const birthDate = new Date(tourist.birthDate);
      const currentDate = new Date();
      const age = differenceInYears(currentDate, birthDate);
      return age;
    }

    return new Promise((resolve, reject) => {
      const newList = touristList.map((tourist) => {
        const age = calculateAgeOfTourist(tourist);
        if (age >= 12) {
          return { ...tourist, type: "ADULT", age };
        }

        if (age >= 2 && age <= 11) {
          return { ...tourist, type: "CHILDREN", age };
        }

        return { ...tourist, type: "BABY", age };
      });

      if (newList.length === 0) {
        reject(new Error("Danh sách hành khách không được trống!"));
      }

      const adultNumber = newList.filter(
        (tourist) => tourist.type && tourist.type === "ADULT"
      ).length;

      if (adultNumber === 0) {
        reject(new Error("Phải có ít nhất một hành khách là người lớn!"));
      }

      let isValid = true;
      const errors = [];

      newList.forEach((tourist) => {
        const validate = validateTourist(tourist);
        if (!validate.isValid) {
          errors.push({ id: tourist.id, error: validate.errors });
        }
      });

      if (errors.length > 0) {
        isValid = false;
      }

      resolve({ touristList: newList, isValid, errors });
    });
  }

  function handleNavigateToCheckout() {
    return new Promise((resolve, reject) => {
      const loading = toast.loading("Đang xác nhận thông tin đặt tour...");
      setTimeout(() => {
        if (accountId) {
          toast.dismiss(loading);
          resolve({ message: "Xác nhận thông tin thành công!" });
        } else {
          toast.dismiss(loading);
          reject(new Error("Vui lòng đăng nhập để tiếp tục!"));
        }
      }, 500);
    });
  }

  function handleConfirmBookingInfo() {
    handleValidateRepresentative()
      .then((res) => {
        setErrors(new Map());
        handleValidateTouristList()
          .then((res) => {
            if (!res.isValid) {
              toast.error("Thông tin hành khách chưa hợp lệ!");
            } else {
              dispatch(
                addBookingInfo({
                  representative,
                  selectedTour: tour,
                  touristList: res.touristList,
                  note,
                })
              );

              handleNavigateToCheckout()
                .then((res) => {
                  toast.success(res.message, { duration: 500 });
                  setTimeout(() => {
                    const loading = toast.loading(
                      "Đang chuyển đến thanh toán..."
                    );
                    setTimeout(() => {
                      toast.dismiss(loading);
                      navigate(ROUTE.CHECKOUT);
                    }, 1000);
                  }, 500);
                })
                .catch((err) => {
                  const errorToast = toast.error(err.message);
                  setTimeout(() => {
                    toast.dismiss(errorToast);
                    navigate(ROUTE.LOGIN, {
                      state: { prevPath: ROUTE.CHECKOUT },
                    });
                  }, 1000);
                });
            }
          })
          .catch((err) => {
            toast(err.message, {
              icon: "⚠️",
            });
          });
      })
      .catch((error) => {
        setErrors(error);
      });
  }

  const handleCheckTourIsBookedByUser = useCallback(() => {
    if (tour && tour.id && accountId) {
      checkTourIsBookedByUser(accountId, tour.id)
        .then((res) => {
          const bool = res.data;
          if (bool) {
            warningAlertNoCancel(
              "Cảnh báo",
              "Quý khách đã đặt tour này rồi, không thể đặt lại",
              "Trang chủ"
            ).then((res) => {
              navigate(ROUTE.HOME);
            });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, [tour, accountId, navigate]);

  useEffect(() => {
    handleCheckTourIsBookedByUser();
  }, [handleCheckTourIsBookedByUser]);

  useEffect(() => {
    const tourCode = param.tourCode;
    getDetailsSubTourByTourCodeApi(tourCode)
      .then((res) => {
        setTour(res.data);
      })
      .catch((err) => {
        navigate(ROUTE.HOME);
      });
    //eslint-disable-next-line
  }, [param]);

  useEffect(() => {
    const selectedTour = bookingInfo.selectedTour;
    if (tour && selectedTour && tour.tourCode === selectedTour.tourCode) {
      setRepresentative(bookingInfo.representative);
      setTouristList(bookingInfo.touristList);
      setNote(bookingInfo.note);
    }
  }, [bookingInfo, tour, dispatch]);

  return (
    <div className="main-session booking-container">
      <header className="booking-container__header">
        <h2>Đặt Tour</h2>
      </header>
      <section className="customer-info">
        <CustomerInfo
          currentTour={tour}
          representative={representative}
          touristList={touristList}
          note={note}
          handleChangeNote={handleChangeNote}
          handleChangeRepresentative={handleChangeRepresentative}
          setTouristList={setTouristList}
          errors={errors}
        />
      </section>
      <section className="tour-info">
        {tour && (
          <TourInfo
            tour={tour}
            handleConfirmBookingInfo={handleConfirmBookingInfo}
          />
        )}
      </section>
    </div>
  );
};

export default BookingContainer;
