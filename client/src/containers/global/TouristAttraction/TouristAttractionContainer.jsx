import React, { useCallback, useEffect, useState } from "react";
import TouristAttraction from "../../../components/global/TouristAttraction/TouristAttraction";
import { getAllTouristAttractionsApi } from "../../../api/global/tourist_attraction.api";
import SearchContainer from "./SearchContainer";

const TouristAttractionContainer = () => {
  const [data, setData] = useState([]);
  const [pagination, setPagination] = useState({
    size: 5,
    page: 0,
    totalPages: 1,
    sort: "",
  });
  const [search, setSearch] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [isOpenSearch, setIsOpenSearch] = useState(false);
  const [isShowMore, setIsShowMore] = useState(false);

  const handleOffLoading = () => {
    setTimeout(() => {
      setIsLoading(false);
    }, 1000);
  };

  const handleOpenSearch = () => setIsOpenSearch(true);
  const handleCloseSearch = () => setIsOpenSearch(false);

  const handleChangeSearch = (e) => {
    setSearch(e.target.value);
  };

  const fetchAllTouristAttractions = useCallback(() => {
    if (data.length === 0) {
      setIsLoading(true);
    }

    getAllTouristAttractionsApi(pagination)
      .then((res) => {
        handleOffLoading();
        const { content, totalPages } = res.data;
        setData((prev) => [...prev, ...content]);
        setPagination((prev) => ({ ...prev, totalPages: totalPages }));
        handleCloseShowMore();
      })
      .catch((err) => {
        handleOffLoading();
        handleCloseShowMore();
      });
    //eslint-disable-next-line
  }, [pagination.page]);

  const handleShowMore = () => {
    setIsShowMore(true);
    setPagination((prev) => ({
      ...prev,
      page: prev.page + 1,
    }));
  };

  const handleCloseShowMore = () => {
    setTimeout(() => {
      setIsShowMore(false);
    }, 500);
  };

  useEffect(() => {
    fetchAllTouristAttractions();
  }, [fetchAllTouristAttractions]);

  return (
    <>
      <TouristAttraction
        data={data}
        handleOpenSearch={handleOpenSearch}
        handleShowMore={handleShowMore}
        isLoading={isLoading}
        isShowMore={isShowMore}
        currentPage={pagination.page}
        totalPages={pagination.totalPages}
      />
      {isOpenSearch && (
        <SearchContainer
          search={search}
          handleClose={handleCloseSearch}
          handleChange={handleChangeSearch}
        />
      )}
    </>
  );
};

export default TouristAttractionContainer;
