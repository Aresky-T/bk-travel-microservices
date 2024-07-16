package com.aresky.tourservice.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.tourservice.dto.request.SubTourCreateForm;
import com.aresky.tourservice.dto.request.TourCreateForm;
import com.aresky.tourservice.dto.request.TourFilter;
import com.aresky.tourservice.dto.response.SubTourAdminResponse;
import com.aresky.tourservice.dto.response.SubTourDetails;
import com.aresky.tourservice.dto.response.SubTourResponse;
import com.aresky.tourservice.dto.response.TourDetails;
import com.aresky.tourservice.dto.response.TourResponse;
import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.entity.Tour;
import com.aresky.tourservice.exception.TourException;
import com.aresky.tourservice.repository.SubTourRepository;
import com.aresky.tourservice.repository.TourRepository;
import com.aresky.tourservice.service.cloudinary.ICloudinaryService;
import com.aresky.tourservice.specification.SubTourSpecification;
import com.aresky.tourservice.specification.TourSpecification;
import com.aresky.tourservice.utils.FieldUtils;

import jakarta.transaction.Transactional;

@Service
public class TourServiceProvider implements ITourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private SubTourRepository subTourRepository;

    @Autowired
    private ICloudinaryService cloudinaryService;

    private static final String[] BLACKLIST_TOUR_KEYS = { "id", "tourCode", "tourId" };

    @Transactional
    @Override
    public void createTour(TourCreateForm form) {
        if (existsTourByTitle(form.getTitle())) {
            throw new TourException("Tiêu đề này đã bị trùng với Tour khác!");
        }

        try {
            String image1 = (String) cloudinaryService.uploadImage(form.getImage1()).get("secure_url");
            String image2 = (String) cloudinaryService.uploadImage(form.getImage2()).get("secure_url");
            String image3 = (String) cloudinaryService.uploadImage(form.getImage3()).get("secure_url");
            String image4 = (String) cloudinaryService.uploadImage(form.getImage4()).get("secure_url");

            Tour tour = TourCreateForm.toEntity(form);
            tour.setImage1(image1);
            tour.setImage2(image2);
            tour.setImage3(image3);
            tour.setImage4(image4);
            tourRepository.save(tour);

        } catch (IOException e) {
            throw new TourException("Upload file ảnh không thành công!");
        }
    }

    @Transactional
    @Override
    public void createSubTour(Integer tourId, SubTourCreateForm form) {
        Tour tour = findTourById(tourId);

        if (tour == null) {
            throw new TourException("Tour id không hợp lệ!");
        }

        if (existsSubTourByTitle(form.getTitle())) {
            throw new TourException("Tiêu đề này đã bị trùng với Tour phụ khác!");
        }

        SubTour subTour = SubTourCreateForm.toEntity(form);
        subTour.setTour(tour);
        subTour.setAvailableSeats(tour.getTotalSeats());

        if (save(subTour) != null) {
            updateTotalSubToursForTour(tour);
        }
    }

    @Override
    public Page<TourResponse> findAllTourResponses(Pageable pageable) {
        Page<Tour> tourPage = tourRepository.findAll(pageable);
        if (tourPage.isEmpty()) {
            return Page.empty();
        }

        return tourPage.map(TourResponse::toDTO);
    }

    @Override
    public Page<TourResponse> findAllTourResponses(Pageable pageable, TourFilter tourFilter) {
        return tourRepository.findAll(TourSpecification.buildWhere(tourFilter), pageable)
                .map(TourResponse::toDTO);
    }

    @Override
    public TourDetails findTourDetailsById(Integer id) {
        if (!existsTourById(id)) {
            throw new TourException("Invalid id!");
        }

        return TourDetails.toDTO(findTourById(id));
    }

    @Override
    public Page<SubTourResponse> findAllSubTourResponses(Pageable pageable) {
        Page<SubTour> subTourPage = subTourRepository.findAll(pageable);
        if (subTourPage.isEmpty()) {
            return Page.empty();
        }
        return subTourPage.map(subTour -> SubTourResponse.toDTO(subTour, subTour.getTour()));
    }

    @Override
    public Page<SubTourResponse> findAllSubTourResponses(Pageable pageable, TourFilter tourFilter) {
        return subTourRepository.findAll(SubTourSpecification.buildWhere(tourFilter), pageable)
                .map(subTour -> SubTourResponse.toDTO(subTour, subTour.getTour()));
    }

    @Override
    public List<SubTourAdminResponse> findAllSubTourResponses(Integer tourId) {
        if (!existsTourById(tourId)) {
            throw new TourException("Invalid tourId");
        }

        return subTourRepository.findAllByTourId(tourId)
                .stream().map(SubTourAdminResponse::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SubTourResponse> findLatestSubTours(Integer count) {
        return subTourRepository.findAllOrderByCreatedTime(count)
                .stream()
                .map(subTour -> SubTourResponse.toDTO(subTour, subTour.getTour()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateTour(Integer tourId, Map<String, Object> fields) {
        Tour tour = findTourById(tourId);
        if (tour == null) {
            throw new TourException("Invalid tourId");
        }
        tourRepository.save(updateTourByFields(tour, fields));
    }

    @Override
    @Transactional
    public void updateSubTour(Integer subTourId, Map<String, Object> fields) {
        SubTour subTour = findSubTourById(subTourId);

        if (subTour == null) {
            throw new TourException("Invalid subTourId");
        }

        for (Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (isBlacklisted(key)) {
                continue;
            }

            Field field = FieldUtils.findField(subTour, key);
            FieldUtils.setFieldValue(subTour, field, value);
        }

        validateSubTour(subTour);
        subTourRepository.save(subTour);
    }

    @Override
    public SubTourDetails findSubTourDetailsByTourCode(String tourCode) {
        Optional<SubTour> optional = subTourRepository.findByTourCode(tourCode);

        if (optional.isEmpty()) {
            throw new TourException("This subTourId doesn't exist!");
        }

        SubTour subTour = optional.get();
        Tour tour = subTour.getTour();
        return SubTourDetails.toDTO(subTour, tour);
    }

    @Override
    public SubTourDetails findSubTourDetailsById(Integer subTourId) {
        Optional<SubTour> optional = subTourRepository.findById(subTourId);

        if (optional.isEmpty()) {
            throw new TourException("This subTourId doesn't exist!");
        }

        SubTour subTour = optional.get();
        Tour tour = subTour.getTour();
        return SubTourDetails.toDTO(subTour, tour);
    }

    @Override
    public void deleteTourById(Integer id) {
        if (existsTourById(id)) {
            tourRepository.deleteById(id);
        } else {
            throw new TourException("Invalid id!");
        }
    }

    @Transactional
    @Override
    public void deleteSubTourById(Integer id) {
        if (existsSubTourById(id)) {
            subTourRepository.deleteById(id);
        } else {
            throw new TourException("Invalid tourCode!");
        }
    }

    @Transactional
    @Override
    public void deleteSubTourByTourCode(String tourCode) {
        if (existsSubTourByTourCode(tourCode)) {
            subTourRepository.deleteByTourCode(tourCode);
        } else {
            throw new TourException("Invalid tourCode!");
        }
    }

    @Transactional
    @Override
    public void deleteAllSubToursByTourId(Integer tourId) {
        if (existsTourById(tourId)) {
            subTourRepository.deleteAllByTourId(tourId);
        } else {
            throw new TourException("Invalid tourId!");
        }
    }

    @Override
    public Boolean existsTourById(Integer id) {
        return tourRepository.existsById(id);
    }

    @Override
    public Boolean existsTourByTitle(String title) {
        return tourRepository.existsByTitle(title);
    }

    @Override
    public Boolean existsSubTourById(Integer id) {
        return subTourRepository.existsById(id);
    }

    @Override
    public Boolean existsSubTourByTitle(String title) {
        return subTourRepository.existsByTitle(title);
    }

    @Override
    public Boolean existsSubTourByTourCode(String tourCode) {
        return subTourRepository.existsByTourCode(tourCode);
    }

    private Tour findTourById(Integer tourId) {
        return tourRepository.findById(tourId).orElse(null);
    }

    private SubTour findSubTourById(Integer subTourId) {
        return subTourRepository.findById(subTourId).orElse(null);
    }

    @Transactional
    private Tour save(Tour entity) {
        return tourRepository.save(entity);
    };

    @Transactional
    private SubTour save(SubTour entity) {
        return subTourRepository.save(entity);
    }

    @Transactional
    private void updateTotalSubToursForTour(Tour tour) {
        List<SubTour> subTours = tour.getSubTours();
        tour.setTotalSubTours(subTours.size());
        tourRepository.save(tour);
    }

    private Tour updateTourByFields(Tour tour, Map<String, Object> fields) {
        for (Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (isBlacklisted(key)) {
                continue;
            }

            Field field = FieldUtils.findField(tour, key);
            FieldUtils.setFieldValue(tour, field, value);
        }

        return tour;
    }

    private boolean isBlacklisted(String key) {
        for (String blacklistedKey : BLACKLIST_TOUR_KEYS) {
            if (blacklistedKey.equals(key.trim())) {
                return true;
            }
        }
        return false;
    }

    private void validateSubTour(SubTour subTour) {
        Tour tour = subTour.getTour();
        Integer totalSeats = tour.getTotalSeats();

        if (subTour.getAvailableSeats() > totalSeats) {
            throw new TourException("Số chỗ còn trống không được vượt quá " + totalSeats);
        }

        if (subTour.getDepartureTime().before(new Date(System.currentTimeMillis()))) {
            throw new TourException("Thời gian khởi hành không được ở trong quá khứ!");
        }
    }
}
