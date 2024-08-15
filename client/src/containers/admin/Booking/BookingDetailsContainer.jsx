import React, { useCallback, useEffect, useState } from "react";
import { useAdmin } from "../../../redux/selector";
import {
  getBookingDetailsByIdApi,
  getCancellationRequestedApi,
  updateBookingStatusApi,
} from "../../../api/admin/booking.api";
import { useFormik } from "formik";
import { questionAlert, warningAlert } from "../../../config/sweetAlertConfig";
import BookingDetailsAdmin from "../../../components/admin/Booking/BookingDetailsAdmin";
import CancellationRequestedContainer from "./cancellation_requested";
import { toast } from "react-toastify";

const BookingDetailsContainer = ({ handleRefetchBookings }) => {
  const [bookingDetails, setBookingDetails] = useState();
  const [cancellationRequest, setCancellationRequest] = useState(null);
  const [isShowCancellationRequest, setIsShowCancellationRequest] =
    useState(false);

  const { bookingManager } = useAdmin();
  const { selectedBookingId } = bookingManager;

  const bookingForm = useFormik({
    initialValues: {
      id: 0,
      accountId: null,
      tourId: null,
      subTourId: null,
      tourCode: "",
      bookingCode: "",
      fullName: "",
      email: "",
      address: "",
      phone: "",
      adultNumber: 0,
      childrenNumber: 0,
      babyNumber: 0,
      totalPersons: 0,
      note: "",
      amount: 0,
      status: "",
      formOfPayment: "",
      bookedTime: "",
      touristList: [],
      isCancellationRequested: false,
    },
  });

  const handleUpdateBookingStatus = useCallback(() => {
    const { id, status, formOfPayment } = bookingForm.values;

    if (
      status === bookingDetails.status &&
      formOfPayment === bookingDetails.formOfPayment
    ) {
      toast.warning("Không có trạng thái bị thay đổi", {
        position: "top-center",
        autoClose: 2000,
      });
      return;
    }

    const formData = { status, formOfPayment };

    if (status !== "PAY_UP") {
      delete formData["formOfPayment"];
    }

    if (status === "PAY_UP" && !formOfPayment) {
      toast.warning("Hãy lựa chọn hình thức thanh toán", {
        position: "top-center",
      });
      return;
    }

    questionAlert(
      "Cảnh báo",
      "Bạn có chắc chắn muốn thay đổi trạng thái thanh toán không?",
      "Chắc chắn",
      "Quay lại"
    )
      .then((res) => {
        if (res.isConfirmed) {
          const loading = toast.loading("Đang xử lý");
          updateBookingStatusApi(id, formData)
            .then((res) => {
              setTimeout(() => {
                handleRefetchBookings();
                toast.success("Đã cập nhật trạng thái thành công", {
                  position: "top-center",
                });
                toast.dismiss(loading);
              }, 1000);
            })
            .catch((err) => {
              setTimeout(() => {
                toast.error("Cập nhật trạng thái thất bại");
                toast.dismiss(loading);
              }, 500);
            });
        }
      })
      .catch((err) => {});

    //eslint-disable-next-line
  }, [bookingForm.values]);

  const handleResetBookingStatus = () => {
    bookingForm.setFieldValue("status", bookingDetails?.status);
    bookingForm.setFieldValue("formOfPayment", bookingDetails?.formOfPayment);
  };

  const handleShowCancellationRequest = () =>
    setIsShowCancellationRequest(true);

  const handleHiddenCancellationRequest = () => {
    setIsShowCancellationRequest(false);
    setCancellationRequest(null);
  };

  const fetchCancellationRequest = useCallback(() => {
    if (!bookingDetails || !isShowCancellationRequest) return;

    getCancellationRequestedApi(bookingDetails.id)
      .then((res) => {
        const data = res.data;
        setCancellationRequest((prev) => ({
          ...prev,
          id: data.id,
          reason: data.reason,
          status: data.status,
          createdTime: data.createdTime,
        }));
      })
      .catch((err) => {
        setCancellationRequest(null);
        setIsShowCancellationRequest(false);
        warningAlert(
          "Yêu cầu huỷ chuyến đi",
          "Hiện không có yêu cầu huỷ chuyến cho khách hàng này!"
        );
      });
  }, [bookingDetails, isShowCancellationRequest]);

  useEffect(() => {
    fetchCancellationRequest();
  }, [fetchCancellationRequest]);

  useEffect(() => {
    if (bookingDetails) {
      const obj = { ...bookingForm.values };
      for (let key in bookingDetails) {
        if (Object.hasOwnProperty.call(obj, key)) {
          obj[key] = bookingDetails[key];
        }
      }
      bookingForm.setValues(obj);
    }

    //eslint-disable-next-line
  }, [bookingDetails]);

  useEffect(() => {
    if (selectedBookingId) {
      getBookingDetailsByIdApi(selectedBookingId)
        .then((res) => {
          const data = res.data;
          setBookingDetails(data);
        })
        .catch((err) => {
          console.log(err);
        });
    }
    //eslint-disable-next-line
  }, [selectedBookingId]);

  return (
    <React.Fragment>
      <BookingDetailsAdmin
        bookingForm={bookingForm}
        onShowCancellationRequest={handleShowCancellationRequest}
        onSubmitUpdateBookingStatus={handleUpdateBookingStatus}
        onResetBookingStatus={handleResetBookingStatus}
      />
      <CancellationRequestedContainer
        cancellationRequest={cancellationRequest}
        booking={bookingDetails}
        onClose={handleHiddenCancellationRequest}
        handleRefetchRequest={fetchCancellationRequest}
      />
    </React.Fragment>
  );
};

export default BookingDetailsContainer;
