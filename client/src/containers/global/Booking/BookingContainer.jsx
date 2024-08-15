import React, { useEffect, useState } from "react";
import Booking from "../../../components/global/Booking/Booking";
import { useNavigate, useParams } from "react-router-dom";
import { useFormik } from "formik";
import { useDispatch } from "react-redux";
import { useAuth } from "../../../redux/selector";
import { ROUTE } from "../../../constant/route";
import {
  errorAlert,
  successAlert,
  warningAlertNoCancel,
  warningAlertWithCancel,
} from "../../../config/sweetAlertConfig";
import {
  bookTourForUserApi,
  checkTourIsBookedByUser,
} from "../../../api/user/booking.api";
import { offLoading } from "../../../redux/slices/loading.slice";
import { customToast } from "../../../toaster";
import { getTourByTourCodeApi } from "../../../api/global/tours.api";
import { ROLE } from "../../../constant/role";
import { validateBooking } from "../../../validation";

const initTourist = {
  fullName: "",
  birthDate: "",
  gender: "",
};

const BookingContainer = () => {
  const [tour, setTour] = useState({});
  const param = useParams();
  const { accessToken, role } = useAuth();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const bookingFormik = useFormik({
    initialValues: {
      fullName: "",
      email: "",
      phone: "",
      address: "",
      adultNumber: 1,
      childrenNumber: 0,
      babyNumber: 0,
      note: "",
      touristList: [],
      adults: [],
      children: [],
      babies: [],
      tourId: 0,
    },
    onSubmit: (values) => {
      values.touristList = [
        ...values.adults,
        ...values.children,
        ...values.babies,
      ];
      if (accessToken && role === ROLE.USER) {
        validateBooking
          .validate(values)
          .then((data) => {
            console.log(data);
            const {
              fullName,
              email,
              phone,
              address,
              adultNumber,
              childrenNumber,
              babyNumber,
              note,
              touristList,
              tourId,
            } = data;
            bookTourForUserApi(
              {
                fullName,
                email,
                phone,
                address,
                adultNumber,
                childrenNumber,
                babyNumber,
                note,
                touristList,
                tourId,
              },
              accessToken,
              dispatch
            )
              .then((res) => {
                dispatch(offLoading());
                console.log(res.data);
                successAlert(
                  "Chúc mừng",
                  "Đặt tour thành công, cảm ơn quý khách đã lựa chọn dịch vụ của BK Travel!",
                  "Trang chủ"
                ).then((res) => {
                  if (res.isConfirmed) {
                    navigate(ROUTE.HOME);
                  }
                });
              })
              .catch((err) => {
                dispatch(offLoading());
                errorAlert(
                  "Thất bại",
                  "Đặt tour không thành công, vui lòng kiểm tra lại!"
                );
                console.log(err);
              });
          })
          .catch((err) => {
            console.log(err);
            warningAlertNoCancel(
              "Cảnh báo",
              "Hãy nhập đầy đủ thông tin người đại diện và khách hàng!",
              "OK"
            );
          });
      } else {
        warningAlertWithCancel(
          "Cảnh báo đăng nhập",
          "Quý khách vui lòng đăng nhập trước khi đặt tour!",
          "Đăng nhập",
          "Để sau"
        ).then((res) => {
          if (res.isConfirmed) {
            navigate(ROUTE.LOGIN);
          }
        });
      }
    },
  });

  const handleChangeTouristNumber = (name, action) => {
    const totalSeats =
      bookingFormik.values.adultNumber +
      bookingFormik.values.childrenNumber +
      bookingFormik.values.babyNumber;

    switch (action) {
      case "increase":
        if (totalSeats >= tour.availableSeats) {
          customToast("Đã vượt quá số chỗ trống", "⚠️");
          return;
        }
        bookingFormik.setValues({
          ...bookingFormik.values,
          [name]: bookingFormik.values[name] + 1,
        });
        break;
      case "decrease":
        if (bookingFormik.values[name] < 1) {
          customToast("Số hành khách không thể nhỏ hơn 0", "⚠️");
          return;
        }

        if (totalSeats <= 1) {
          customToast("Phải có ít nhất 1 hành khách", "⚠️");
          return;
        }

        if (name === "adultNumber" && bookingFormik.values.adultNumber <= 1) {
          customToast("Phải có ít nhất 1 hành khách là người lớn", "⚠️");
          return;
        }

        bookingFormik.setValues({
          ...bookingFormik.values,
          [name]: bookingFormik.values[name] - 1,
        });
        break;
      default:
    }
  };

  useEffect(() => {
    const adultNumber = bookingFormik.values.adultNumber;
    const childrenNumber = bookingFormik.values.childrenNumber;
    const babyNumber = bookingFormik.values.babyNumber;
    const adults = [...bookingFormik.values.adults];
    const children = [...bookingFormik.values.children];
    const babies = [...bookingFormik.values.babies];

    if (typeof adultNumber === "number" && adultNumber > 0) {
      for (let i = 1; i <= adultNumber; i++) {
        if (bookingFormik.values.adults.length < i) {
          adults.push({ ...initTourist });
        }

        if (bookingFormik.values.adults.length > adultNumber) {
          adults.splice(adultNumber);
        }
      }
    }

    if (typeof childrenNumber === "number" && childrenNumber >= 0) {
      for (let i = 0; i <= childrenNumber; i++) {
        if (bookingFormik.values.children.length < i) {
          children.push({ ...initTourist });
        }

        if (bookingFormik.values.children.length > childrenNumber) {
          children.splice(childrenNumber);
        }
      }
    }

    if (typeof babyNumber === "number" && babyNumber >= 0) {
      for (let i = 0; i <= babyNumber; i++) {
        if (bookingFormik.values.babies.length < i) {
          babies.push({ ...initTourist });
        }

        if (bookingFormik.values.babies.length > babyNumber) {
          babies.splice(babyNumber);
        }
      }
    }

    bookingFormik.setValues({
      ...bookingFormik.values,
      adults: adults,
      children: children,
      babies: babies,
    });
    //eslint-disable-next-line
  }, [
    bookingFormik.values.adultNumber,
    bookingFormik.values.childrenNumber,
    bookingFormik.values.babyNumber,
  ]);

  useEffect(() => {
    const tourCode = param.tourCode;
    getTourByTourCodeApi(tourCode)
      .then((res) => {
        setTour(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [param]);

  useEffect(() => {
    if (tour && tour.id) {
      bookingFormik.setFieldValue("tourId", tour.id);
    }
    //eslint-disable-next-line
  }, [tour]);

  useEffect(() => {
    if (tour.id && accessToken) {
      checkTourIsBookedByUser(accessToken, tour.id)
        .then((res) => {
          const bool = res.data;
          if (bool) {
            warningAlertNoCancel(
              "Cảnh báo",
              "Quý khách đã đặt tour này rồi, không thể đặt lại",
              "Trang chủ"
            ).then((res) => {
              if (res.isConfirmed) {
                navigate(ROUTE.HOME);
              }
            });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    }
    //eslint-disable-next-line
  }, [tour, accessToken]);

  return (
    <Booking
      tour={tour}
      bookingFormik={bookingFormik}
      handleChangeTouristNumber={handleChangeTouristNumber}
    />
  );
};

export default BookingContainer;
