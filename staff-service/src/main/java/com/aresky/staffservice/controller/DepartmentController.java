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

import com.aresky.staffservice.dto.request.DepartmentCreateForm;
import com.aresky.staffservice.dto.request.DepartmentUpdateForm;
import com.aresky.staffservice.dto.request.KeyValueRequest;
import com.aresky.staffservice.service.department.IDepartmentService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    // GET - getAllDepartments()
    @GetMapping
    public Mono<ResponseEntity<?>> getAllDepartments() {
        return departmentService.getAllDepartments().map(ResponseEntity::ok);
    }

    // GET - getDetailsDepartmentBy(Integer departmentId)
    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<?>> getDetailsDepartmentBy(@PathVariable(name = "id") Integer departmentId) {
        return departmentService.getDetailsDepartmentBy(departmentId).map(ResponseEntity::ok);
    }

    // GET - getDetailsDepartmentBy(String departmentName)
    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<?>> getDetailsDepartmentBy(@PathVariable(name = "name") String departmentName) {
        return departmentService.getDetailsDepartmentBy(departmentName).map(ResponseEntity::ok);
    }

    // POST - createDepartment(DepartmentCreateForm form)
    @PostMapping
    public Mono<ResponseEntity<?>> createDepartment(@RequestBody DepartmentCreateForm form) {
        return departmentService.createDepartment(form).thenReturn(ResponseEntity.ok("success"));
    }

    // PUT - updateDepartment(DepartmentUpdateForm form)
    @PutMapping
    public Mono<ResponseEntity<?>> updateDepartment(
            @RequestParam(name = "id") Integer departmentId,
            @RequestBody DepartmentUpdateForm form) {
        return departmentService.updateDepartment(departmentId, form).map(ResponseEntity::ok);
    }

    // PATCH - updateDepartment(Integer departmentId, KeyValueRequest data)
    @PatchMapping("/field")
    public Mono<ResponseEntity<?>> updateDepartment(
            @RequestParam(name = "id") Integer departmentId,
            @RequestBody KeyValueRequest data) {
        return departmentService.updateDepartment(departmentId, data.getKey(), data.getValue()).map(ResponseEntity::ok);
    }

    // PATCH - updateDepartment(Integer departmentId, Map<String, Object> fields)
    @PatchMapping("/fields")
    public Mono<ResponseEntity<?>> updateDepartment(
            @RequestParam(name = "id") Integer departmentId,
            @RequestBody Map<String, Object> fields) {
        return departmentService.updateDepartment(departmentId, fields).map(ResponseEntity::ok);
    }

    @PatchMapping("/add-manager")
    public Mono<ResponseEntity<?>> updateDepartment(
            @RequestParam(name = "departmentId") Integer departmentId,
            @RequestParam(name = "staffId") Integer staffId
    ) {
        return departmentService.applyManager(departmentId, staffId).thenReturn(ResponseEntity.ok("success"));
    }

    // DELETE- deleteDepartment(Integer departmentId)
    @DeleteMapping
    public Mono<ResponseEntity<?>> deleteDepartment(@RequestParam(name = "id") Integer departmentId) {
        return departmentService.deleteDepartment(departmentId).thenReturn(ResponseEntity.ok("success"));
    }
}
