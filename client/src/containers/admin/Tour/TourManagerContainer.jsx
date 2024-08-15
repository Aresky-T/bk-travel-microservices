import { useCallback, useEffect, useState } from "react";
import TourManager from "../../../components/admin/Tour/TourManager";
import TourCreateContainer from "./TourCreateContainer";
import { useDispatch } from "react-redux";
import { fetchAllToursThunk } from "../../../redux/slices/admin.slice";
import { useAdmin } from "../../../redux/selector";
import TourDetailsAdminContainer from "./TourDetailsAdminContainer";

const TourManagerContainer = () => {
  const [isShowCreateTour, setIsShowCreateTour] = useState(false);
  const { tourManager } = useAdmin();
  const { selectedTourId, pageNumber } = tourManager;

  const [pagination, setPagination] = useState({
    page: pageNumber,
    size: 5,
  });

  const dispatch = useDispatch();

  const handleChangeCurrentPage = (page) => {
    setPagination({
      ...pagination,
      page: page,
    });
  };

  const handleShowCreateTour = () => {
    !isShowCreateTour && setIsShowCreateTour(true);
  };

  const handleHiddenCreateTour = () => {
    isShowCreateTour && setIsShowCreateTour(false);
  };

  const fetchAllTours = useCallback(() => {
    if (selectedTourId || isShowCreateTour) {
      return;
    }

    dispatch(fetchAllToursThunk(pagination));
    //eslint-disable-next-line
  }, [pagination, selectedTourId, isShowCreateTour]);

  useEffect(() => {
    fetchAllTours();
  }, [fetchAllTours]);

  return (
    <div className="administrator-manager-container">
      {selectedTourId ? (
        <TourDetailsAdminContainer />
      ) : (
        <TourManager
          handleChangeCurrentPage={handleChangeCurrentPage}
          handleShowCreateTour={handleShowCreateTour}
        />
      )}
      <TourCreateContainer
        isShowCreateTour={isShowCreateTour}
        handleHiddenCreateTour={handleHiddenCreateTour}
      />
    </div>
  );
};

export default TourManagerContainer;
