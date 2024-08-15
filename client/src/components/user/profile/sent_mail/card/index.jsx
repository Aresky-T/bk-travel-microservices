import React from "react";

const initStatus = {
  NEW: "Đã gửi thành công",
  READ: "Nhân viên đã xem thư của bạn",
  REPLIED: "Thư của bạn đã được phản hồi",
};

const MailCard = ({
  mail,
  mailDetails,
  isShowDetails,
  onShowDetails,
  onHiddenDetails,
}) => {
  const renderMailStatus = (status) => {
    return initStatus[status] || "Không xác định";
  };

  const renderDate = (date) => {
    return date.toLocaleString("vi-VN", {
      dateStyle: "short",
      timeStyle: "short",
    });
  };

  const renderRepliedMail = (repliedMail) => {
    return (
      <ul>
        <li>
          Tiêu đề: <b>{repliedMail.subject}</b>
        </li>
        <li>
          Ngày trả lời: <b>{renderDate(new Date(repliedMail.repliedAt))}</b>
        </li>
        <li>
          Nội dung:{" "}
          <div className="replied-mail--body">
            {React.createElement("div", {
              dangerouslySetInnerHTML: { __html: repliedMail.body },
            })}
          </div>
        </li>
      </ul>
    );
  };

  return (
    <div className={`sent-mail-card${isShowDetails ? " active" : ""}`}>
      <div className="sent-mail-card__main">
        <div className="sent-mail-card__item mail-subject">
          <span className="sent-mail-card__item__label">▶ Tiêu đề:</span>
          <span className="sent-mail-card__item__content">{mail.subject}</span>
        </div>
        <div className="sent-mail-card__item mail-body">
          <span className="sent-mail-card__item__label">▶ Nội dung:</span>
          <span className="sent-mail-card__item__content">{mail.body}</span>
        </div>
        <div className="sent-mail-card__item sent-at">
          <span className="sent-mail-card__item__label">▶ Ngày gửi:</span>
          <span className="sent-mail-card__item__content">
            {renderDate(new Date(mail.sentAt))}
          </span>
        </div>
        <div className="sent-mail-card__item mail-status">
          <span className="sent-mail-card__item__label">▶ Trạng thái:</span>
          <span className="sent-mail-card__item__content">
            {renderMailStatus(mail.status)}
          </span>
        </div>
        {mailDetails.staff && (
          <div className="sent-mail-card__item staff-info">
            <span className="sent-mail-card__item__label">
              ▶ Nhân viên nhận thư:
            </span>
            <span className="sent-mail-card__item__content">
              {mailDetails.staff.fullName}
            </span>
          </div>
        )}
        {mailDetails.reply && (
          <div className="sent-mail-card__item replied-mail">
            <span className="sent-mail-card__item__label">▶ Thư trả lời:</span>
            {renderRepliedMail(mailDetails.reply)}
          </div>
        )}

        <div className="sent-mail-card__buttons">
          {isShowDetails ? (
            <button className="profile-btn normal" onClick={onHiddenDetails}>
              Ẩn
            </button>
          ) : (
            <button className="profile-btn normal" onClick={onShowDetails}>
              Xem chi tiết
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default MailCard;
