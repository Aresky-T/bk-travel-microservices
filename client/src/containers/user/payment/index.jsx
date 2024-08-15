import React, { useCallback, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  checkExistVnpayTransactionInfoApi,
  handleVnpayReturnApi,
} from "../../../api/payment";
import SuccessPayment from "../../../components/user/payment/success";
import FailedPayment from "../../../components/user/payment/failed";
import CancelledPayment from "../../../components/user/payment/cancelled";
import { useDispatch } from "react-redux";
import { offLoading, onLoading } from "../../../redux/slices/loading.slice";
import { useLoading } from "../../../redux/selector";

const PaymentContainer = () => {
  const [params, setParams] = useState({});
  const [bookingId, setBookingId] = useState();
  const [paymentStatus, setPaymentStatus] = useState();

  const isLoading = useLoading();

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();

  const handleUpdateParams = useCallback(() => {
    const search = new URLSearchParams(location.search);
    const bookingId = search.get("bookingId");

    if (!bookingId) {
      navigate("/");
      return;
    }

    setBookingId(Number(bookingId));

    const obj = {};
    for (const param of search.entries()) {
      const key = param[0];
      const value = param[1];
      obj[key] = value;
    }

    setParams(obj);
    // eslint-disable-next-line
  }, [location]);

  const renderPaymentInfo = useCallback(() => {
    if (!paymentStatus) return;

    switch (paymentStatus) {
      case "EXIST":
        return (
          <div className="payment payment--exist">
            <div className="payment_title">Giao dịch đã tồn tại!</div>
          </div>
        );
      case "NOT_EXIST":
        return (
          <div className="payment payment--not-exist">
            <div className="payment_title">Giao dịch không tồn tại!</div>
          </div>
        );
      case "SUCCESS":
        return <SuccessPayment />;
      case "FAILED":
        return <FailedPayment />;
      case "CANCELLED":
        return <CancelledPayment />;
      default:
        return "";
    }
  }, [paymentStatus]);

  const handleVnpayReturn = (transactionInfo) => {
    dispatch(onLoading());
    handleVnpayReturnApi(transactionInfo)
      .then((res) => res.data)
      .then((status) => {
        setPaymentStatus(status);
      })
      .catch((err) => {
        setPaymentStatus("NOT_EXIST");
      });
  };

  const checkExistVnpayTransactionInfo = useCallback(() => {
    if (!bookingId) {
      return;
    }

    checkExistVnpayTransactionInfoApi(bookingId)
      .then((res) => {
        if (res.data) {
          setPaymentStatus("EXIST");
          return;
        }

        const transactionInfo = {
          bookingId: bookingId,
          bank: params["vnp_BankCode"],
          cardType: params["vnp_CardType"],
          orderInfo: params["vnp_OrderInfo"],
          payDate: params["vnp_PayDate"],
          transactionNo: params["vnp_TransactionNo"],
          responseCode: params["vnp_ResponseCode"],
          txnRef: params["vnp_TxnRef"],
          amount: params["vnp_Amount"],
        };

        handleVnpayReturn(transactionInfo);
      })
      .catch((err) => {});

    //eslint-disable-next-line
  }, [bookingId]);

  useEffect(() => {
    if (isLoading) {
      setTimeout(() => dispatch(offLoading()), 2000);
    }

    // eslint-disable-next-line
  }, [isLoading]);

  useEffect(() => {
    checkExistVnpayTransactionInfo();
  }, [checkExistVnpayTransactionInfo]);

  useEffect(() => {
    handleUpdateParams();
  }, [handleUpdateParams]);

  return (
    <div className="main-session payment-container">
      {!isLoading && renderPaymentInfo()}
    </div>
  );
};

export default PaymentContainer;
