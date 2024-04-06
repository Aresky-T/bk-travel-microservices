package com.aresky.staffservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.staffservice.dto.request.KeyValueRequest;
import com.aresky.staffservice.dto.request.PositionCreateForm;
import com.aresky.staffservice.dto.request.PositionUpdateForm;
import com.aresky.staffservice.service.position.IPositionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    // GET - getAllPositions()
    @GetMapping
    public Mono<ResponseEntity<?>> getAllPositions() {
        return positionService.getAllPositionResponses().map(ResponseEntity::ok);
    }

    // GET - getAllPositionsBy(Integer departmentId)
    @GetMapping("/department-id/{id}")
    public Mono<ResponseEntity<?>> getAllPositionsBy(@PathVariable(name = "id") Integer departmentId) {
        return positionService.getAllPositionResponses(departmentId).map(ResponseEntity::ok);
    }

    // GET - getDetailsPositionBy(Integer positionId)
    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<?>> getDetailsPositionBy(@PathVariable(name = "id") Integer positionId) {
        return positionService.getPositionDetailsBy(positionId).map(ResponseEntity::ok);
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<?>> getDetailsPositionBy(@PathVariable(name = "name") String positionName) {
        return positionService.getPositionDetailsBy(positionName).map(ResponseEntity::ok);
    }

    // POST - createPosition(PositionCreateForm form)
    @PostMapping
    public Mono<ResponseEntity<?>> createPosition(@RequestBody PositionCreateForm form) {
        return positionService.createPosition(form).thenReturn(ResponseEntity.ok("success"));
    }

    // PUT - updatePosition(PositionUpdateForm form)
    @PutMapping
    public Mono<ResponseEntity<?>> updatePosition(
            @RequestParam(name = "id") Integer positionId,
            @RequestBody PositionUpdateForm form) {
        return positionService.updatePosition(positionId, form).map(ResponseEntity::ok);
    }

    // PATCH - updatePosition(Integer positionId, KeyValueRequest field)
    @PatchMapping("/field")
    public Mono<ResponseEntity<?>> updatePosition(
            @RequestParam(name = "id") Integer positionId,
            @RequestBody KeyValueRequest field) {
        return positionService.updatePosition(positionId, field.getKey(), field.getValue()).map(ResponseEntity::ok);
    }

    // PATCH - updatePosition(Integer positionId, Map<String, Object> fields)
    @PatchMapping("/fields")
    public Mono<ResponseEntity<?>> updatePosition(
            @RequestParam(name = "id") Integer positionId,
            @RequestBody Map<String, Object> fields) {
        return positionService.updatePosition(positionId, fields).map(ResponseEntity::ok);
    }

    // DELETE- deletePosition(Integer staffId)
    @DeleteMapping
    public Mono<ResponseEntity<?>> deletePosition(@RequestParam(name = "id") Integer positionId) {
        return positionService.deletePosition(positionId).thenReturn(ResponseEntity.ok("success"));
    }
}
