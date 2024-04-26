package com.aresky.bookingservice.service.booking;

import java.util.List;
import java.util.Map;

import com.aresky.bookingservice.dto.request.*;
import com.aresky.bookingservice.dto.response.BookingDetails;
import com.aresky.bookingservice.model.Tourist;
import com.aresky.bookingservice.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.bookingservice.dto.response.BookingResponse;
import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.dto.response.VnPayTransactionInfo;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.model.EPaymentType;
import com.aresky.bookingservice.repository.BookingRepository;
import com.aresky.bookingservice.service.account.AccountService;
import com.aresky.bookingservice.service.payment.PaymentService;
import com.aresky.bookingservice.service.tour.TourService;

import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;

@Service
public class BookingServiceImp implements IBookingService {

        @Autowired
        private BookingRepository bookingRepository;

        @Autowired
        private TouristRepository touristRepository;

        @Autowired
        private TourService tourService;

        @Autowired
        private AccountService accountService;

        @Autowired
        private PaymentService paymentService;

        @Transactional
        @Override
        public Mono<String> handleBooking(CreateBookingForm form, EPaymentType type) {
                int accountId = form.getAccountId();
                int subTourId = form.getSubTourId();

                return bookingRepository.existsByAccountIdAndSubTourId(accountId, subTourId)
                                .filter(Boolean.FALSE::equals)
                                .switchIfEmpty(Mono.error(
                                                new BookingException(
                                                                "Bạn đã đặt tour này rồi, vui lòng kiểm tra trong hồ sơ của bạn!")))
                                .flatMap(value -> {
                                        if (type == EPaymentType.PAY_LATER) {
                                                return Mono.zip(findSubTour(subTourId), validateAccount(accountId))
                                                                .flatMap(tuple -> this
                                                                                .create(tuple.getT1(), accountId, form)
                                                                                .thenReturn(
                                                                                                "Đặt tour thành công, vui lòng kiểm tra thông tin chi tiết trong hồ sơ của bạn!"));
                                        }

                                        if (type == EPaymentType.VNPAY_ON_WEBSITE) {
                                                return paymentService.connect()
                                                                .filter(Boolean.TRUE::equals)
                                                                .flatMap(isConnected -> Mono.zip(findSubTour(subTourId),
                                                                                validateAccount(accountId))
                                                                                .flatMap(tuple -> {
                                                                                        SubTourResponse subTour = tuple
                                                                                                        .getT1();

                                                                                        return this.create(subTour,
                                                                                                        accountId, form)
                                                                                                        .flatMap(booking -> paymentService
                                                                                                                        .getVnPayPaymentURL(
                                                                                                                                        PaymentRequest.createDTO(
                                                                                                                                                        booking,
                                                                                                                                                        subTour))
                                                                                                                        .onErrorResume(ex -> bookingRepository
                                                                                                                                        .delete(booking)
                                                                                                                                        .then(Mono.error(
                                                                                                                                                        ex))));
                                                                                }));
                                        }

                                        return Mono.error(new BookingException(
                                                        "Hiện không hỗ trợ hình thức thanh toán này!"));
                                });
        }

        @Override
        public Mono<String> handleBookingAfterPaymentWithVnPay(VnPayReturn vnPayReturn) {
                String responseCode = vnPayReturn.getResponseCode();

                return bookingRepository.findById(vnPayReturn.getBookingId())
                                .flatMap(booking -> {
                                        if (responseCode.equals("00") || Integer.parseInt(responseCode) == 0) {
                                                booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                                                booking.setStatus(EBookingStatus.PAY_UP);
                                                return paymentService.requestCloseVnPayPaymentSession(vnPayReturn)
                                                                .flatMap(message -> {
                                                                        return bookingRepository.save(booking)
                                                                                        .thenReturn("SUCCESS");
                                                                });
                                        }

                                        if (responseCode.equals("24") || Integer.parseInt(responseCode) == 24) {
                                                booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                                                paymentService.requestCloseSession(vnPayReturn.getBookingId());
                                                return bookingRepository.delete(booking)
                                                                .thenReturn("CANCELED");
                                        }

                                        booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                                        booking.setStatus(EBookingStatus.PAY_FAILED);
                                        paymentService.requestCloseSession(vnPayReturn.getBookingId());
                                        return bookingRepository.save(booking)
                                                        .thenReturn("FAILED");
                                });
        }

