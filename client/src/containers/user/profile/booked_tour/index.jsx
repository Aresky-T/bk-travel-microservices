import React, { useCallback, useEffect, useState } from "react";
import { useAuth } from "../../../../redux/selector";
import { getAllBookedToursApi } from "../../../../api/user/booking.api";
import LoadingIndicator from "../../../../components/global/Loading/LoadingIndicator";
import CancelBookedTourRequest from "../../../../components/user/profile/booked_tour/cancel";
import ReviewBookedTour from "../../../../components/user/profile/booked_tour/review";
import BookedTourCardContainer from "./card";

export const OPTIONS = {
  REVIEW_BOOKED_TOUR: "review-booked-tour",
  CANCEL_BOOKED_TOUR: "cancel-booked-tour",
};

const BookedTourContainer = () => {
  const [bookedTours, setBookedTours] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [isShowModal, setIsShowModal] = useState(false);
  const [type, setType] = useState("");
  const [selectedBookedTour, setSelectedBookedTour] = useState();
  const [isRefetch, setIsRefetch] = useState(false);

  const auth = useAuth();
  const accountId = auth.id;

  const startLoading = () => {
    setIsLoading(true);
  };

  const endLoading = () => {
    setTimeout(() => {
      setIsLoading(false);
    }, 1000);
  };

  const showModal = (bookedTour, type) => {
    setType(type);
    setSelectedBookedTour(bookedTour);
    setIsShowModal(true);
  };

  const hiddenModal = () => {
    setType("");
    setSelectedBookedTour(null);
    setIsShowModal(false);
  };

  const handleRefetchBookings = () => setIsRefetch(true);

  const renderBookedTourList = useCallback(() => {
    if (bookedTours.length === 0) {
      return <>Danh sách trống!</>;
    }
    return bookedTours.map((item) => (
      <BookedTourCardContainer
        bookedTour={item}
        showModal={showModal}
        key={item.id}
      />
    ));
    // eslint-disable-next-line
  }, [bookedTours]);

  const fetchAllBookingForUser = useCallback(() => {
    if (accountId) {
      startLoading();
      getAllBookedToursApi(accountId)
        .then((res) => {
          const bookedTours = [...res.data].sort((a, b) => {
            const bookedTime1 = new Date(a.bookedTime);
            const bookedTime2 = new Date(b.bookedTime);
            return bookedTime2.getTime() - bookedTime1.getTime();
          });
          setBookedTours(bookedTours);
        })
        .catch((err) => {})
        .finally(() => {
          setIsRefetch(false);
          endLoading();
        });
    }

    //eslint-disable-next-line
  }, [accountId]);

  useEffect(() => {
    fetchAllBookingForUser();
    //eslint-disable-next-line
  }, [fetchAllBookingForUser]);

  useEffect(() => {
    if (isRefetch) fetchAllBookingForUser();

    //eslint-disable-next-line
  }, [isRefetch]);

  return (
    <div className="profile__booked-tour-info">
      <div className="profile__booked-tour-info__item">
        <div className="profile-container__main__title-1">
          Danh sách Tour đã đặt
        </div>
      </div>
      <div className="profile__booked-tour-info__item">
        <div className="booked-tour-list">
          {isLoading ? (
            <LoadingIndicator />
          ) : (
            <>
              {bookedTours.length > 0 && (
                <div className="profile__note">
                  Bạn đã đặt {bookedTours.length} tour:
                </div>
              )}
              {renderBookedTourList()}
            </>
          )}
        </div>
      </div>
      <CancelBookedTourRequest
        type={type}
        isShow={isShowModal}
        hiddenModal={hiddenModal}
        bookedTour={selectedBookedTour}
        setIsRefetch={setIsRefetch}
        onRefetchBookings={handleRefetchBookings}
      />
      <ReviewBookedTour
        type={type}
        bookedTour={selectedBookedTour}
        isShow={isShowModal}
        hiddenModal={hiddenModal}
        onRefetchBookings={handleRefetchBookings}
      />
    </div>
  );
};

export default BookedTourContainer;
