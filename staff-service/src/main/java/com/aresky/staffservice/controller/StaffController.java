package com.aresky.staffservice.controller;

import com.aresky.staffservice.dto.request.*;
import com.aresky.staffservice.dto.response.StaffResponse;
import com.aresky.staffservice.service.staff.IStaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/staffs")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    // GET - getAllStaffs(Pageable pageable)
    @GetMapping
    public Mono<ResponseEntity<Page<StaffResponse>>> getAllStaffs(Pageable pageable) {
        return staffService.getAllStaffResponses(pageable).map(ResponseEntity::ok);
    }

    // GET - getAllStaffs(Pageable pageable, StaffFilter filter)
    @GetMapping("/filter")
    public Mono<ResponseEntity<Page<StaffResponse>>> getAllStaffs(
            Pageable pageable,
            StaffFilter filter) {
        return staffService.getAllStaffResponses(pageable, filter).map(ResponseEntity::ok);
    }

    // GET - getDetailsStaffById(Integer id)
    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<?>> getDetailsStaffById(@PathVariable(name = "id") Integer id) {
        return staffService.getDetailsStaffById(id).map(ResponseEntity::ok);
    }

    // GET - getDetailsStaffByEmail(String email)
    @GetMapping("/email/{email}")
    public Mono<ResponseEntity<?>> getDetailsStaffByEmail(@PathVariable(name = "email") String email) {
        return staffService.getDetailsStaffByEmail(email).map(ResponseEntity::ok);
    }

    // GET - getDetailsStaffByPhone(String phone)
    @GetMapping("/phone/{phone}")
    public Mono<ResponseEntity<?>> getDetailsStaffByPhone(@PathVariable(name = "phone") String phone) {
        return staffService.getDetailsStaffByPhone(phone).map(ResponseEntity::ok);
    }

    @GetMapping("/check-account/email")
    public Mono<ResponseEntity<?>> checkStaffByEmail(@RequestParam String email) {
        return staffService.checkAccountOfStaffByEmail(email).map(ResponseEntity::ok);
    }

    // POST - createStaff(StaffCreateForm form)
    @PostMapping
    public Mono<ResponseEntity<?>> createStaff(@RequestBody StaffCreateForm form) {
        return staffService.createStaff(form).thenReturn(ResponseEntity.ok("success"));
    }

    // PUT - updateStaff(StaffUpdateForm form)
    @PutMapping
    public Mono<ResponseEntity<?>> updateStaff(
            @RequestParam Integer staffId,
            @RequestBody StaffUpdateForm form) {
        return staffService.updateStaff(staffId, form).map(ResponseEntity::ok);
    }

    @PatchMapping("/bind-account")
    public Mono<ResponseEntity<?>> bindAccountToStaff(@RequestParam String email){
        return staffService.bindAccountToStaff(email).thenReturn(ResponseEntity.ok("success"));
    }

    // PATCH - updateStaff(Integer staffId, Map<String, Object> fields)
    @PatchMapping("/field")
    public Mono<ResponseEntity<?>> updateStaff(
            @RequestParam("id") Integer staffId,
            @RequestBody KeyValueRequest field) {
        return staffService.updateStaff(staffId, field.getKey(), field.getValue()).map(ResponseEntity::ok);
    }

    // PATCH - updateStaff(Integer staffId, Map<String, Object> fields)
    @PatchMapping("/fields")
    public Mono<ResponseEntity<?>> updateStaff(
            @RequestParam("id") Integer staffId,
            @RequestBody Map<String, Object> fields) {
        return staffService.updateStaff(staffId, fields).map(ResponseEntity::ok);
    }

    @PatchMapping("/layoff")
    public Mono<ResponseEntity<?>> layoffStaff(@RequestParam("staffId") Integer staffId) {
        return staffService.layoffStaff(staffId).thenReturn(ResponseEntity.ok("success"));
    }

    // DELETE- deleteStaff(Integer staffId)
    @Transactional
    @DeleteMapping
    public Mono<ResponseEntity<?>> deleteStaff(@RequestParam(name = "id") Integer staffId) {
        return staffService.deleteStaff(staffId).thenReturn(ResponseEntity.ok("success"));
    }

    @DeleteMapping("/bind-account")
    public Mono<ResponseEntity<?>> unbindAccountToStaff(@RequestParam String email){
        return staffService.unbindAccountToStaff(email).thenReturn(ResponseEntity.ok("success"));
    }
}
