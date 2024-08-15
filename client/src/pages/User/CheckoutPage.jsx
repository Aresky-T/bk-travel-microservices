import React from "react";
import HelmetTitle from "../../components/helmet/HelmetTitle";
import CheckoutContainer from "../../containers/user/checkout/CheckoutContainer";

const CheckoutPage = () => {
  return (
    <>
      <HelmetTitle
        title={"BK Travel - Thủ tục thanh toán"}
        metaName={"meta-checkout"}
      />
      <CheckoutContainer />
    </>
  );
};

export default CheckoutPage;