        @Override
        public Mono<String> handlePaymentWithVnPayAfterBooking(Integer bookingId) {
                return bookingRepository.findById(bookingId)
                                .switchIfEmpty(Mono.error(
                                                new BookingException("Invalid bookingId!")))
                                .flatMap(booking -> tourService.findSubTour(booking.getSubTourId())
                                                .flatMap(subTour -> paymentService
                                                                .getVnPayPaymentURL(PaymentRequest.createDTO(booking,
                                                                                subTour))));
        }

        @Override
        public Mono<String> handlePaymentAfterBooking(Integer bookingId, EFormOfPayment formOfPayment) {
                return bookingRepository.findById(bookingId)
                                .switchIfEmpty(Mono.error(new BookingException("Invalid bookingId")))
                                .flatMap(booking -> findSubTour(booking.getSubTourId())
                                                .switchIfEmpty(Mono
                                                                .error(new BookingException("SubTour was not found!")))
                                                .flatMap(
                                                                subTour -> paymentService.getVnPayPaymentURL(
                                                                                PaymentRequest.createDTO(booking,
                                                                                                subTour))
                                                                                .onErrorResume(Mono::error)));
        }

        @Override
        public Mono<Page<BookingResponse>> findAll(Pageable pageable) {
                return bookingRepository.findAllBy(pageable)
                                .map(BookingResponse::toDTO)
                                .collectList()
                                .map(dtos -> new PageImpl<>(dtos, pageable, dtos.size()));
        }

        @Override
        public Mono<List<BookingResponse>> findAll(Integer accountId) {
                return bookingRepository.findAllByAccountId(accountId)
                                .switchIfEmpty(Mono.empty())
                                .map(BookingResponse::toDTO)
                                .collectList();
        }

        @Override
        public Mono<Page<BookingResponse>> findAll(Pageable pageable, BookingFilter filter) {
                throw new UnsupportedOperationException("Unimplemented method 'findAll'");
        }

        @Override
        public Mono<VnPayTransactionInfo> findVnPayTransactionInfo(Integer bookingId) {
                return bookingRepository.existsById(bookingId)
                                .filter(Boolean.TRUE::equals)
                                .switchIfEmpty(Mono.error(new BookingException("Invalid bookingId!")))
                                .flatMap(validBooking -> paymentService.getVnPayTransactionInfo(bookingId)
                                                .map(value -> value));
        }

        @Override
        public Mono<BookingDetails> findOne(Integer bookingId) {
                return bookingRepository.findById(bookingId)
                                .switchIfEmpty(Mono.error(new BookingException("Invalid bookingId")))
                                .flatMap(booking -> Mono.zip(
                                                findSubTour(booking.getSubTourId()),
                                                findAllTourist(bookingId)).flatMap(tuple -> {
                                                        SubTourResponse subTour = tuple.getT1();
                                                        List<Tourist> touristList = tuple.getT2();
                                                        return Mono.just(BookingDetails.toDTO(booking, subTour,
                                                                        touristList));
                                                }))
                                .onErrorResume(ex -> {
                                        return Mono.error(ex);
                                });
        }

        @Override
        public Mono<BookingDetails> findOne(Integer accountId, Integer subTourId) {
                return Mono.zip(validateAccount(accountId), findSubTour(subTourId))
                                .onErrorResume(Mono::error)
                                .flatMap(tuple -> findByAccountIdAndSubTourId(accountId, subTourId)
                                                .flatMap(booking -> findAllTourist(booking.getId())
                                                                .map(tourist -> BookingDetails.toDTO(booking,
                                                                                tuple.getT2(), tourist))));
        }

