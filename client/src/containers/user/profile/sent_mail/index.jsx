import React, { useCallback, useEffect, useState } from "react";
import { useAuth } from "../../../../redux/selector";
import { getAllMailsForCustomerApi } from "../../../../api/mail/mail.api";
import LoadingIndicator from "../../../../components/global/Loading/LoadingIndicator";
import MailCardContainer from "./card";

const SentMailContainer = () => {
  const [mailList, setMailList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [pagination, setPagination] = useState({
    page: 0,
    size: 5,
    totalPages: 1,
    totalElements: 0,
  });

  const [isShowMore, setIsShowMore] = useState(false);

  const { email } = useAuth();

  const startLoading = () => setIsLoading(true);

  const endLoading = () => {
    setIsLoading(false);
  };

  const handleShowMoreMail = () => {
    setPagination((prev) => ({ ...prev, page: prev.page + 1 }));
    setIsShowMore(true);
  };

  const renderShowMoreButton = useCallback(() => {
    if (isShowMore)
      return (
        <div className="show-more-btn profile-btn normal">
          Đang tải dữ liệu...
        </div>
      );

    if (mailList.length === pagination.totalElements) return;

    return (
      <button
        className="show-more-btn profile-btn normal"
        onClick={handleShowMoreMail}
      >
        Xem thêm
      </button>
    );
  }, [mailList, pagination, isShowMore]);

  const renderSentMailList = useCallback(() => {
    if (isLoading) return <LoadingIndicator />;
    if (!mailList || !mailList.length) return "Danh sách trống!";

    return (
      <>
        <div className="profile__note">
          Bạn đã gửi {[pagination.totalElements]} mail:
        </div>
        {mailList.map((mail, index) => (
          <MailCardContainer mail={mail} key={index} />
        ))}
      </>
    );
  }, [mailList, isLoading, pagination.totalElements]);

  const getAllSentMails = useCallback(() => {
    if (!email) return;
    mailList.length === 0 && startLoading();
    getAllMailsForCustomerApi(email, pagination.page, pagination.size)
      .then((res) => {
        const { content, totalPages, totalElements } = res.data;
        const newList = [...mailList];
        const mailMap = new Map(mailList.map((mail) => [mail.id, mail]));
        content.forEach((mail) => {
          if (!mailMap.has(mail.id)) {
            newList.push(mail);
          }
        });
        setTimeout(() => {
          setMailList(newList);
          setPagination((prevPagination) => ({
            ...prevPagination,
            totalPages: totalPages,
            totalElements: totalElements,
          }));
          endLoading();
          isShowMore && setIsShowMore(false);
        }, 1000);
      })
      .catch((err) => {
        endLoading();
      });
    //eslint-disable-next-line
  }, [email, pagination.page]);

  useEffect(() => {
    if (isShowMore) {
      getAllSentMails();
    }
    //eslint-disable-next-line
  }, [isShowMore]);

  useEffect(() => {
    getAllSentMails();
  }, [getAllSentMails]);

  return (
    <div className="profile-container__main_item profile--sent-mail">
      <div className="profile-item--sent-mail title">
        <div className="profile-container__main__title-1">
          Danh sách mail đã gửi
        </div>
      </div>
      <div className="profile-item--sent-mail list">
        {renderSentMailList()}
        {renderShowMoreButton()}
      </div>
    </div>
  );
};

export default SentMailContainer;
