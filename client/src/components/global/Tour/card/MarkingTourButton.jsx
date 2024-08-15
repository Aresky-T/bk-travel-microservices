import React, { useCallback, useEffect, useState } from "react";
import {
  MdOutlineBookmarkAdded,
  MdOutlineBookmarkBorder,
} from "react-icons/md";
import { useDispatch } from "react-redux";
import { useAuth } from "../../../../redux/selector";
import RingLoading from "../../../loading/ring";
import { fetchAllMarkedTourThunk } from "../../../../redux/slices/marking.slice";

const MarkingTourButton = ({ onClick, isMarked }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [isShowTitle, setIsShowTitle] = useState(false);
  const dispatch = useDispatch();
  const auth = useAuth();
  const accountId = auth.id;

  const onShowTitle = () => setIsShowTitle(true);
  const onHiddenTitle = () => setIsShowTitle(false);

  const renderIcon = useCallback(() => {
    if (isLoading) {
      return <RingLoading />;
    }

    if (isMarked) {
      return <MdOutlineBookmarkAdded />;
    }

    return <MdOutlineBookmarkBorder />;
  }, [isMarked, isLoading]);

  useEffect(() => {
    if (isLoading) {
      setTimeout(() => {
        setIsLoading(false);
        accountId && dispatch(fetchAllMarkedTourThunk(accountId));
      }, 1500);
    }

    // eslint-disable-next-line
  }, [isLoading, accountId]);

  return (
    <div className={`tour-card--marking-btn${isMarked ? " marked" : ""}`}>
      <button
        onClick={() => {
          setIsLoading(true);
          onClick();
        }}
        onMouseOver={onShowTitle}
        onMouseLeave={onHiddenTitle}
      >
        {renderIcon()}
      </button>
      {isShowTitle && !isLoading && (
        <div className="tour-card--marking-btn--title">
          {isMarked ? "Đã lưu" : "Chưa lưu"}
        </div>
      )}
    </div>
  );
};

export default MarkingTourButton;
