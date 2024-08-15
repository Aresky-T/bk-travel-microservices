import React from "react";
import HelmetTitle from "../../components/helmet/HelmetTitle";
import NotificationContainer from "../../containers/notification";

const NotificationPage = () => {
  return (
    <>
      <HelmetTitle title={"BK Travel - Thông báo"} metaName={"meta-checkout"} />
      <NotificationContainer />
    </>
  );
};

export default NotificationPage;
