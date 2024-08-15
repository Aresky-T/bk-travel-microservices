import React, { useEffect, useState } from "react";
import { getAllToursByFilterApi } from "../../../api/global/tours.api";
import { createCustomStorage } from "../../../config/localStorageConfig";
import toursHeaderImage from "../../../assets/image/tours-header-image.jpeg";
import TourFilter from "../../../components/global/Tour/TourFilter";
import RollerLoading from "../../../components/global/Loading/RollerLoading";
import TourCardContainer from "./card";
import CustomPaginate from "../../../components/pagination/CustomPaginate";

const PAGE_SIZE = 12;
const TOUR_STORAGE = "tour_storage";
const CURRENT_PAGE = "currentPage";
const tourPaginate = createCustomStorage(TOUR_STORAGE);
tourPaginate.set(CURRENT_PAGE, 0);
const MIN_DISTANCE = Number(2000000);
const MAX_PRICE = Number(20000000);

const ToursContainer = () => {
  const [tours, setTours] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [pagination, setPagination] = useState({
    size: PAGE_SIZE,
    page: tourPaginate.get("currentPage") || 0,
  });
  const [filter, setFilter] = useState({
    minPrice: 0,
    maxPrice: MAX_PRICE,
  });

  const [isLoading, setIsLoading] = useState(false);

  const handleOffLoading = () => {
    setTimeout(() => {
      setIsLoading(false);
    }, 500);
  };

  const getAllToursWithFilter = (fields) => {
    setIsLoading(true);
    getAllToursByFilterApi(fields)
      .then((res) => {
        setTours(res.data.content);
        setTotalPages(res.data.totalPages);
        handleOffLoading();
      })
      .catch((err) => {
        handleOffLoading();
      });
  };

  const handleChangePrices = (e) => {
    if (e.target) {
      const name = e.target.name;
      const value = Number(e.target.value);

      if (name === "minPrice" && value + MIN_DISTANCE <= MAX_PRICE) {
        if (filter.maxPrice - value < MIN_DISTANCE) {
          setFilter({
            ...filter,
            minPrice: value,
            maxPrice: value + MIN_DISTANCE,
          });
        } else {
          setFilter({
            ...filter,
            minPrice: value,
          });
        }
      }

      if (name === "maxPrice" && value >= MIN_DISTANCE) {
        if (value - filter.minPrice < MIN_DISTANCE) {
          setFilter({
            ...filter,
            maxPrice: value,
            minPrice: value - MIN_DISTANCE,
          });
        } else {
          setFilter({
            ...filter,
            maxPrice: value,
          });
        }
      }
    }
  };

  const handleChangeFilter = (e) => {
    if (e.target) {
      const { name, value } = e.target;
      setFilter((prevFilter) => ({
        ...prevFilter,
        [name]: value,
      }));
    }
  };

  const handleChangeCurrentPage = (page) => {
    setPagination((prevPagination) => ({
      ...prevPagination,
      page: page,
    }));
    tourPaginate.set(CURRENT_PAGE, page);
  };

  const handleSubmitFilter = () => {
    handleChangeCurrentPage(0);
    for (const key in filter) {
      if (filter[key] === "") {
        delete filter[key];
      }
    }
    getAllToursWithFilter({ ...pagination, ...filter });
  };

  useEffect(() => {
    getAllToursWithFilter({
      ...filter,
      ...pagination,
    });
    // eslint-disable-next-line
  }, [pagination.page]);

  return (
    <div className="main-session tours-container">
      <section className="main-session-header tours-header">
        <div className="header-item tours-header__image">
          <img src={toursHeaderImage} alt="tours-header" />
          <h1>Khám phá Tour</h1>
        </div>
        <TourFilter
          filter={filter}
          handleChangeFilter={handleChangeFilter}
          handleChangePrices={handleChangePrices}
          handleSubmitFilter={handleSubmitFilter}
        />
      </section>
      <section className="tours-main">
        {isLoading ? (
          <div className="tours-main__loading">
            <RollerLoading />
          </div>
        ) : (
          <>
            {tours && tours.length > 0 ? (
              <div className="tours-list">
                {tours.map((tour) => (
                  <TourCardContainer tour={tour} key={tour.id} />
                ))}
              </div>
            ) : (
              <div className="tours-list--empty">Không có kết quả!</div>
            )}
            <CustomPaginate
              currentPage={pagination?.page}
              firstLabel="Đầu"
              lastLabel="Cuối"
              setCurrentPage={handleChangeCurrentPage}
              totalPages={totalPages}
            />
          </>
        )}
      </section>
    </div>
  );
};

export default ToursContainer;
