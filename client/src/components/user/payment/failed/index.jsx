import React from "react";
import { CgCloseO } from "react-icons/cg";

const FailedPayment = () => {
  return (
    <div className="payment payment--failed">
      <div className="payment_icons">
        <CgCloseO />
      </div>
      <div className="payment_title">Thanh toán thất bại</div>
      <div className="payment_message">
        Quý khách đã không thanh toán thành công cho chuyến đi này. Cảm ơn quý
        khách đã sử dụng dịch vụ của chúng tôi!
      </div>
    </div>
  );
};

export default FailedPayment;
