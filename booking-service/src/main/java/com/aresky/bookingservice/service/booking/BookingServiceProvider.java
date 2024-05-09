package com.aresky.bookingservice.service.booking;

import com.aresky.bookingservice.dto.request.BookingFilter;
import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.dto.request.TouristRequest;
import com.aresky.bookingservice.dto.request.UpdateBookingForm;
import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.ETouristType;
import com.aresky.bookingservice.model.Tourist;
import com.aresky.bookingservice.repository.BookingRepository;
import com.aresky.bookingservice.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BookingServiceProvider implements IBookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TouristRepository touristRepository;

    @Override
    public Mono<List<Booking>> findAllBookings() {
        return bookingRepository.findAll().collectList();
    }

    @Override
    public Mono<Page<Booking>> findAllBookings(Pageable pageable) {
        return bookingRepository.findAllBy(pageable)
                .collectList()
                .zipWith(bookingRepository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    @Override
    public Mono<List<Booking>> findAllBookings(Integer accountId) {
        return bookingRepository.findAllByAccountId(accountId).collectList();
    }

    @Override
    public Mono<Page<Booking>> findAllBookings(Pageable pageable, BookingFilter filter) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<List<Booking>> findAllBookings(EBookingStatus status) {
        return bookingRepository.findAllByStatus(status).collectList();
    }

    @Override
    public Mono<Booking> findBookingBy(Integer bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public Mono<Booking> findBookingBy(Integer accountId, Integer subTourId) {
        return bookingRepository.findByAccountIdAndSubTourId(accountId, subTourId);
    }

    @Override
    public Mono<List<Tourist>> findAllTourists(Integer bookingId) {
        return touristRepository.findAllByBookingId(bookingId).collectList();
    }

    @Transactional
    @Override
    public Mono<Booking> createBooking(SubTourResponse subTour, CreateBookingForm form) {
        return Mono.zip(validateAmount(subTour, form), validateTouristList(form))
                .map(tuple -> {
                    if (!tuple.getT1()) {
                        throw new BookingException(BookingException.INVALID_AMOUNT);
                    }

                    if (!tuple.getT2()) {
                        return new BookingException(BookingException.INVALID_TOURIST_LIST);
                    }

                    return true;
                })
                .flatMap(isValidArgs -> {
                    Booking booking = CreateBookingForm.buildBooking(form);
                    booking.setTourId(subTour.getTourId());
                    booking.setTourCode(subTour.getTourCode());
                    booking.setStatus(EBookingStatus.NOT_PAY);

                    return Mono.just(CreateBookingForm.buildTouristList(form))
                            .switchIfEmpty(Mono.empty())
                            .flatMap(tourists1 -> bookingRepository.save(booking)
                                    .flatMap(savedBooking -> setBookingForTouristList(tourists1, savedBooking.getId())
                                    .flatMap(tourists2 -> touristRepository.saveAll(tourists2).then().thenReturn(savedBooking))));
                });
    }

    @Override
    public Mono<Booking> saveBooking(Booking booking) {
        return Mono.just(booking)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.REQUIRED_BOOKING)))
                .then(bookingRepository.save(booking));
    }

    @Override
    public Mono<Booking> update(UpdateBookingForm form) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<Booking> update(Integer bookingId, Map<String, Object> fields) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Transactional
    @Override
    public Mono<Void> delete(Integer bookingId) {
        return bookingRepository.existsById(bookingId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(isExists -> bookingRepository.deleteById(bookingId)
                        .then());
    }

    @Transactional
    @Override
    public Mono<Void> delete(Booking booking) {
        return Mono.just(booking)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.REQUIRED_BOOKING)))
                .then(bookingRepository.delete(booking));
    }

    @Override
    public Mono<List<Tourist>> setBookingForTouristList(List<Tourist> tourists, Integer bookingId) {
        return Mono.just(tourists)
                .switchIfEmpty(Mono.empty())
                .flatMapMany(Flux::fromIterable)
                .map(tourist -> {
                    tourist.setBookingId(bookingId);
                    return tourist;
                }).collectList();
    }

    @Override
    public Mono<Boolean> existsBookingBy(Integer bookingId) {
        return bookingRepository.existsById(bookingId);
    }

    @Override
    public Mono<Boolean> existsBookingBy(Integer accountId, Integer subTourId) {
        return bookingRepository.existsByAccountIdAndSubTourId(accountId, subTourId);
    }

    @Override
    public Mono<Boolean> validateAmount(SubTourResponse subTour, CreateBookingForm form) {
        Integer adultPrice = subTour.getAdultPrice();
        Integer childrenPrice = subTour.getAdultPrice();
        Integer babyPrice = subTour.getBabyPrice();

        Integer adultNumber = form.getAdultNumber();
        Integer childrenNumber = form.getChildrenNumber();
        Integer babyNumber = form.getBabyNumber();

        Integer amount = (adultNumber * adultPrice) + (childrenNumber * childrenPrice)
                + (babyNumber * babyPrice);

        return Mono.just(amount.equals(form.getAmount()));
    }


    @Override
    public Mono<Boolean> validateTouristList(CreateBookingForm form) {
        return Mono.just(form.getTouristList())
                .switchIfEmpty(Mono.error(new BookingException("Tourist list is empty!")))
                .flatMap(tourists -> {

                    Mono<Boolean> isValidAdultNumberMono = calculateTouristNumberByTouristType(tourists, ETouristType.ADULT)
                            .map(adultNum -> Objects.equals(adultNum, form.getAdultNumber()));

                    Mono<Boolean> isValidChildNumberMono = calculateTouristNumberByTouristType(tourists, ETouristType.CHILDREN)
                            .map(childNum -> Objects.equals(childNum, form.getChildrenNumber()));

                    Mono<Boolean> isValidBabyNumberMono = calculateTouristNumberByTouristType(tourists, ETouristType.BABY)
                            .map(babyNum -> Objects.equals(babyNum, form.getBabyNumber()));

                    return Mono.zip(isValidAdultNumberMono, isValidChildNumberMono, isValidBabyNumberMono)
                            .map(tuple -> tuple.getT1() && tuple.getT2() && tuple.getT3());
                });
    }

    @Override
    public Mono<Integer> calculateTouristNumberByTouristType(List<TouristRequest> tourists, ETouristType type) {
        return Mono.just(tourists)
                .flatMapMany(Flux::fromIterable)
                .map(tourist -> tourist.getType().equals(type))
                .collectList()
                .map(List::size);
    }
}
