package com.aresky.staffservice.service.position;

import com.aresky.staffservice.dto.request.PositionCreateForm;
import com.aresky.staffservice.dto.request.PositionUpdateForm;
import com.aresky.staffservice.dto.response.PositionDetails;
import com.aresky.staffservice.dto.response.PositionResponse;
import com.aresky.staffservice.exception.StaffException;
import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.model.Staff;
import com.aresky.staffservice.repository.IDepartmentRepository;
import com.aresky.staffservice.repository.IPositionRepository;
import com.aresky.staffservice.service.staff.IStaffService;

import io.r2dbc.spi.Row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImp implements IPositionService {

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IPositionRepository positionRepository;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private DatabaseClient databaseClient;

    private final String[] blackList = {
            "id", "departmentId"
    };

    @Override
    public Mono<List<Position>> getAllPositionsBy(Integer departmentId) {
        return positionRepository
                .findAllByDepartmentId(departmentId)
                .switchIfEmpty(Mono.empty())
                .collectList();
    }

    @Override
    public Mono<List<PositionResponse>> getAllPositionResponses() {
        return positionRepository.findAll()
                .switchIfEmpty(Mono.empty())
                .map(PositionResponse::toDTO)
                .collectList();
    }

    @Override
    public Mono<List<PositionResponse>> getAllPositionResponses(Integer departmentId) {
        return departmentRepository.existsById(departmentId)
                .flatMap(isExistDept -> {
                    if (!isExistDept) {
                        throw new StaffException(StaffException.DEPARTMENT_DOES_NOT_EXIST);
                    }

                    return positionRepository
                            .findAllByDepartmentId(departmentId)
                            .switchIfEmpty(Mono.empty())
                            .map(PositionResponse::toDTO)
                            .collectList();
                });
    }

    @Override
    public Mono<PositionDetails> getPositionDetailsBy(Integer positionId) {
        Mono<List<Staff>> staffsMono = staffService.getAllStaffsByPosition(positionId);
        Mono<Position> positionMono = positionRepository.findById(positionId);

        return Mono.zip(staffsMono, positionMono)
                .flatMap(tuple -> {
                    List<Staff> staffs = tuple.getT1();
                    Position position = tuple.getT2();

                    if (position == null) {
                        throw new StaffException(StaffException.POSITION_DOES_NOT_EXIST);
                    }

                    return Mono.just(PositionDetails.toDTO(position, staffs));
                });
    }

    @Override
    public Mono<PositionDetails> getPositionDetailsBy(String positionName) {
        String query = "SELECT * FROM position WHERE name LIKE '%:positionName%'";

        return databaseClient.sql(query)
                .bind("positionName", positionName)
                .map((row, metadata) -> mapRowToPosition(row))
                .one()
                .switchIfEmpty(Mono.empty())
                .flatMap(position -> {
                    if (position.getHeadcount() == 0) {
                        return Mono.just(PositionDetails.toDTO(position));
                    }

                    return staffService.getAllStaffsByPosition(position.getId())
                            .map(staffs -> PositionDetails.toDTO(position, staffs));
                });
    }

    @Override
    public Mono<Void> createPosition(PositionCreateForm form) {
        return positionRepository.existsByName(form.getName())
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_ALREADY_EXIST)))
                .flatMap(value -> positionRepository
                        .save(PositionCreateForm.toPosition(form))
                        .then());
    }

    @Override
    public Mono<PositionResponse> updatePosition(Integer positionId, PositionUpdateForm form) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .flatMap(position -> {
                    position.setName(form.getName());
                    position.setDescription(form.getDescription());
                    position.setHeadcount(form.getHeadcount());

                    return positionRepository.save(position)
                            .map(PositionResponse::toDTO);
                });
    }

    @Override
    public Mono<PositionResponse> updatePosition(Integer positionId, Map<String, Object> fields) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .flatMap(position -> positionRepository.save(updateByFields(position, fields))
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

                    Field field = ReflectionUtils.findField(Position.class, key);
                    if (field == null) {
                        throw new StaffException("Thuộc tính " + key + " " + StaffException.DOES_NOT_EXIST);
                    }

                    field.setAccessible(true);
                    ReflectionUtils.setField(field, position, field.getType().cast(value));

                    return positionRepository.save(position).map(PositionResponse::toDTO);
                });
    }

    @Override
    public Mono<Void> deletePosition(Integer positionId) {
        return positionRepository.findById(positionId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.POSITION_DOES_NOT_EXIST)))
                .map(value -> positionRepository.delete(value))
                .then();
    }

    private Position mapRowToPosition(Row row) {
        Position position = new Position();
        position.setId(row.get("id", Integer.class));
        position.setDepartmentId(row.get("department_id", Integer.class));
        position.setName(row.get("name", String.class));
        position.setDescription(row.get("description", String.class));
        position.setHeadcount(row.get("headcount", Integer.class));
        return position;
    }

    private Position updateByFields(Position position, Map<String, Object> fields) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Position.class, key);

            if (checkKeyIsExistInBlackList(key)) {
                throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
            }

            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, position, value);
            } else {
                throw new StaffException("Thuộc tính " + key + " " + StaffException.DOES_NOT_EXIST);
            }
        });

        return position;
    }

    private boolean checkKeyIsExistInBlackList(String key) {
        return List.of(blackList).contains(key.trim());
    }

}
