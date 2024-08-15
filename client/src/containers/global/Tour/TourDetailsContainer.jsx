import TourDetails from "../../../components/global/Tour/TourDetails";
import { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getDetailsSubTourByIdApi } from "../../../api/global/tours.api";
import TourReviewsContainer from "./TourReviewsContainer";
import RollerLoading from "../../../components/global/Loading/RollerLoading";
import { useAuth } from "../../../redux/selector";
import { checkTourIsBookedByUser } from "../../../api/user/booking.api";
import { getReviewForTourApi } from "../../../api/user/review.api";

const TourDetailsContainer = () => {
  const [tour, setTour] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const [reviewState, setReviewState] = useState({
    isShowModal: false,
    isReviewed: false,
    tourId: null,
    numberOfReviews: 0,
    avgStars: 0,
    reviewList: [],
    page: 0,
    size: 10,
  });
  const [isBooked, setIsBooked] = useState(false);

  const auth = useAuth();
  const accountId = auth.id;
  const param = useParams();

  const showModal = () => {
    !reviewState.isShowModal &&
      setReviewState((prevState) => ({ ...prevState, isShowModal: true }));
  };

  const hiddenModal = () => {
    reviewState.isShowModal &&
      setReviewState((prevState) => ({ ...prevState, isShowModal: false }));
  };

  const handleCheckTourIsBookedByUser = useCallback(() => {
    if (tour && tour.id && accountId) {
      checkTourIsBookedByUser(accountId, tour.id)
        .then((res) => {
          setIsBooked(res.data);
        })
        .catch((err) => {});
    }
  }, [tour, accountId]);

  const fetchReview = useCallback(() => {
    const { page, size, tourId } = reviewState;
    if (!tourId) return;

    getReviewForTourApi(tourId, page, size)
      .then((res) => {
        const data = res.data;
        setReviewState((prevState) => ({
          ...prevState,
          isReviewed: true,
          avgStars: data.avgStars,
          reviewList: data.reviewList,
          numberOfReviews: data.numberOfReviews,
        }));
      })
      .catch((err) => {
        console.log(err);
      });
  }, [reviewState]);

  useEffect(() => {
    if (reviewState.tourId) fetchReview();

    //eslint-disable-next-line
  }, [reviewState.tourId]);

  useEffect(() => {
    if (tour) {
      setReviewState((prev) => ({ ...prev, tourId: tour.tourId }));
    }
  }, [tour]);

  useEffect(() => {
    const tourId = param.id;
    if (tourId) {
      setIsLoading(true);
      getDetailsSubTourByIdApi(tourId)
        .then((res) => {
          setTimeout(() => {
            setIsLoading(false);
            setTour(res.data);
          }, 1000);
        })
        .catch((err) => {
          setIsLoading(false);
        });
    }
  }, [param]);

  useEffect(() => {
    handleCheckTourIsBookedByUser();
  }, [handleCheckTourIsBookedByUser]);

  return (
    <div className="main-session tour-details-container">
      {isLoading ? (
        <div className="tour-details-loading">
          <RollerLoading />
        </div>
      ) : (
        <>
          {tour ? (
            <>
              <TourDetails
                tour={tour}
                reviewState={reviewState}
                showReviewsModal={showModal}
                isBooked={isBooked}
              />
              <TourReviewsContainer
                tour={tour}
                reviewState={reviewState}
                hiddenModal={hiddenModal}
              />
            </>
          ) : (
            <>Không có dữ liệu!</>
          )}
        </>
      )}
    </div>
  );
};

export default TourDetailsContainer;
