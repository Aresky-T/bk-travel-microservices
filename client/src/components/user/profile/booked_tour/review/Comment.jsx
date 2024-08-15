import React from "react";
import { GoQuestion } from "react-icons/go";

const Comment = ({ comment, handleChangeComment }) => {
  return (
    <div className="comment">
      <div className="comment__title modal-title-2">
        <span>
          <b>Cảm nhận</b> <GoQuestion />
        </span>
      </div>
      <div className="modal-note">
        <p>
          Những lời đánh giá công tâm của bạn sẽ giúp những khách hàng khác đưa
          ra quyết định đặt Tour sáng suốt. Vui lòng chỉ viết đánh giá sau khi
          chuyến đi kết thúc và chỉ viết về trải nghiệm cá nhân của bạn.
        </p>
      </div>
      <div className="comment__main">
        <div className="comment-field">
          <textarea
            name="comment"
            rows="10"
            placeholder="Nhập vào đây..."
            value={comment ?? ""}
            onChange={handleChangeComment}
          ></textarea>
        </div>
      </div>
    </div>
  );
};

export default Comment;
