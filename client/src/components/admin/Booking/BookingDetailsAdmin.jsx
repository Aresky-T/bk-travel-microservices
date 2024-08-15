import { Box, Button, FormControl, MenuItem, TextField } from "@mui/material";
import React, { useCallback } from "react";
import { useDispatch } from "react-redux";
import { onUpdateAdminSelectedBookingId } from "../../../redux/slices/admin.slice";

const BookingDetailsAdmin = ({
  bookingForm,
  onShowCancellationRequest,
  onSubmitUpdateBookingStatus,
  onResetBookingStatus,
}) => {
  const dispatch = useDispatch();

  const renderFormOfPayment = useCallback(() => {
    const paymentStatus = bookingForm.values.status;
    if (paymentStatus !== "PAY_UP") return;

    return (
      <FormControl fullWidth margin="normal">
        <TextField
          type="text"
          name="formOfPayment"
          label="Hình thức thanh toán"
          select
          helperText="(*) Có thể thay đổi hình thức thanh toán"
          value={bookingForm.values.formOfPayment ?? ""}
          onChange={bookingForm.handleChange}
        >
          <MenuItem value="BANK_TRANSFER">Chuyển khoản</MenuItem>
          <MenuItem value="CASH_PAYMENT">Thanh toán tiền mặt</MenuItem>
          <MenuItem value="VNPAY_ON_WEBSITE">
            Thanh toán qua VNPAY tại trang web
          </MenuItem>
        </TextField>
      </FormControl>
    );

    // eslint-disable-next-line
  }, [bookingForm.values.status, bookingForm.values.formOfPayment]);

  const renderTotalPersons = useCallback(() => {
    const count = bookingForm.values.totalPersons;
    return count > 0 ? `(Tổng: ${count})` : "";
  }, [bookingForm.values.totalPersons]);

  const renderGender = (gender) => {
    let value = "";
    switch (gender) {
      case "MALE":
        value = "Nam";
        break;
      case "FEMALE":
        value = "Nữ";
        break;
      case "OTHER":
        value = "Khác";
        break;
      default:
        value = "Không xác định";
    }

    return (
      <TextField
        type="text"
        fullWidth
        margin="normal"
        name="fullName"
        label="Giới tính của hành khách"
        helperText="(*) Không thể thay đổi"
        value={value}
        InputProps={{
          readOnly: true,
        }}
      />
    );
  };

  return (
    <div className="administrator-manager booking-detail-admin-wrapper scroll-container">
      <div className="booking-detail-admin">
        <h1>Chi tiết tour đã đặt</h1>
        <Button
          className="admin-btn--back"
          onClick={() => {
            dispatch(onUpdateAdminSelectedBookingId(null));
          }}
        >
          Quay lại
        </Button>
        <form className="admin-main-form" onReset={bookingForm.handleReset}>
          <TextField
            type="text"
            // fullWidth
            margin="normal"
            name="bookingCode"
            label="Mã Booking"
            helperText="(*) Không thể thay đổi"
            value={bookingForm.values.bookingCode}
            InputProps={{
              readOnly: true,
            }}
          />
          <Box
            sx={{
              marginTop: "20px",
              borderTop: "2px dashed #ccc",
            }}
          >
            <h3>Thông tin tour và tổng chi phí</h3>
            <Box
              sx={{
                display: "grid",
                gridTemplateColumns: "repeat(3, 1fr)",
                columnGap: "20px",
              }}
            >
              <TextField
                type="text"
                fullWidth
                margin="normal"
                name="tourCode"
                label="Mã tour khách hàng đã đặt"
                helperText="(*) Không thể thay đổi"
                value={bookingForm.values.tourCode}
                InputProps={{
                  readOnly: true,
                }}
              />
              <TextField
                type="text"
                fullWidth
                margin="normal"
                name="totalPrice"
                label="Tổng tiền khách hàng phải thanh toán (đơn giá: VND)"
                helperText="(*) Không thể thay đổi"
                value={bookingForm.values.amount.toLocaleString("vi-VN")}
                InputProps={{
                  readOnly: true,
                }}
              />
            </Box>
          </Box>
          <Box
            sx={{
              marginTop: "20px",
              borderTop: "2px dashed #ccc",
            }}
          >
            <h3>Thông tin người đặt tour</h3>
            <Box
              sx={{
                display: "grid",
                gridTemplateColumns: "repeat(3, 1fr)",
                columnGap: "20px",
              }}
            >
              <TextField
                type="text"
                fullWidth
                margin="normal"
                name="fullName"
                label="Họ tên khách hàng"
                helperText="(*) Không thể thay đổi"
                value={bookingForm.values.fullName}
                InputProps={{
                  readOnly: true,
                }}
              />
              <TextField
                type="text"
                fullWidth
                margin="normal"
                name="email"
                label="Địa chỉ email liên hệ"
                helperText="(*) Không thể thay đổi"
                value={bookingForm.values.email}
                InputProps={{
                  readOnly: true,
                }}
              />
              <TextField
                type="text"
                fullWidth
                margin="normal"
                name="phone"
                helperText="(*) Không thể thay đổi"
                label="Số điện thoại liên hệ"
                value={bookingForm.values.phone}
                InputProps={{
                  readOnly: true,
                }}
              />
            </Box>
            <TextField
              type="text"
              fullWidth
              margin="normal"
              name="address"
              label="Địa chỉ"
              helperText="(*) Không thể thay đổi"
              value={bookingForm.values.address}
              InputProps={{
                readOnly: true,
              }}
            />
            <TextField
              type="text"
              fullWidth
              margin="normal"
              name="fullName"
              label="Nhắn nhủ tới từ khách hàng"
              helperText="(*) Không thể thay đổi"
              multiline
              value={bookingForm.values.note}
              InputProps={{
                readOnly: true,
              }}
            />
          </Box>
          <Box
            sx={{
              marginTop: "20px",
              borderTop: "2px dashed #ccc",
            }}
          >
            <h3>Danh sách hành khách {renderTotalPersons()}</h3>
            <Box
              sx={{
                display: "grid",
                gridTemplateColumns: "repeat(3, 1fr)",
                columnGap: "20px",
              }}
            >
              <TextField
                type="number"
                fullWidth
                margin="normal"
                name="adultNumber"
                label="Số người lớn"
                helperText="(*) Không thể thay đổi"
                value={bookingForm.values.adultNumber}
                InputProps={{
                  readOnly: true,
                }}
              />
              <TextField
                type="number"
                fullWidth
                margin="normal"
                name="childrenNumber"
                label="Số trẻ em"
                helperText="(*) Không thể thay đổi"
                value={bookingForm.values.childrenNumber}
                InputProps={{
                  readOnly: true,
                }}
              />
              <TextField
                type="number"
                fullWidth
                margin="normal"
                name="babyNumber"
                label="Số em bé"
                helperText="(*) Không thể thay đổi"
                value={bookingForm.values.babyNumber}
                InputProps={{
                  readOnly: true,
                }}
              />
            </Box>
            {bookingForm.values.touristList.map((tourist) => (
              <Box
                sx={{
                  display: "grid",
                  gridTemplateColumns: "repeat(3, 1fr)",
                  columnGap: "20px",
                }}
                key={tourist.fullName}
              >
                <TextField
                  type="text"
                  fullWidth
                  margin="normal"
                  name="fullName"
                  label="Họ tên hành khách"
                  helperText="(*) Không thể thay đổi"
                  value={tourist.fullName}
                  InputProps={{
                    readOnly: true,
                  }}
                />
                {renderGender(tourist.gender)}
                <TextField
                  type="text"
                  fullWidth
                  margin="normal"
                  name="birthDate"
                  label="Ngày sinh của hành khách"
                  helperText="(*) Không thể thay đổi"
                  value={new Date(tourist.birthDate).toLocaleDateString(
                    "vi-VN",
                    {
                      dateStyle: "medium",
                    }
                  )}
                  InputProps={{
                    readOnly: true,
                  }}
                />
              </Box>
            ))}
          </Box>
          <Box
            sx={{
              marginTop: "20px",
              borderTop: "2px dashed #ccc",
            }}
          >
            <h3>Trạng thái thanh toán</h3>
            <Box
              sx={{
                display: "grid",
                gridTemplateColumns: "repeat(3, 1fr)",
                columnGap: "20px",
              }}
            >
              <FormControl fullWidth margin="normal">
                {/* <InputLabel id="booked-tour-admin-form__status-id">
                Trạng thái đặt tour
              </InputLabel> */}
                {/* <Select
                name="status"
                labelId="booked-tour-admin-form__status-id"
                label="Trạng thái đặt tour"
                value={bookingForm.values.status || ""}
                onChange={bookingForm.handleChange}
              >
                <MenuItem value="NOT_PAY">Chưa thanh toán</MenuItem>
                <MenuItem value="PAY_UP">Đã thanh toán</MenuItem>
                <MenuItem value="REJECTED">Từ chối</MenuItem>
              </Select> */}
                <TextField
                  name="status"
                  label="Trạng thái thanh toán"
                  select
                  required
                  helperText="(*) Có thể thay đổi trạng thái thanh toán"
                  value={bookingForm.values.status || ""}
                  onChange={bookingForm.handleChange}
                >
                  <MenuItem value="NOT_PAY">Chưa thanh toán</MenuItem>
                  <MenuItem value="PAY_UP">Đã thanh toán</MenuItem>
                  <MenuItem value="REJECTED">Từ chối</MenuItem>
                </TextField>
              </FormControl>
              {renderFormOfPayment()}
            </Box>
            <Button
              className="admin-btn admin-btn--submit"
              type="button"
              onClick={onSubmitUpdateBookingStatus}
            >
              Cập nhật trạng thái thanh toán
            </Button>
            <Button
              className="admin-btn admin-btn--red"
              type="button"
              onClick={onResetBookingStatus}
            >
              Khôi phục lại trạng thái
            </Button>
          </Box>
          <Box
            sx={{
              marginTop: "20px",
              borderTop: "2px dashed #ccc",
            }}
          >
            <h3>Yêu cầu huỷ chuyến đi</h3>
            <div>
              <p>
                <i>
                  {bookingForm.values.isCancellationRequested
                    ? "Đang có một yêu cầu huỷ chuyến đi!"
                    : "Không có yêu cầu huỷ chuyến đi!"}
                </i>
              </p>
            </div>
            <Button
              className="admin-btn admin-btn--standard"
              type="button"
              onClick={onShowCancellationRequest}
            >
              Kiếm tra yêu cầu huỷ chuyến đi
            </Button>
          </Box>
        </form>
      </div>
    </div>
  );
};

export default BookingDetailsAdmin;
