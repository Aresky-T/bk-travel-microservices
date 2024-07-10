package com.aresky.bookingservice.dto.request;

import java.util.List;

import com.aresky.bookingservice.model.Booking;

import com.aresky.bookingservice.model.Tourist;
import com.aresky.bookingservice.util.DateUtils;
import com.aresky.bookingservice.util.RandomUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBookingForm {

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    @Email(message = "Địa chỉ email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Pattern(regexp = "\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})", message = "Định dạng số điện thoại không hợp lệ")
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotNull(message = "Số người lớn không được để trống")
    @Min(value = 1, message = "Phải có ít nhất 1 người lớn")
    private Integer adultNumber;

    @NotNull(message = "Số trẻ em không được để trống")
    @Min(value = 0, message = "Số trẻ em không thể là số âm")
    private Integer childrenNumber;

    @NotNull(message = "Số em bé không được để trống")
    @Min(value = 0, message = "Số em bé không thể là số âm")
    private Integer babyNumber;

    private String note;

    @NotNull(message = "ID tài khoản không được để trống")
    private Integer accountId;

    @NotNull(message = "ID tour không được để trống")
    private Integer subTourId;

    @NotNull(message = "Số tiền không được để trống")
    @Min(value = 1, message = "Số tiền phải lớn hơn 0")
    private Integer amount;

    @Valid
    @NotEmpty(message = "Danh sách du khách không được trống")
    private List<TouristRequest> touristList;

    public static Booking buildBooking(CreateBookingForm dto) {
        return Booking.builder()
                .accountId(dto.accountId)
                .subTourId(dto.subTourId)
                .bookingCode(RandomUtils.randomString(10))
                .isCancellationRequested(Boolean.FALSE)
                .fullName(dto.fullName)
                .email(dto.email)
                .phone(dto.getPhone())
                .address(dto.address)
                .adultNumber(dto.adultNumber)
                .childrenNumber(dto.childrenNumber)
                .babyNumber(dto.babyNumber)
                .note(dto.note)
                .amount(dto.amount)
                .bookedTime(DateUtils.now())
                .build();
    }

    public static List<Tourist> buildTouristList(CreateBookingForm dto) {
        return dto.touristList.stream().map(TouristRequest::buildTourist).toList();
    }
}
