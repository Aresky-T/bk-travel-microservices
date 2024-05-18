package com.aresky.staffservice.service.position;

import com.aresky.staffservice.dto.request.PositionCreateForm;
import com.aresky.staffservice.dto.request.PositionUpdateForm;
import com.aresky.staffservice.dto.response.PositionDetails;
import com.aresky.staffservice.dto.response.PositionResponse;
import com.aresky.staffservice.exception.StaffException;
import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.repository.IPositionRepository;

import com.aresky.staffservice.utils.FieldUtils;
import io.r2dbc.spi.Row;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImp implements IPositionService {

    @Autowired
    private IPositionRepository positionRepository;

    @Autowired
    private DatabaseClient databaseClient;

    private final Map<String, String> FIELDS = new HashMap<>();
    private final Map<String, String> BLACKLIST = new HashMap<>();
    private final Map<String, String> UNIQUE_FIELDS = new HashMap<>();

    @PostConstruct
    public void init() {
        FIELDS.put("name", "Tên chức vụ");
        FIELDS.put("description", "Mô tả chức vụ");
        FIELDS.put("basicSalary", "Lương cơ bản");

        BLACKLIST.put("id", "id");
        UNIQUE_FIELDS.put("name", "name");
    }

    @Override
    public Mono<List<PositionResponse>> getAllPositionResponses() {
        return positionRepository.findAll()
                .switchIfEmpty(Mono.empty())
                .map(PositionResponse::toDTO)
                .collectList();
    }

    @Override
    public Mono<PositionDetails> getPositionDetailsBy(Integer positionId) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .map(PositionDetails::toDTO);
    }

    @Override
    public Mono<PositionDetails> getPositionDetailsBy(String positionName) {
        String query = "SELECT * FROM position WHERE name = :positionName";

        return databaseClient.sql(query)
                .bind("positionName", positionName)
                .map((row, metadata) -> mapRowToPosition(row))
                .one()
                .switchIfEmpty(Mono.empty())
                .map(PositionDetails::toDTO);
    }

    @Override
    public Mono<Void> createPosition(PositionCreateForm form) {
        return existsPositionByName(form.getName())
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_NAME_ALREADY_EXIST)))
                .flatMap(exists -> positionRepository.save(PositionCreateForm.toPosition(form)).then());
    }

    @Override
    public Mono<PositionResponse> updatePosition(Integer positionId, PositionUpdateForm form) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .flatMap(position -> {
                    position.setName(form.getName());
                    position.setDescription(form.getDescription());

                    return positionRepository.save(position)
                            .map(PositionResponse::toDTO);
                });
    }

    @Override
    public Mono<PositionResponse> updatePosition(Integer positionId, Map<String, Object> fields) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .flatMap(position -> positionRepository.save(updatePositionByFields(position, fields))
                        .map(PositionResponse::toDTO));
    }

    @Override
    public Mono<PositionResponse> updatePosition(Integer positionId, String key, Object value) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .flatMap(position -> {
                    if (checkKeyIsExistInBlackList(key)) {
                        throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
                    }

                    return checkFieldIsUnique(key)
                            .flatMap(isUniqueField -> {
                                if (isUniqueField) {
                                    return checkExistValueOfField(key, value)
                                            .flatMap(isExistValue -> {
                                                if (isExistValue) {
                                                    return Mono.error(new StaffException(
                                                            FIELDS.get(key) + " " + StaffException.ALREADY_EXIST));
                                                }

                                                return positionRepository.save(updatePosition(position, key, value))
                                                        .map(PositionResponse::toDTO);
                                            });
                                }

                                return positionRepository.save(updatePosition(position, key, value))
                                        .map(PositionResponse::toDTO);
                            });

                });
    }

    @Override
    public Mono<Void> deletePosition(Integer positionId) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .map(value -> positionRepository.delete(value))
                .then();
    }

    @Override
    public Mono<Boolean> existsPositionById(Integer positionId) {
        return positionRepository.existsById(positionId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_POSITION_ID)));
    }

    @Override
    public Mono<Boolean> existsPositionByName(String name) {
        return positionRepository.existsByName(name);
    }

    private Position mapRowToPosition(Row row) {
        Position position = new Position();
        position.setId(row.get("id", Integer.class));
        position.setName(row.get("name", String.class));
        position.setDescription(row.get("description", String.class));
        position.setBasicSalary(row.get("basic_salary", BigDecimal.class));
        return position;
    }

    private Position updatePositionByFields(Position position, Map<String, Object> fields) {
        fields.forEach((key, value) -> {
            Field field = FieldUtils.findField(position, key);

            if (checkKeyIsExistInBlackList(key)) {
                throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
            }

            FieldUtils.setFieldValue(position, field, value);
        });

        return position;
    }

    private boolean checkKeyIsExistInBlackList(String key) {
        return BLACKLIST.containsKey(key);
    }

    private Mono<Boolean> checkFieldIsUnique(String fieldName) {
        return Mono.just(UNIQUE_FIELDS.containsKey(fieldName));
    }

    private Position updatePosition(Position position, String key, Object value) {
        Field field = FieldUtils.findField(position, key);
        FieldUtils.setFieldValue(position, field, value);
        return position;
    }

    private Mono<Boolean> checkExistValueOfField(String fieldName, Object fieldValue) {
        String query = "Select count(*) as count from position where " + fieldName + " = :value";
        return databaseClient.sql(query)
                .bind("value", fieldValue)
                .map((row, metadata) -> row.get("count", Integer.class))
                .one()
                .map(count -> count == 1);
    }

}
