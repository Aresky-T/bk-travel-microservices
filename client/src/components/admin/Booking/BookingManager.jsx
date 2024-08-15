import React from "react";
import CustomPaginate from "../../pagination/CustomPaginate";
import { Box, TextField, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import { useDispatch } from "react-redux";
import { onUpdateAdminSelectedBookingId } from "../../../redux/slices/admin.slice";
import { useAdmin } from "../../../redux/selector";

const BookingManager = ({ paginate, handleChangeCurrentPage }) => {
  const { bookingManager } = useAdmin();
  const { bookingList, totalPages } = bookingManager;
  const dispatch = useDispatch();
  const renderStatus = (status) => {
    switch (status) {
      case "NOT_PAY":
        return "Chưa thanh toán";
      case "PAY_UP":
        return "Đã thanh toán";
      case "REJECTED":
        return "Bị từ chối";
      default:
        return "Không xác định";
    }
  };

  return (
    <Box className="administrator-manager booking-manager">
      <section className="administrator-manager_header">
        <div className="administrator-manager__title">Danh sách đặt tour</div>
        <div className="administrator-manager__menu">
          <div className="administrator-manager__menu-item tours active">
            Booking
          </div>
        </div>
      </section>
      <Box
        className="administrator-manager_main"
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          gap: "20px",
        }}
      >
        <Box
          className="booking-manager__actions"
          sx={{
            height: "80px",
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            marginTop: "15px",
          }}
        >
          <Box
            className="booking-manager__actions--left"
            display={"flex"}
            gap={"15px"}
            width={"40%"}
          >
            <TextField
              type="text"
              name="name"
              label="Tìm kiếm khách hàng"
              placeholder="nhập vào đây..."
              margin="normal"
              sx={{ minWidth: 100 }}
              variant="standard"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <SearchIcon />
                  </InputAdornment>
                ),
              }}
            />
          </Box>
        </Box>
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            width: "100%",
            height: "100%",
          }}
        >
          {bookingList.length ? (
            <Box width={"100%"}>
              <table className="admin-table bookings-table">
                <thead>
                  <tr>
                    <th>STT</th>
                    <th>Họ tên</th>
                    <th>Mã Booking</th>
                    <th>Mã tour</th>
                    <th>Sổ khách</th>
                    <th>Tổng giá (vnd)</th>
                    <th>Thời gian đặt</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                  </tr>
                </thead>
                <tbody>
                  {bookingList?.map((item, index) => (
                    <tr className="admin-table-row" key={index}>
                      <td>{index + 1}</td>
                      <td>{item.fullName}</td>
                      <td>{item.bookingCode}</td>
                      <td>{item.tourCode}</td>
                      <td>{item.totalPersons}</td>
                      <td>{Number(item.amount).toLocaleString("vi-VN")}</td>
                      <td>
                        {new Date(item.bookedTime).toLocaleString("vi-VN", {
                          dateStyle: "short",
                          timeStyle: "short",
                        })}
                      </td>
                      <td>{renderStatus(item.status)}</td>
                      <td className="admin-table-data__action">
                        <button
                          onClick={() =>
                            dispatch(onUpdateAdminSelectedBookingId(item.id))
                          }
                        >
                          Xem
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <CustomPaginate
                currentPage={paginate?.page}
                totalPages={totalPages}
                setCurrentPage={handleChangeCurrentPage}
              />
            </Box>
          ) : (
            <Box
              sx={{
                fontSize: "1.4rem",
                color: "var(--gray-color)",
              }}
            >
              Danh sách trống!
            </Box>
          )}
        </Box>
      </Box>
    </Box>
  );
};

export default BookingManager;
