package com.aresky.tourservice.service;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.Instant;
import java.util.*;
import java.util.Map.Entry;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.aresky.tourservice.utils.TourUtils;
import com.aresky.tourservice.utils.TourUtils.TourParams.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.aresky.tourservice.dto.request.SubTourCreateForm;
import com.aresky.tourservice.dto.request.TourCreateForm;
import com.aresky.tourservice.dto.request.TourFilter;
import com.aresky.tourservice.dto.request.TourUpdateForm;
import com.aresky.tourservice.dto.response.SubTour2Response;
import com.aresky.tourservice.dto.response.SubTourResponse;
import com.aresky.tourservice.dto.response.TourResponse;
import com.aresky.tourservice.exception.TourException;
import com.aresky.tourservice.model.ETourStatus;
import com.aresky.tourservice.model.SubTour;
import com.aresky.tourservice.model.Tour;
import com.aresky.tourservice.repository.SubTourRepository;
import com.aresky.tourservice.repository.TourRepository;

import io.r2dbc.spi.Row;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TourServiceImp implements ITourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private SubTourRepository subTourRepository;

    @Autowired
    private DatabaseClient databaseClient;

    private static final String[] BLACKLIST_TOUR_KEYS = { "id", "tourCode", "tourId" };

    @Transactional
    @Override
    public Mono<Void> createTour(TourCreateForm form) {
        return existsTourByTitle(form.getTitle())
                .flatMap(isExists -> {
                    if (isExists) {
                        throw new TourException("This tour already exist");
                    }
                    return tourRepository.save(TourCreateForm.toEntity(form))
                            .then();
                });
    }

    @Transactional
    @Override
    public Mono<Void> createSubTour(SubTourCreateForm form) {
        Mono<Tour> tourMono = tourRepository.findById(form.getTourId());
        Mono<Boolean> subTourExistMono = existsSubTourByTitle(form.getTitle());

        return Mono.zip(tourMono, subTourExistMono)
                .flatMap(tuple -> {
                    Tour tour = tuple.getT1();
                    boolean isSubTourExist = tuple.getT2();

                    if (Objects.isNull(tour)) {
                        throw new TourException("This tour doesn't exist!");
                    }

                    if (isSubTourExist) {
                        throw new TourException("This sub tour already exist!");
                    }

                    SubTour subTour = SubTourCreateForm.toEntity(form);
                    subTour.setAvailableSeats(tour.getTotalSeats());

                    return subTourRepository.save(subTour)
                            .flatMap(createdSubTour -> {
                                tour.setTotalSubTours(tour.getTotalSubTours() + 1);
                                return tourRepository.save(tour).then();
                            });
                });
    }

    @Override
    public Mono<Void> updateTourWithSubTour(TourUpdateForm form) {
        // Mono<Tour> tourMono = Mono.just(TourUpdateForm.buildTour(form));
        // Mono<SubTour> subTourMono = Mono.just(TourUpdateForm.buildSubTour(form));

        // return tourRepository.existsById(form.getId())
        // .flatMap(isExistTour -> {
        // if(!isExistTour){
        // throw new TourException("This tour doesn't exist!");
        // }

        // });
        return Mono.empty();
    }

    @Transactional
    @Override
    public Mono<Void> updateTourWithSubTour(int tourId, int subTourId, Map<String, Object> fields) {
        Mono<Tour> tourMono = tourRepository.findById(tourId)
                .switchIfEmpty(Mono.error(new TourException("This tour doesn't exist!")));
        Mono<SubTour> subTourMono = subTourRepository.findById(subTourId)
                .switchIfEmpty(Mono.error(new TourException("This sub tour doesn't exist!")));

        return updateTourWithSubTour(tourMono, subTourMono, fields);
    }

    @Override
    public Mono<Void> updateTourWithSubTour(int tourId, String tourCode, Map<String, Object> fields) {
        Mono<Tour> tourMono = tourRepository.findById(tourId)
                .switchIfEmpty(Mono.error(new TourException("This tour doesn't exist!")));

        Mono<SubTour> subTourMono = subTourRepository.findByTourCode(tourCode)
                .switchIfEmpty(Mono.error(new TourException("This sub tour doesn't exist!")));

        return updateTourWithSubTour(tourMono, subTourMono, fields);
    }

    @Override
    public Mono<Page<TourResponse>> findAllTours(Pageable pageable) {
        return tourRepository.findAllBy(pageable)
                .switchIfEmpty(Mono.empty())
                .map(TourResponse::toDTO)
                .collectList()
                .zipWith(tourRepository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    @Override
    public Mono<Page<TourResponse>> findAllTours(Pageable pageable, TourFilter tourFilter) {
        TourUtils.TourParams tourParams = TourUtils.buildTourParams()
                .bind(TourFilter.DEPARTURE_LOCATION, tourFilter.getDepartureLocation())
                .bind(TourFilter.DESTINATION, tourFilter.getDestination())
                .bind(TourFilter.VEHICLE, tourFilter.getVehicle())
                .bind(TourFilter.MIN_PRICE, tourFilter.getMinPrice())
                .bind(TourFilter.MAX_PRICE, tourFilter.getMaxPrice());

        String query = generateQuery(tourFilter).concat(" LIMIT :limit OFFSET :offset");

        DatabaseClient.GenericExecuteSpec executeSpec = databaseClient.sql(query);
        for (Param param : tourParams.getParams()) {
            executeSpec = executeSpec.bind(param.getName(), param.getValue());
        }

        return executeSpec
                .bind("limit", pageable.getPageSize())
                .bind("offset", pageable.getOffset())
                .map((row, metadata) -> mapRowToTour(row))
                .all()
                .map(TourResponse::toDTO)
                .collectList()
                .map(tourResponses -> {
                    int sizes = tourResponses.size();
                    return new PageImpl<>(tourResponses, pageable, sizes);
                });
    }

    @Override
    public Mono<List<TourResponse>> findLatestTours(int count) {
        throw new UnsupportedOperationException("Unimplemented method 'findLatestTours'");
    }

    @Override
    public Mono<TourResponse> findTourById(int id) {
        return tourRepository.findById(id)
                .map(TourResponse::toDTO)
                .switchIfEmpty(Mono.error(new TourException("Invalid id")));
    }

    @Override
    public Mono<Page<SubTourResponse>> findAllSubTours(Pageable pageable) {
        return subTourRepository.findAllBy(pageable)
                .switchIfEmpty(Mono.empty())
                .flatMap(subTour -> tourRepository.findById(subTour.getTourId())
                        .map(tour -> SubTourResponse.toDTO(subTour, tour)))
                .collectList()
                .zipWith(subTourRepository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    @Override
    public Mono<Page<SubTourResponse>> findAllSubTours(Pageable pageable, TourFilter tourFilter) {
        TourUtils.TourParams tourParams = TourUtils.buildTourParams()
                .bind(TourFilter.DEPARTURE_LOCATION, tourFilter.getDepartureLocation())
                .bind(TourFilter.DESTINATION, tourFilter.getDestination())
                .bind(TourFilter.VEHICLE, tourFilter.getVehicle())
                .bind(TourFilter.MIN_PRICE, tourFilter.getMinPrice())
                .bind(TourFilter.MAX_PRICE, tourFilter.getMaxPrice());

        String query = generateQuery(tourFilter);
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(query);

        for (Param param : tourParams.getParams()) {
            spec = spec.bind(param.getName(), param.getValue());
        }

        return spec.map((row, metadata) -> mapRowToTour(row))
                .all()
                .flatMap(tour -> {
                    if (tour.getTotalSubTours() == 0) {
                        return Flux.empty();
                    }

                    return subTourRepository.findAllByTourId(tour.getId())
                            .map(subTour -> SubTourResponse.toDTO(subTour, tour));
                })
                .collectList()
                .map(dtos -> new PageImpl<>(dtos, pageable, dtos.size()));
    }

    @Override
    public Mono<List<SubTour2Response>> findAllSubTours(int tourId) {
        String query = "SELECT * from sub_tour WHERE tour_id = :tourId";
        return databaseClient.sql(query)
                .bind("tourId", tourId)
                .map((row, metadata) -> mapRowToSubTour(row))
                .all()
                .map(SubTour2Response::toDTO)
                .collectList();
    }

    @Override
    public Mono<List<SubTourResponse>> findLatestSubTours(int count) {
        throw new UnsupportedOperationException("Unimplemented method 'findLatestSubTours'");
    }

    @Override
    public Mono<SubTourResponse> findSubTourByTourCode(String tourCode) {
        return subTourRepository.findByTourCode(tourCode)
                .flatMap(subTour -> {
                    return tourRepository.findById(subTour.getTourId())
                            .map(tour -> SubTourResponse.toDTO(subTour, tour));
                })
                .switchIfEmpty(Mono.error(new TourException("This sub tour doesn't exist!")));
    }

    @Override
    public Mono<SubTourResponse> findSubTourById(int subTourId) {
        return subTourRepository.findById(subTourId)
                .flatMap(subTour -> {
                    return tourRepository.findById(subTour.getTourId())
                            .map(tour -> SubTourResponse.toDTO(subTour, tour));
                })
                .switchIfEmpty(Mono.error(new TourException("This sub tour doesn't exist!")));
    }

    @Transactional
    @Override
    public Mono<Void> deleteTourById(int id) {
        return tourRepository.existsById(id)
                .filter(isExists -> isExists)
                .flatMap(isExists -> tourRepository.deleteById(id).then())
                .switchIfEmpty(Mono.error(new TourException("This tour doesn't exist!")));
    }

    @Transactional
    @Override
    public Mono<Void> deleteSubTourById(int id) {
        System.out.println(id);
        return subTourRepository.existsById(id)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new TourException("This sub tour doesn't exist!")))
                .flatMap(isExists -> {
                    System.out.println(isExists);
                    return subTourRepository.deleteById(id).then();
                });
    }

    @Transactional
    @Override
    public Mono<Void> deleteSubTourByTourCode(String tourCode) {
        return subTourRepository.existsByTourCode(tourCode).filter(isExists -> isExists)
                .flatMap(isExists -> subTourRepository.deleteByTourCode(tourCode).then())
                .switchIfEmpty(Mono.error(new TourException("This sub tour doesn't exist!")));
    }

    @Transactional
    @Override
    public Mono<Void> deleteAllSubToursByTourId(int tourId) {
        return subTourRepository.deleteAllByTourId(tourId);
    }

    @Transactional
    @Override
    public Mono<Void> updateOnlyTour(int tourId, Map<String, Object> fields) {
        return tourRepository.findById(tourId)
                .flatMap(tour -> tourRepository
                        .save(this.updateTourByFields(tour, fields)).then());
    }

    @Transactional
    @Override
    public Mono<Void> updateOnlySubTour(int subTourId, Map<String, Object> fields) {
        return subTourRepository.findById(subTourId)
                .flatMap(subTour -> subTourRepository
                        .save(this.updateSubTourByFields(subTour, fields)).then());
    }

    @Override
    public Mono<Boolean> existsTourById(int id) {
        return tourRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> existsTourByTitle(String title) {
        return tourRepository.existsByTitle(title);
    }

    @Override
    public Mono<Boolean> existsSubTourById(int id) {
        return subTourRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> existsSubTourByTitle(String title) {
        return subTourRepository.existsByTitle(title);
    }

    @Override
    public Mono<Boolean> existsSubTourByTourCode(String tourCode) {
        return subTourRepository.existsByTourCode(tourCode);
    }

    @Transactional
    private Mono<Void> updateTourWithSubTour(Mono<Tour> tourMono, Mono<SubTour> subTourMono,
            Map<String, Object> fields) {
        String[] blackListKeys = { "id", "tourCode", "tourId" };
        return Mono.zip(tourMono, subTourMono)
                .flatMap(tuple -> {
                    Tour tour = tuple.getT1();
                    SubTour subTour = tuple.getT2();

                    fields.forEach((key, value) -> {
                        if (List.of(blackListKeys).contains(key.trim())) {
                            throw new TourException("Can not update field: " + key);
                        }

                        Field field = ReflectionUtils.findField(tour.getClass(), key);

                        if (field == null) {
                            field = ReflectionUtils.findField(subTour.getClass(), key);
                        }

                        if (field != null) {
                            field.setAccessible(true);
                            boolean isInstanceOfTour = field.getDeclaringClass().isInstance(tour);

                            if (field.getType().equals(ETourStatus.class)) {
                                ReflectionUtils.setField(
                                        field,
                                        field.getDeclaringClass().cast(isInstanceOfTour ? tour : subTour),
                                        ETourStatus.valueOf(String.valueOf(value)));
                            } else {
                                ReflectionUtils.setField(
                                        field,
                                        field.getDeclaringClass().cast(isInstanceOfTour ? tour : subTour),
                                        field.getType().cast(value));
                            }
                        }
                    });

                    return tourRepository.save(tour)
                            .then(subTourRepository.save(subTour))
                            .then();
                });
    }

    private Tour updateTourByFields(Tour tour, Map<String, Object> fields) {
        String[] blackListKeys = { "id" };

        for (Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (List.of(blackListKeys).contains(key.trim())) {
                continue;
            }

            Field field = ReflectionUtils.findField(Tour.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, tour, field.getType().cast(value));
            }
        }

        return tour;
    }

    private SubTour updateSubTourByFields(SubTour subTour, Map<String, Object> fields) {
        for (Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (isBlacklisted(key)) {
                continue;
            }

            Field field = findSubTourField(subTour, key);
            if (field != null) {
                field.setAccessible(true);

                try {
                    value = convertValue(field, value);
                } catch (Exception e) {
                    log.error("Error converting value for field: {}", key);
                    continue;
                }

                try {
                    field.set(subTour, value);
                } catch (IllegalAccessException e) {
                    log.error("Error setting value for field: {}", key);
                }

                ReflectionUtils.setField(field, subTour, value);
            }
        }

        return subTour;
    }

    private Tour mapRowToTour(Row row) {
        return Tour.builder()
                .id(row.get("id", Integer.class))
                .title(row.get("title", String.class))
                .destinations(row.get("destinations", String.class))
                .duration(row.get("duration", String.class))
                .departureLocation(row.get("departure_location", String.class))
                .schedules(row.get("schedules", String.class))
                .vehicle(row.get("vehicle", String.class))
                .totalSeats(row.get("total_seats", Integer.class))
                .adultPrice(row.get("adult_price", Integer.class))
                .childrenPrice(row.get("children_price", Integer.class))
                .babyPrice(row.get("baby_price", Integer.class))
                .image1(row.get("image1", String.class))
                .image2(row.get("image2", String.class))
                .image3(row.get("image3", String.class))
                .image4(row.get("image4", String.class))
                .totalSubTours(row.get("total_sub_tours", Integer.class))
                .createdTime(row.get("created_time", ZonedDateTime.class))
                .build();
    }

    private static String generateQuery(TourFilter tourFilter) {
        String query = "SELECT * FROM tour WHERE 1=1";

        if (tourFilter.getDepartureLocation() != null) {
            query += " AND departure_location = :" + TourFilter.DEPARTURE_LOCATION;
        }

        if (tourFilter.getDestination() != null) {
            query += " AND destinations LIKE CONCAT('%', :" + TourFilter.DESTINATION + ", '%')";
        }

        if (tourFilter.getVehicle() != null) {
            query += " AND vehicle LIKE CONCAT ('%', :" + TourFilter.VEHICLE + ", '%')";
        }

        if (tourFilter.getMinPrice() != null) {
            query += " AND adult_price >= :" + TourFilter.MIN_PRICE;
        }

        if (tourFilter.getMaxPrice() != null) {
            query += " AND adult_price <= :" + TourFilter.MAX_PRICE;
        }

        return query;
    }

    private SubTour mapRowToSubTour(Row row) {
        return SubTour.builder()
                .id(row.get("id", Integer.class))
                .tourId(row.get("tour_id", Integer.class))
                .tourCode(row.get("tour_code", String.class))
                .title(row.get("title", String.class))
                .status(row.get("status", ETourStatus.class))
                .availableSeats(row.get("available_seats", Integer.class))
                .tourGuideId(row.get("tour_guide_id", Integer.class))
                .departureTime(row.get("departure_time", ZonedDateTime.class))
                .createdTime(row.get("created_time", ZonedDateTime.class))
                .build();
    }

    private boolean isBlacklisted(String key) {
        for (String blacklistedKey : BLACKLIST_TOUR_KEYS) {
            if (blacklistedKey.equals(key.trim())) {
                return true;
            }
        }
        return false;
    }

    private Field findSubTourField(SubTour subTour, String fieldName) {
        try {
            return SubTour.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            System.err.println("Field not found: " + fieldName);
            return null;
        }
    }

    private static Object convertValue(Field field, Object value) throws ParseException {
        Class<?> fieldType = field.getType();

        if (fieldType.equals(Date.class) && value instanceof String) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse((String) value);
        }

        if (fieldType.equals(ZonedDateTime.class)) {
            return Instant.parse((String) value).atZone(ZoneId.systemDefault());
        }

        if (fieldType.isEnum()) {
            return Enum.valueOf((Class<? extends Enum>) fieldType, String.valueOf(value));
        }

        return field.getType().cast(value); // No conversion needed for other types
    }
}
