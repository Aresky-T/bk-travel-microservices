import React, { useCallback, useEffect, useState } from "react";
import BookingManager from "../../../components/admin/Booking/BookingManager";
import { useAdmin } from "../../../redux/selector";
import BookingDetailsContainer from "./BookingDetailsContainer";
import { useDispatch } from "react-redux";
import { fetchAllBookingsThunk } from "../../../redux/slices/admin.slice";

// const menu = [
//   {
//     id: 1,
//     name: "Booking",
//     title: "Danh sách đặt tour",
//     element: <BookingManager/>,
//   },
//   {
//     id: 2,
//     name: "Yêu cầu huỷ booking",
//     title: "",
//     element: <DepartmentManagerContainer />,
//   },
// ];

const BookingManagerContainer = () => {
  const [pagination, setPagination] = useState({
    size: 10,
    page: 0,
    sort: "bookedTime,DESC",
  });

  const { bookingManager } = useAdmin();
  const { selectedBookingId } = bookingManager;
  const dispatch = useDispatch();

  const handleChangeCurrentPage = (page) => {
    setPagination({
      ...pagination,
      page: page,
    });
  };

  const fetchAllBookings = useCallback(() => {
    dispatch(fetchAllBookingsThunk(pagination));

    // eslint-disable-next-line
  }, [pagination]);

  useEffect(() => {
    fetchAllBookings();
    // eslint-disable-next-line
  }, [fetchAllBookings]);

  return (
    <div className="administrator-manager-container booking-manager-container">
      {selectedBookingId ? (
        <BookingDetailsContainer handleRefetchBookings={fetchAllBookings} />
      ) : (
        <BookingManager
          handleChangeCurrentPage={handleChangeCurrentPage}
          paginate={pagination}
        />
      )}
    </div>
  );
};

export default BookingManagerContainer;
