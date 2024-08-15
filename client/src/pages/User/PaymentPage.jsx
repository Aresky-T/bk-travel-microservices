import React from "react";
import HelmetTitle from "../../components/helmet/HelmetTitle";
import PaymentContainer from "../../containers/user/payment";

const PaymentPage = () => {
  return (
    <>
      <HelmetTitle title={"BK Travel - Thanh toán"} metaName={"meta-payment"} />
      <PaymentContainer />
    </>
  );
};

export default PaymentPage;
