import React from "react";
import headerVideo from "../../../assets/video/pexels-aleks-michajlowicz-6907296-1920x1080-25fps.mp4";
import { FiSearch } from "react-icons/fi";
import TouristAttractionCard from "./TouristAttractionCard";
import RollerLoading from "../Loading/RollerLoading";
import SpinnerLoading from "../Loading/SpinnerLoading";

const TouristAttraction = ({
  data,
  search,
  handleChangeSearch,
  handleShowMore,
  isLoading,
  isShowMore,
  pagination,
}) => {
  return (
    <div className="main-session tourist-attraction-container">
      <section className="main-session-header tourist-attraction-header">
        <div className="header-item tour-att-header__video">
          <video src={headerVideo} autoPlay loop />
          <h1>Địa điểm du lịch</h1>
        </div>
        <div className="tour-att-header__filter">
          <input
            type="text"
            name="search"
            placeholder="Tìm kiếm địa danh..."
            value={search}
            onChange={handleChangeSearch}
            // onFocus={handleOpenSearch}
            // autoComplete="off"
            // readOnly
          />
          <span className="search-icon">
            <FiSearch />
          </span>
        </div>
      </section>
      <section className="tourist-attraction-main">
        {isLoading ? (
          <div className="tourist-attraction-main__loading">
            <RollerLoading />
          </div>
        ) : (
          <>
            {data?.length > 0 && (
              <div className="tourist-attraction-list">
                {data.map((item) => (
                  <TouristAttractionCard data={item} key={item.name} />
                ))}
                {!pagination?.last && (
                  <div className="show-more">
                    {isShowMore ? (
                      <SpinnerLoading />
                    ) : (
                      <button onClick={handleShowMore}>Xem thêm</button>
                    )}
                  </div>
                )}
              </div>
            )}
            {data?.length < 1 && (
              <div className="empty_result">
                Không tìm thấy kết quả phù hợp!
              </div>
            )}
            {!data && <div className="empty_result">Không có dữ liệu!</div>}
          </>
        )}
      </section>
    </div>
  );
};

export default TouristAttraction;
