package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.model.SubTour;
import com.aresky.bookingservice.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetails {
    private Integer id;
    private String bookingCode;
    private Integer accountId;
    private Integer tourId;
    private Integer subTourId;
    private String tourCode;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Integer totalPersons;
    private Integer adultNumber;
    private Integer childrenNumber;
    private Integer babyNumber;
    private String note;
    private Integer amount;
    private String status;
    private EFormOfPayment formOfPayment;
    private String bookedTime;
    private Boolean isCancellationRequested;
    private TourInfo tourInfo;
    private VnPayTransactionInfoResponse transactionInfo;
    private List<TouristResponse> touristList;

    public static BookingDetails toDTO(Booking booking) {
        List<TouristResponse> touristList = booking.getTouristList()
                .stream()
                .map(TouristResponse::toDTO)
                .toList();

        return BookingDetails.builder()
                .id(booking.getId())
                .bookingCode(booking.getBookingCode())
                .accountId(booking.getAccountId())
                .tourId(booking.getTourId())
                .subTourId(booking.getSubTourId())
                .tourCode(booking.getTourCode())
                .fullName(booking.getFullName())
                .email(booking.getEmail())
                .address(booking.getAddress())
                .phone(booking.getPhone())
                .totalPersons(booking.getAdultNumber() + booking.getChildrenNumber() + booking.getBabyNumber())
                .adultNumber(booking.getAdultNumber())
                .childrenNumber(booking.getChildrenNumber())
                .babyNumber(booking.getBabyNumber())
                .note(booking.getNote())
                .amount(booking.getAmount())
                .status(booking.getStatus().name())
                .formOfPayment(booking.getFormOfPayment())
                .bookedTime(DateUtils.formatDateTime(booking.getBookedTime()))
                .isCancellationRequested(booking.getIsCancellationRequested())
                .tourInfo(Optional.ofNullable(booking.getSubTour()).map(TourInfo::new).orElse(null))
                .transactionInfo(Optional.ofNullable(booking.getTransactionInfo()).map(VnPayTransactionInfoResponse::new).orElse(null))
                .touristList(touristList)
                .build();
    }

    @Data
    @NoArgsConstructor
    public static class TourInfo {
        private Integer tourId;
        private Integer subTourId;
        private String title;
        private String image;
        private String code;
        private String status;

        public TourInfo(SubTour subTour) {
            this.tourId = subTour.getTourId();
            this.subTourId = subTour.getId();
            this.code = subTour.getTourCode();
            this.title = subTour.getTitle();
            this.image = subTour.getImage1();
            this.status = subTour.getStatus();
        }
    }
}
