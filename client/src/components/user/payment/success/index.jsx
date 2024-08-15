import React from "react";
import { FaRegCircleCheck } from "react-icons/fa6";

const SuccessPayment = () => {
  return (
    <div className="payment payment--success">
      <div className="payment_icons">
        <FaRegCircleCheck />
      </div>
      <div className="payment_title">Thanh toán thành công</div>
      <div className="payment_message">
        Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi. <br />
        Để xem thông tin chuyến đi xin vui lòng truy cập hồ sơ!
      </div>
    </div>
  );
};

export default SuccessPayment;
