import React, { useCallback, useEffect, useState } from "react";
import { IoCloseOutline } from "react-icons/io5";
import { PiSealWarningFill } from "react-icons/pi";
import { OPTIONS } from "../card";
import toast from "react-hot-toast";
import {
  deleteCancellationBookingRequestApi,
  getCancellationBookingRequestApi,
  sendCancellationBookingRequestApi,
} from "../../../../../api/user/booking.api";
import { useAuth } from "../../../../../redux/selector";
import { questionAlert } from "../../../../../config/sweetAlertConfig";

const CancelBookedTourRequest = ({
  type,
  isShow,
  bookedTour,
  hiddenModal,
  onRefetchBookings,
}) => {
  const [reason, setReason] = useState("");
  const [status, setStatus] = useState("");
  const [createdTime, setCreatedTime] = useState();
  const [isExists, setIsExists] = useState(false);

  const auth = useAuth();
  const accountId = auth.id;

  const isActive = type === OPTIONS.CANCEL_BOOKED_TOUR && isShow;

  const handleChange = (event) => {
    setReason(event.target.value);
  };

  const handleHiddenModal = () => {
    setReason("");
    hiddenModal();
  };

  const renderStatus = useCallback(() => {
    let message;
    switch (status) {
      case "PENDING":
        message = "Đã gửi, đang chờ xử lý";
        break;
      case "APPROVED":
        message = "Yêu cầu đã được chấp nhận";
        break;
      case "REJECTED":
        message = "Yêu cầu bị từ chối";
        break;
      default:
    }

    return (
      <h2 className="modal-title">
        <span>#</span> Trạng thái:{" "}
        <i style={{ color: "#000", fontWeight: 600 }}>
          <i>{message ?? "Không xác định"}</i>
        </i>
      </h2>
    );
  }, [status]);

  const renderCreatedTime = useCallback(() => {
    return (
      <h2 className="modal-title">
        <span>#</span> Ngày gửi:{" "}
        <i style={{ color: "#000", fontWeight: 600 }}>
          <i>
            {new Date(createdTime).toLocaleString("vi-VN") ?? "Không xác định"}
          </i>
        </i>
      </h2>
    );
  }, [createdTime]);

  const handleSubmitCreate = () => {
    if (!accountId) {
      toast.error("Thông tin tài khoản bị thiếu!", { duration: 1000 });
      return;
    }

    if (!reason || reason.trim().length < 1) {
      toast.error("Lý do không được để trống!", { duration: 1000 });
      return;
    }

    const formData = {
      bookingId: bookedTour.id,
      reason: reason,
    };

    const loading = toast.loading("Đang xử lý");
    sendCancellationBookingRequestApi(accountId, formData)
      .then((res) => {
        setTimeout(() => {
          const data = res.data;
          // update state
          setIsExists(true);
          setStatus(data.status);
          setCreatedTime(data.createdTime);
          // push success message
          toast.dismiss(loading);
          toast.success("Gửi yêu cầu thành công!");

          onRefetchBookings();
        }, 1500);
      })
      .then(() => {})
      .catch((err) => {
        setTimeout(() => {
          toast.dismiss(loading);
          toast.error("Đánh giá không thành công, vui lòng thử lại!", {
            duration: 1000,
          });
        }, 1500);
      });
  };

  const handleSubmitDelete = () => {
    if (!bookedTour) return;

    questionAlert(
      "Cảnh báo",
      "Bạn có chắc chắn muốn gỡ bỏ yêu cầu này không?",
      "Có, hãy xoá",
      "Quay lại"
    ).then((btn) => {
      if (btn.isConfirmed) {
        const loading = toast.loading("Đang xử lý");

        deleteCancellationBookingRequestApi(bookedTour.id)
          .then((res) => {
            setTimeout(() => {
              setIsExists(false);
              setReason("");
              setStatus("");
              setCreatedTime("");
              toast.success("Đã gỡ bỏ yêu cầu");
              onRefetchBookings();
            }, 1500);
          })
          .catch(() => {})
          .finally(() => {
            setTimeout(() => {
              toast.dismiss(loading);
            }, 1500);
          });
      }
    });
  };

  useEffect(() => {
    if (isShow && bookedTour) {
      getCancellationBookingRequestApi(bookedTour.id)
        .then((res) => {
          const data = res.data;
          setIsExists(true);
          setReason(data.reason);
          setStatus(data.status);
          setCreatedTime(data.createdTime);
        })
        .catch((err) => {
          setIsExists(false);
          setStatus("");
        });
    }

    //eslint-disable-next-line
  }, [bookedTour, isShow]);

  return (
    <div
      className={`${OPTIONS.CANCEL_BOOKED_TOUR} modal-container ${
        isActive ? "active" : ""
      }`}
    >
      <div className="modal">
        <div className="modal-close">
          <button onClick={handleHiddenModal}>
            <IoCloseOutline />
          </button>
        </div>
        <header className="modal__header">
          <strong>
            <span className="icon">
              <PiSealWarningFill />
            </span>
            Yêu cầu hủy chuyến đi
          </strong>
        </header>
        <div className="modal__main scroll-container">
          <h2 className="modal-title">
            <span>#</span> Tour:{" "}
            <i style={{ color: "#000", fontWeight: 600 }}>
              <i>{bookedTour?.tourCode ?? "Không xác định"}</i>
            </i>
          </h2>
          {isExists ? (
            <>
              {renderStatus()}
              {renderCreatedTime()}
              <h2 className="modal-title">
                <span>#</span> Lý do huỷ:
              </h2>
            </>
          ) : (
            <h2 className="modal-title">
              <span>#</span> Hãy cho chúng tôi biết lý do tại sao bạn muốn hủy
              chuyến đi này?
            </h2>
          )}
          <div className="modal__body-main">
            <form>
              <div>
                <textarea
                  name="reason"
                  rows="10"
                  spellCheck={false}
                  placeholder="Vui lòng điền lý do ở đây..."
                  value={reason}
                  onChange={handleChange}
                  readOnly={isExists}
                ></textarea>
              </div>
            </form>
          </div>
        </div>
        <div className="modal__footer">
          <div className="buttons-area">
            {isExists ? (
              <>
                {status === "PENDING" && (
                  <button
                    type="button"
                    className="profile-form__btn cancel"
                    onClick={handleSubmitDelete}
                  >
                    Gỡ bỏ yêu cầu
                  </button>
                )}
                <button
                  type="button"
                  className="profile-form__btn normal"
                  onClick={hiddenModal}
                >
                  Thoát
                </button>
              </>
            ) : (
              <>
                <button
                  type="button"
                  className="profile-btn submit"
                  onClick={handleSubmitCreate}
                >
                  Gửi yêu cầu
                </button>
                <button
                  type="button"
                  className="profile-form__btn cancel"
                  onClick={handleHiddenModal}
                >
                  Thoát
                </button>
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CancelBookedTourRequest;
