import React, { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  checkMarkedTourApi,
  markTourApi,
  unmarkTourApi,
} from "../../../../api/user/marking.api";
import TourCard from "../../../../components/global/Tour/card/TourCard";
import { useAuth } from "../../../../redux/selector";

const TourCardContainer = ({ tour }) => {
  const [isMarked, setIsMarked] = useState(false);
  const auth = useAuth();
  const accountId = auth.id;

  const navigate = useNavigate();

  const onClickBooking = () => {
    navigate(`/booking/${tour.tourCode}`);
  };

  const onClickMarkingButton = () => {
    if (isMarked) {
      handleUnmarkTour();
    } else {
      handleMarkTour();
    }
  };

  const handleCheckMarkedTour = useCallback(() => {
    if (accountId && tour) {
      checkMarkedTourApi(accountId, tour.id)
        .then((res) => {
          setIsMarked(res.data);
        })
        .catch((err) => {});
    }
  }, [accountId, tour]);

  const handleMarkTour = () => {
    if (accountId && tour) {
      markTourApi(accountId, tour.id)
        .then((res) => {
          setIsMarked(true);
        })
        .catch((err) => {});
    }
  };

  const handleUnmarkTour = () => {
    if (accountId && tour) {
      unmarkTourApi(accountId, tour.id)
        .then((res) => {
          setIsMarked(false);
        })
        .catch((err) => {});
    }
  };

  useEffect(() => {
    handleCheckMarkedTour();
  }, [handleCheckMarkedTour]);

  return (
    <TourCard
      tour={tour}
      isMarked={isMarked}
      onClickBooking={onClickBooking}
      onClickMarkingButton={onClickMarkingButton}
    />
  );
};

export default TourCardContainer;
