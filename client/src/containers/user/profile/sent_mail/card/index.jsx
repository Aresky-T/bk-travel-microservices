import React, { useCallback, useEffect, useState } from "react";
import MailCard from "../../../../../components/user/profile/sent_mail/card";
import { getMailDetailsApi } from "../../../../../api/mail/mail.api";
import { useAuth } from "../../../../../redux/selector";

const MailCardContainer = ({ mail }) => {
  const [isShowDetails, setIsShowDetails] = useState(false);
  const [mailDetails, setMailDetails] = useState({});

  const { email } = useAuth();

  const handleShowDetails = () => setIsShowDetails(true);
  const handleHiddenDetails = () => {
    setMailDetails({});
    setIsShowDetails(false);
  };

  const getMailDetails = useCallback(() => {
    if (!isShowDetails || !mail || !email) return;
    getMailDetailsApi(email, mail.id)
      .then((res) => {
        setMailDetails((prev) => ({
          ...prev,
          ...res.data,
        }));
      })
      .catch((err) => {});
  }, [isShowDetails, mail, email]);

  useEffect(() => {
    getMailDetails();
  }, [getMailDetails]);

  return (
    <MailCard
      mail={mail}
      mailDetails={mailDetails}
      isShowDetails={isShowDetails}
      onShowDetails={handleShowDetails}
      onHiddenDetails={handleHiddenDetails}
    />
  );
};

export default MailCardContainer;
