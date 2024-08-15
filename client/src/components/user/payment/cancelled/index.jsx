import React from "react";
import { MdCancelPresentation } from "react-icons/md";

const CancelledPayment = () => {
  return (
    <div className="payment payment--cancelled">
      <div className="payment_icons">
        <MdCancelPresentation />
      </div>
      <div className="payment_title">Bạn đã huỷ bỏ thanh toán</div>
      <div className="payment_message">
        Quý khách huỷ bỏ phiên thanh toán cho chuyến đi này. <br />
        Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!
      </div>
    </div>
  );
};

export default CancelledPayment;
