import React, { useEffect, useState } from "react";
import { paymentBookedTourWithVNPayApi } from "../../../../../api/payment";
import { AxiosError } from "axios";
import { warningAlert } from "../../../../../config/sweetAlertConfig";
import { checkReviewApi } from "../../../../../api/user/review.api";
import { getDetailsBookingApi } from "../../../../../api/user/booking.api";
import BookedTourCard from "../../../../../components/user/profile/booked_tour/card";

const BookedTourCardContainer = ({ bookedTour, showModal }) => {
  const [isShowDetails, setIsShowDetails] = useState(false);
  const [isReviewed, setIsReviewed] = useState(false);
  const [detailsBooking, setDetailsBooking] = useState({});

  const handleShowDetail = () => setIsShowDetails(true);
  const handleCancelShowDetail = () => {
    setDetailsBooking({});
    setIsShowDetails(false);
  };

  const handlePayment = () => {
    if (!bookedTour || !bookedTour.id) return;

    paymentBookedTourWithVNPayApi(bookedTour.id)
      .then((res) => {
        const vnpayResponseUrl = res.data;
        window.open(vnpayResponseUrl, "_blank");
      })
      .catch((err) => {
        console.log(err);
        if (err instanceof AxiosError) {
          const response = err.response;
          const data = response.data;
          const message = data?.message ?? "Lỗi thanh toán";
          warningAlert("Cảnh báo", message, {
            cancelButtonText: "Thoát",
          });
        }
      });
  };

  useEffect(() => {
    function checkReviewedTour(booking) {
      const { accountId, subTourId } = booking;
      checkReviewApi(accountId, subTourId)
        .then((res) => {
          setIsReviewed(res.data);
        })
        .catch((err) => {});
    }

    if (bookedTour) {
      checkReviewedTour(bookedTour);
    }
  }, [bookedTour]);

  useEffect(() => {
    if (isShowDetails) {
      getDetailsBookingApi(bookedTour.id)
        .then((res) => {
          setDetailsBooking(res.data);
        })
        .catch((err) => {});
    }

    //eslint-disable-next-line
  }, [isShowDetails]);
  return (
    <BookedTourCard
      bookedTour={bookedTour}
      showModal={showModal}
      isReviewed={isReviewed}
      isShowDetails={isShowDetails}
      detailsBooking={detailsBooking}
      onClickShowDetail={handleShowDetail}
      onClickHiddenDetail={handleCancelShowDetail}
      onClickSubmitPayment={handlePayment}
    />
  );
};

export default BookedTourCardContainer;
