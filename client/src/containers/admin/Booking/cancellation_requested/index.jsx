import React, { useEffect, useMemo, useState } from "react";
import AdminFormWrapper from "../../../../components/admin/Form";
import { Box, Button, MenuItem, TextField } from "@mui/material";
import { questionAlert } from "../../../../config/sweetAlertConfig";
import { toast } from "react-toastify";
import {
  approveCancellationRequestedApi,
  rejectCancellationRequestedApi,
} from "../../../../api/admin/booking.api";

const OPTIONS = {
  PENDING: "PENDING",
  APPROVED: "APPROVED",
  REJECTED: "REJECTED",
};

const CancellationRequestedContainer = ({
  cancellationRequest,
  booking,
  onClose,
  handleRefetchRequest,
}) => {
  const [option, setOption] = useState();

  const isShowSubmitButton = useMemo(
    () => cancellationRequest && cancellationRequest.status === OPTIONS.PENDING,
    [cancellationRequest]
  );

  const onCenterLoading = () =>
    toast.loading("Đang xử lý yêu cầu", { position: "top-center" });

  const onCenterSuccessAlert = (message) => {
    toast.success(message, { position: "top-center", autoClose: 2000 });
  };

  const onCenterErrorAlert = (message) => {
    toast.error(message, {
      position: "top-center",
      autoClose: 3000,
    });
  };

  const handleApproveCancellationRequest = (bookingId) => {
    questionAlert(
      "Cảnh báo",
      "Bạn có chắc chắn muốn chấp nhận yêu cầu huỷ chuyến đi này không, nếu có hãy bấm nút xác nhận?",
      "Xác nhận",
      "Quay lại"
    ).then((btn) => {
      if (btn.isConfirmed) {
        const loading = onCenterLoading();
        approveCancellationRequestedApi(bookingId)
          .then((res) => {
            setTimeout(() => {
              handleRefetchRequest();
              onCenterSuccessAlert("Xử lý yêu cầu thành công");
              toast.dismiss(loading);
            }, 1000);
          })
          .catch((err) => {
            setTimeout(() => {
              onCenterErrorAlert(
                err?.response?.data?.message ?? "Xử lý yêu cầu thất bại"
              );
              toast.dismiss(loading);
            }, 500);
          });
      }
    });
  };

  const handleRejectCancellationRequest = (bookingId) => {
    questionAlert(
      "Cảnh báo",
      "Bạn có chắc chắn muốn từ chối yêu cầu huỷ chuyến đi này không, nếu có hãy bấm nút xác nhận?",
      "Xác nhận",
      "Quay lại"
    ).then((btn) => {
      if (btn.isConfirmed) {
        const loading = onCenterLoading();
        rejectCancellationRequestedApi(bookingId)
          .then((res) => {
            setTimeout(() => {
              handleRefetchRequest();
              onCenterSuccessAlert("Xử lý yêu cầu thành công");
              toast.dismiss(loading);
            }, 1000);
          })
          .catch((err) => {
            setTimeout(() => {
              onCenterErrorAlert("Xử lý yêu cầu thất bại");
              toast.dismiss(loading);
            }, 500);
          });
      }
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (!booking || !option) return;

    if (option === cancellationRequest.status) {
      toast.warning("Hãy thay đổi trạng thái của yêu cầu nếu muốn tiếp tục", {
        position: "top-center",
        autoClose: 4000,
      });
      return;
    }

    switch (option) {
      case OPTIONS.PENDING:
        toast.warning("Không thể chọn trạng thái chưa giải quyết", {
          position: "top-center",
          autoClose: 4000,
        });
        break;
      case OPTIONS.APPROVED:
        handleApproveCancellationRequest(booking.id);
        break;
      case OPTIONS.REJECTED:
        handleRejectCancellationRequest(booking.id);
        break;
      default:
    }
  };

  useEffect(() => {
    if (cancellationRequest) {
      setOption(cancellationRequest.status);
    }
  }, [cancellationRequest]);

  return (
    <AdminFormWrapper
      active={cancellationRequest}
      position={"center"}
      title={"Yêu cầu huỷ chuyến đi"}
    >
      {cancellationRequest ? (
        <form onSubmit={handleSubmit}>
          <TextField
            type="text"
            name="createdTime"
            label="Ngày khách hàng gửi yêu cầu"
            required
            fullWidth
            margin="normal"
            helperText="(*) Không thể sửa đổi ngày gửi yêu cầu!"
            value={new Date(cancellationRequest.createdTime).toLocaleString(
              "vi-VN"
            )}
            InputProps={{
              readOnly: true,
            }}
          />
          <TextField
            type="text"
            name="reason"
            label="Lý do khách hàng muốn huỷ chuyến đi"
            required
            fullWidth
            margin="normal"
            multiline
            minRows={5}
            maxRows={10}
            placeholder="Lý do huỷ chuyến đi"
            helperText="(*) Không thể sửa đổi lý do của khách!"
            value={cancellationRequest.reason}
            InputProps={{
              readOnly: true,
            }}
          />
          <TextField
            type="text"
            name="status"
            label="Trạng thái xử lý của yêu cầu"
            required
            fullWidth
            margin="normal"
            placeholder="Lý do huỷ chuyến đi"
            helperText="(*) Có thể thay đổi trạng thái của yêu cầu!"
            select
            value={option || "PENDING"}
            onChange={(event) => {
              setOption(event.target.value);
            }}
          >
            <MenuItem value={OPTIONS.PENDING}>Chưa giải quyết</MenuItem>
            <MenuItem value={OPTIONS.APPROVED}>Đã chấp nhận yêu cầu</MenuItem>
            <MenuItem value={OPTIONS.REJECTED}>Đã từ chối yêu cầu</MenuItem>
          </TextField>
          <Box>
            {isShowSubmitButton && (
              <Button type="submit" className="admin-btn admin-btn--submit">
                Xác nhận
              </Button>
            )}
            <Button
              type="button"
              className="admin-btn admin-btn--red"
              onClick={onClose}
            >
              Thoát
            </Button>
          </Box>
        </form>
      ) : (
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            rowGap: "25px",
            fontSize: "1rem",
          }}
        >
          <div>Hiện không có yêu cầu nào!</div>
          <Button
            type="button"
            className="admin-btn admin-btn--back"
            onClick={onClose}
          >
            Thoát
          </Button>
        </Box>
      )}
    </AdminFormWrapper>
  );
};

export default CancellationRequestedContainer;
