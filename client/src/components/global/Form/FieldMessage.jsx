import React from "react";
import { PiSealWarningBold } from "react-icons/pi";
import { TbDiscountCheck } from "react-icons/tb";

const FieldMessage = ({ fieldName, isValid, validMessage, invalidMessage }) => {
  return (
    <div
      className={`global-field-check ${
        isValid ? "global-field-check--valid" : "global-field-check--invalid"
      }`}
    >
      {isValid ? (
        <>
          <span className="icon">
            <TbDiscountCheck />
          </span>
          <span className="message">
            {validMessage || "Đã xác thực thông tin!"}
          </span>
        </>
      ) : (
        <>
          <span className="icon">
            <PiSealWarningBold />
          </span>
          <span className="message">
            {invalidMessage ||
              "Thông tin chưa được xác thực hoặc không hợp lệ!"}
          </span>
        </>
      )}
    </div>
  );
};

export default FieldMessage;
