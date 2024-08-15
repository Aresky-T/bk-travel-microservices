import React from "react";
import BookingContainer from "../../containers/global/Booking/BookingContainer2";
import HelmetTitle from "../../components/helmet/HelmetTitle";

const BookingPage = () => {
  return (
    <>
      <HelmetTitle title={"BK Travel - Đặt Tour"} metaName={"meta-booking"} />
      <BookingContainer />
    </>
  );
};

export default BookingPage;
