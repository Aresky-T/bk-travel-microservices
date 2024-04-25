package com.aresky.touristattractionservice.service.touristattraction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.touristattractionservice.dto.request.FieldRequest;
import com.aresky.touristattractionservice.dto.request.SearchRequest;
import com.aresky.touristattractionservice.dto.request.TouristAttractionCreateForm;
import com.aresky.touristattractionservice.dto.request.TouristAttractionUpdateForm;
import com.aresky.touristattractionservice.dto.response.TouristAttractionDetails;
import com.aresky.touristattractionservice.dto.response.TouristAttractionResponse;
import com.aresky.touristattractionservice.entity.TouristAttraction;
import com.aresky.touristattractionservice.exception.TouristAttractionException;
import com.aresky.touristattractionservice.repository.ITouristAttractionRepository;
import com.aresky.touristattractionservice.utils.FieldUtils;

import jakarta.transaction.Transactional;

@Service
public class TouristAttractionServiceImp implements ITouristAttractionService {

    @Autowired
    private ITouristAttractionRepository touristAttractionRepository;

    @Override
    @Transactional
    public void create(TouristAttractionCreateForm form) {
        checkIsNotExist(form.getName());
        touristAttractionRepository.save(form.toEntity());
    }

    @Override
    public Page<TouristAttractionResponse> findAll(Pageable pageable) {
        Page<TouristAttraction> entities = touristAttractionRepository.findAll(pageable);
        return entities.map(TouristAttractionResponse::toDto);
    }

    @Override
    public List<TouristAttractionResponse> findAll(SearchRequest searchRequest) {
        Integer limit = searchRequest.getSize();
        Integer offset = (0 + searchRequest.getPage()) * limit;
        String search = searchRequest.getSearch();

        if (search == null || search.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<TouristAttraction> entities = touristAttractionRepository.findAllWithSearch(search, limit, offset);
        return entities.stream().map(TouristAttractionResponse::toDto).toList();
    }

    @Override
    public TouristAttractionDetails findById(Integer id) {
        return touristAttractionRepository.findById(id).map(TouristAttractionDetails::toDto).orElse(null);
    }

    @Override
    public TouristAttractionDetails findByName(String name) {
        return touristAttractionRepository.findByName(name).map(TouristAttractionDetails::toDto).orElse(null);
    }

    @Transactional
    @Override
    public TouristAttractionResponse update(Integer id, TouristAttractionUpdateForm form) {
        checkIsExist(id);
        String newName = form.getName().trim();
        String newImageUrl = form.getImageUrl().trim();
        String newIntroduction = form.getIntroduction();

        Optional<TouristAttraction> optional1 = touristAttractionRepository.findById(id);

        if (optional1.isEmpty()) {
            throw new TouristAttractionException("Không tìm thấy địa điểm du lịch với ID: " + id);
        }

        TouristAttraction ta1 = optional1.get();

        if (ta1.getName().equals(newName) && ta1.getImageUrl().equals(newImageUrl) && ta1.getIntroduction().equals(newIntroduction)) {
            throw new TouristAttractionException("Không có giá trị nào bị thay đổi!");
        }

        Optional<TouristAttraction> optional2 = touristAttractionRepository.findByName(newName);

        if (optional2.isPresent() && !optional2.get().getId().equals(ta1.getId())) {
            throw new TouristAttractionException("Tên địa điểm du lịch này đã tồn tại!");
        }

        ta1.setName(newName);
        ta1.setImageUrl(newImageUrl);
        ta1.setIntroduction(newIntroduction);
        return TouristAttractionResponse.toDto(touristAttractionRepository.save(ta1));
    }

    @Transactional
    @Override
    public TouristAttractionResponse update(Integer id, FieldRequest form) {
        checkIsExist(id);
        TouristAttraction ta = touristAttractionRepository.findById(id).get();

        String key = form.getKey();
        Object value = form.getValue();
        Field field = FieldUtils.findField(ta, key);

        if (key.equals("id")) {
            throw new TouristAttractionException("Can not update key " + key);
        }

        FieldUtils.setFieldValue(ta, field, value);
        return TouristAttractionResponse.toDto(touristAttractionRepository.save(ta));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        checkIsExist(id);
        touristAttractionRepository.deleteById(id);
    }

    public void checkIsExist(Integer id) {
        if (!touristAttractionRepository.existsById(id)) {
            throw new TouristAttractionException("Không tìm thấy địa điểm du lịch với ID: " + id);
        }
    }

    public void checkIsExist(String name) {
        if (!touristAttractionRepository.existsByName(name.trim())) {
            throw new TouristAttractionException("Invalid name!");
        }
    }

    public void checkIsNotExist(Integer id) {
        if (touristAttractionRepository.existsById(id)) {
            throw new TouristAttractionException("Địa điểm du lịch này đã tồn tại!");
        }
    }

    public void checkIsNotExist(String name) {
        boolean value = touristAttractionRepository.existsByName(name);
        if (Boolean.TRUE.equals(value)) {
            throw new TouristAttractionException("Tên địa điểm du lịch này đã tồn tại!");
        }
    }
}