        @Transactional
        @Override
        public Mono<BookingResponse> update(UpdateBookingForm form) {
                throw new UnsupportedOperationException("Unimplemented method 'update'");
        }

        @Transactional
        @Override
        public Mono<BookingResponse> update(Integer bookingId, Map<String, Object> fields) {
                throw new UnsupportedOperationException("Unimplemented method 'update'");
        }

        @Transactional
        @Override
        public Mono<Void> delete(Integer bookingId) {
                return bookingRepository.existsById(bookingId)
                                .filter(Boolean.TRUE::equals)
                                .switchIfEmpty(Mono.error(new BookingException("Invalid bookingId!")))
                                .flatMap(isExists -> bookingRepository.deleteById(bookingId)
                                                .then());
        }

        private Mono<List<Tourist>> findAllTourist(Integer bookingId) {
                return touristRepository.findAllByBookingId(bookingId).switchIfEmpty(Mono.empty()).collectList();
        }

        private Mono<Booking> findByAccountIdAndSubTourId(Integer accountId, Integer subTourId) {
                return bookingRepository.findByAccountIdAndSubTourId(accountId, subTourId)
                                .switchIfEmpty(Mono.error(new BookingException("This Booking info does not exist!")));
        }

        private Mono<Boolean> validateAccount(Integer accountId) {
                return accountService.validateAccount(accountId);
        }

        private Mono<SubTourResponse> findSubTour(Integer subTourId) {
                return tourService.findSubTour(subTourId);
        }

        @Transactional
        private Mono<Booking> create(SubTourResponse subTour, Integer accountId, CreateBookingForm form) {
                validateTouristList(form);
                validateAmount(subTour, form);

                Booking booking = CreateBookingForm.buildBooking(form);
                booking.setTourId(subTour.getTourId());
                booking.setTourCode(subTour.getTourCode());
                booking.setStatus(EBookingStatus.NOT_PAY);

                List<Tourist> tourists = form.getTouristList().stream()
                                .map(dto -> {
                                        Tourist tourist = TouristRequest.buildTourist(dto);
                                        tourist.setBookingId(booking.getId());
                                        return tourist;
                                })
                                .toList();

                return bookingRepository.save(booking)
                                .flatMap(savedBooking -> {
                                        tourists.forEach(tourist -> tourist.setBookingId(savedBooking.getId()));
                                        return touristRepository.saveAll(tourists)
                                                        .collectList()
                                                        .thenReturn(savedBooking);
                                });
        }

        private void validateAmount(SubTourResponse subTour, CreateBookingForm form) {
                Integer adultPrice = subTour.getAdultPrice();
                Integer childrenPrice = subTour.getAdultPrice();
                Integer babyPrice = subTour.getBabyPrice();

                Integer adultNumber = form.getAdultNumber();
                Integer childrenNumber = form.getChildrenNumber();
                Integer babyNumber = form.getBabyNumber();

                Integer amount = (adultNumber * adultPrice) + (childrenNumber * childrenPrice)
                                + (babyNumber * babyPrice);
                System.out.println("amount: " + amount);

                if (!amount.equals(form.getAmount())) {
                        throw new BookingException("Invalid amount!");
                }
        }

        private void validateTouristList(CreateBookingForm form) {
                List<TouristRequest> touristRequests = form.getTouristList();

                int adultNumber = 0;
                int childrenNumber = 0;
                int babyNumber = 0;

                for (TouristRequest tourist : touristRequests) {
                        switch (tourist.getType()) {
                                case ADULT:
                                        adultNumber += 1;
                                        break;
                                case CHILDREN:
                                        childrenNumber += 1;
                                        break;
                                case BABY:
                                        babyNumber += 1;
                                        break;
                                default:
                                        break;
                        }
                }

                if (adultNumber != form.getAdultNumber()) {
                        throw new BookingException("Invalid adultNumber!");
                }

                if (childrenNumber != form.getChildrenNumber()) {
                        throw new BookingException("Invalid childrenNumber!");
                }

                if (babyNumber != form.getBabyNumber()) {
                        throw new BookingException("Invalid babyNumber!");
                }
        }
}
