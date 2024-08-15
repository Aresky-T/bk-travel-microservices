import React, {
  forwardRef,
  useEffect,
  useImperativeHandle,
  useRef,
} from "react";

const PRICE_LENGTH = Number(20000000);
const PRICE_GAP = Number(500000);

const PriceRangeSlider = ({ minPrice, maxPrice, handleChangePrices }, ref) => {
  const rangeRef = useRef();
  const sliderRef = useRef();

  useImperativeHandle(ref, () => {
    return { slider: sliderRef };
  });

  useEffect(() => {
    if (rangeRef.current) {
      const range = rangeRef.current;
      range.style.left = (minPrice / PRICE_LENGTH) * 100 + "%";
      range.style.right =
        ((PRICE_LENGTH - maxPrice) / PRICE_LENGTH) * 100 + "%";
    }
  }, [minPrice, maxPrice]);

  return (
    <div className="price-range-slider" ref={sliderRef}>
      <header>
        <h2>Chọn khoảng giá</h2>
        <p>Sử dụng thanh trượt để chọn giá trị nhỏ nhất và giá trị lớn nhất</p>
      </header>
      <div className="price-input">
        <div className="price-input__field">
          <input
            type="text"
            className="price-min"
            name="minPrice"
            value={parseInt(minPrice).toLocaleString("vi-VN")}
            readOnly
          />
        </div>
        <div className="separator">-</div>
        <div className="price-input__field">
          <input
            type="text"
            className="price-max"
            name="maxPrice"
            value={parseInt(maxPrice).toLocaleString("vi-VN")}
            readOnly
          />
        </div>
      </div>
      <div className="slider">
        <div className="progress" ref={rangeRef}></div>
      </div>
      <div className="range-input">
        <input
          type="range"
          className="range-min"
          min={0}
          max={PRICE_LENGTH}
          name="minPrice"
          value={minPrice}
          step={PRICE_GAP}
          onChange={handleChangePrices}
        />
        <input
          type="range"
          className="range-max"
          min={0}
          max={PRICE_LENGTH}
          name="maxPrice"
          value={maxPrice}
          step={PRICE_GAP}
          onChange={handleChangePrices}
        />
      </div>
    </div>
  );
};

export default forwardRef(PriceRangeSlider);
