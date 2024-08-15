import React, { useEffect, useRef, useState } from "react";
import { FiSearch } from "react-icons/fi";
import { GiMoneyStack } from "react-icons/gi";
import { MdDirectionsBus, MdLocationOn } from "react-icons/md";
import PriceRangeSlider from "../Slider/PriceRangeSlider";
import { IoIosArrowDown } from "react-icons/io";

const TourFilter = ({
  handleChangePrices,
  filter,
  handleChangeFilter,
  handleSubmitFilter,
}) => {
  const [isShow, setIsShow] = useState(false);
  const pricesRef = useRef();

  const handleChangeIsShow = (value) => {
    setIsShow((prevIsShow) => !prevIsShow);
  };

  useEffect(() => {
    const handleClose = (e) => {
      if (
        pricesRef.current?.slider &&
        !pricesRef.current.slider.current.contains(e.target)
      ) {
        setIsShow(false);
      }
    };
    document.addEventListener("mousedown", handleClose);
    return () => {
      document.removeEventListener("mousedown", handleClose);
    };
  });

  return (
    <div className="header-filter tours-filter">
      <div className="filter-bar">
        <div className="filter-item">
          <div className="filter-icon">
            <MdLocationOn />
          </div>
          <div className="filter-field">
            <label htmlFor="departure-location">Điểm đi</label>
            <input
              type="text"
              name="departureLocation"
              id="departure-location"
              placeholder="Nơi bạn khởi hành?"
              defaultValue={filter?.departureLocation}
              onChange={handleChangeFilter}
            />
          </div>
        </div>
        <div className="filter-item">
          <div className="filter-icon">
            <MdLocationOn />
          </div>
          <div className="filter-field">
            <label htmlFor="destination">Điểm đến</label>
            <input
              type="text"
              name="destination"
              id="destination"
              placeholder="Bạn muốn đi đâu?"
              defaultValue={filter?.destination}
              onChange={handleChangeFilter}
            />
          </div>
        </div>
        <div className="filter-item filter-by-vehicle">
          <div className="filter-icon">
            <MdDirectionsBus />
          </div>
          <div className="filter-field">
            <label>Phương tiện</label>
            <select
              name="vehicle"
              value={filter?.vehicle}
              onChange={handleChangeFilter}
            >
              <option defaultChecked={true} value="">
                Bấm chọn
              </option>
              <option value="Máy bay">Máy bay</option>
              <option value="Xe du lịch">Xe du lịch</option>
              <option value="Máy bay, Xe du lịch">Cả hai</option>
            </select>
          </div>
        </div>
        <div className="filter-item filter-by-price">
          <div className="filter-icon">
            <GiMoneyStack />
          </div>
          <div className="filter-field">
            <label>Mức phí</label>
            <p onClick={handleChangeIsShow}>
              {filter.minPrice.toLocaleString("vi-VN")} -{" "}
              {filter.maxPrice.toLocaleString("vi-VN")}đ <IoIosArrowDown />
            </p>
            {isShow && (
              <PriceRangeSlider
                minPrice={filter.minPrice}
                maxPrice={filter.maxPrice}
                handleChangePrices={handleChangePrices}
                ref={pricesRef}
              />
            )}
          </div>
        </div>
        <div className="filter-item filter-submit">
          <div className="submit-icon" onClick={handleSubmitFilter}>
            <FiSearch />
            <span>Tìm kiếm</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TourFilter;
