import React from "react";
import { MdStarRate } from "react-icons/md";

const colors = {
  main: "var(--primary-color)",
  grey: "rgb(201, 201, 201)",
};

const Rating = ({
  stars,
  currentValue,
  hoverValue,
  handleMouseLeave,
  handleMouseOver,
  handleClick,
}) => {
  return (
    <div className="rating">
      <div className="rating__title modal-title-2">
        <span>
          <b>Đánh giá của bạn *</b>
        </span>
      </div>
      <div className="modal-note">
        <span>1 sao cho trải nghiệm kém. 5 sao cho trải nghiệm rất tốt</span>
      </div>
      <div className="rating__main">
        <div className="stars">
          {stars.map((_, index) => (
            <MdStarRate
              key={index}
              size={60}
              style={{
                cursor: "pointer",
                padding: 10,
              }}
              color={
                (hoverValue || currentValue) > index ? colors.main : colors.grey
              }
              onMouseLeave={handleMouseLeave}
              onMouseOver={() => handleMouseOver(index + 1)}
              onClick={() => handleClick(index + 1)}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Rating;
