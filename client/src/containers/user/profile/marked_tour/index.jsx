import React, { useCallback, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useAuth, useMarking } from "../../../../redux/selector";
import LoadingIndicator from "../../../../components/global/Loading/LoadingIndicator";
import TourCardContainer from "../../../global/Tour/card";
import { fetchAllMarkedTourThunk } from "../../../../redux/slices/marking.slice";

const MarkedTourContainer = () => {
  const [isLoading, setIsLoading] = useState(false);
  const dispatch = useDispatch();

  const marking = useMarking();
  const markedTours = marking.markedTours;

  const auth = useAuth();
  const accountId = auth.id;

  const renderMarkedTours = useCallback(() => {
    if (isLoading) {
      return <LoadingIndicator />;
    }

    if (!markedTours || markedTours.length === 0) {
      return "Danh sách trống!";
    }

    return markedTours.map((item) => (
      <TourCardContainer tour={item.subTour} key={item.id} />
    ));
  }, [markedTours, isLoading]);

  const fetchAllMarkedTour = useCallback(() => {
    if (accountId) {
      setIsLoading(true);
      dispatch(fetchAllMarkedTourThunk(accountId));
    }

    // eslint-disable-next-line
  }, [accountId]);

  useEffect(() => {
    fetchAllMarkedTour();
  }, [fetchAllMarkedTour]);

  useEffect(() => {
    if (isLoading) {
      setTimeout(() => {
        setIsLoading(false);
      }, 500);
    }
  }, [isLoading]);

  return (
    <div className="profile-container__main_item profile--marked-tour">
      <div className="profile-item--marked-tour title">
        <div className="profile-container__main__title-1">Tour đã đánh dấu</div>
      </div>
      <div className="profile-item--marked-tour list">
        {renderMarkedTours()}
      </div>
    </div>
  );
};

export default MarkedTourContainer;
