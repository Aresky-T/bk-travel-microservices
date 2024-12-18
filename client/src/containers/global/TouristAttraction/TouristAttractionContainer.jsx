import React, { useCallback, useEffect, useState } from "react";
import TouristAttraction from "../../../components/global/TouristAttraction/TouristAttraction";
import { getAllTouristAttractionsWithSearchApi } from "../../../api/global/tourist_attraction.api";
// import SearchContainer from "./SearchContainer";
import useDebounce from "../../../hook/useDebounce";

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
  // const [isOpenSearch, setIsOpenSearch] = useState(false);
  const [isShowMore, setIsShowMore] = useState(false);
  const debounceSearch = useDebounce(search, 1000);

  const handleOffLoading = () => {
    setTimeout(() => {
      setIsLoading(false);
    }, 1000);
  };

  // const handleOpenSearch = () => setIsOpenSearch(true);
  // const handleCloseSearch = () => setIsOpenSearch(false);

  const handleChangeSearch = (e) => {
    setSearch(e.target.value);
  };

  const searchTouristAttractions = useCallback(() => {
    if (debounceSearch.length > 0 && debounceSearch.trim() === "") return;

    const currentPage = pagination.page ?? 0;
    const pageSize = pagination.size ?? 5;

    if (currentPage === 0) setIsLoading(true);
    getAllTouristAttractionsWithSearchApi(
      debounceSearch.trim(),
      currentPage,
      pageSize
    )
      .then((res) => {
        handleOffLoading();
        const { content, totalPages, last, totalElements } = res.data;
        setData((prevData) =>
          currentPage > 0 ? [...prevData, ...content] : content
        );
        setPagination((prev) => ({
          ...prev,
          totalPages: totalPages,
          totalElements,
          last,
        }));
        handleCloseShowMore();
      })
      .catch((err) => {
        handleOffLoading();
        handleCloseShowMore();
      });

    //eslint-disable-next-line
  }, [debounceSearch, pagination.page]);

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
    setPagination((prev) => ({ ...prev, page: 0 }));
  }, [debounceSearch]);

  useEffect(() => {
    searchTouristAttractions();
  }, [searchTouristAttractions]);

  return (
    <>
      <TouristAttraction
        data={data}
        search={search}
        handleChangeSearch={handleChangeSearch}
        handleShowMore={handleShowMore}
        isLoading={isLoading}
        isShowMore={isShowMore}
        pagination={pagination}
      />
      {/* {isOpenSearch && (
        <SearchContainer
          search={search}
          handleClose={handleCloseSearch}
          handleChange={handleChangeSearch}
        />
      )} */}
    </>
  );
};

export default TouristAttractionContainer;
