package com.aresky.staffservice.controller;

import com.aresky.staffservice.dto.request.*;
import com.aresky.staffservice.dto.response.StaffResponse;
import com.aresky.staffservice.service.staff.IStaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/staffs")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    // GET - getAllStaffs(Pageable pageable)
    @GetMapping
    public Mono<ResponseEntity<Page<StaffResponse>>> getAllStaffs(Pageable pageable) {
        return Mono.empty();
    }

    // GET - getAllStaffs(Pageable pageable, StaffFilter filter)
    @GetMapping("/filter")
    public Mono<ResponseEntity<Page<StaffResponse>>> getAllStaffs(
            Pageable pageable,
            StaffFilter filter) {
        return Mono.empty();
    }

    // GET - getAllStaffs(Integer departmentId)
    @GetMapping("/department-id/{id}")
    public Mono<ResponseEntity<Page<StaffResponse>>> getAllStaffs(@PathVariable(name = "id") Integer departmentId) {
        return Mono.empty();
    }

    // GET - getAllStaffs(Integer departmentId, Integer positionId)
    @GetMapping("/by-department-and-position")
    public Mono<ResponseEntity<?>> getAllStaffs(
            @RequestParam Integer departmentId,
            @RequestParam Integer positionId) {
        return Mono.empty();
    }

    // GET - getAllStatus()
    @GetMapping("/status")
    public Mono<ResponseEntity<?>> getAllStatus() {
        return Mono.empty();
    }

    // GET - getDetailsStaffById(Integer id)
    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<?>> getDetailsStaffById(@PathVariable(name = "id") Integer id) {
        return Mono.empty();
    }

    // GET - getDetailsStaffByEmail(String email)
    @GetMapping("/email/{email}")
    public Mono<ResponseEntity<?>> getDetailsStaffByEmail(@PathVariable(name = "email") String email) {
        return Mono.empty();
    }

    // GET - getDetailsStaffByPhone(String phone)
    @GetMapping("/phone/{phone}")
    public Mono<ResponseEntity<?>> getDetailsStaffByPhone(@PathVariable(name = "phone") String phone) {
        return Mono.empty();
    }

    // GET - getDetailsStatus(Integer statusId)
    @GetMapping("/status/id/{id}")
    public Mono<ResponseEntity<?>> getDetailsStatus(@PathVariable(name = "id") Integer statusId) {
        return Mono.empty();
    }

    // POST - createStaff(StaffCreateForm form)
    @PostMapping
    public Mono<ResponseEntity<?>> createStaff(@RequestBody StaffCreateForm form) {
        return Mono.empty();
    }

    // POST - createStaffStatus(StatusCreateForm form)
    @PostMapping("/status")
    public Mono<ResponseEntity<?>> createStaffStatus(@RequestBody StatusCreateForm form) {
        return Mono.empty();
    }

    // PUT - updateStaff(StaffUpdateForm form)
    @PutMapping
    public Mono<ResponseEntity<?>> updateStaff(@RequestBody StaffUpdateForm form) {
        return Mono.empty();
    }

    // PATCH - updateStaff(Integer staffId, Map<String, Object> fields)
    @PatchMapping("/field")
    public Mono<ResponseEntity<?>> updateStaff(
            @RequestParam("id") Integer staffId,
            @RequestBody KeyValueRequest field) {
        return Mono.empty();
    }

    // PATCH - updateStaff(Integer staffId, Map<String, Object> fields)
    @PatchMapping("/fields")
    public Mono<ResponseEntity<?>> updateStaff(
            @RequestParam("id") Integer staffId,
            @RequestBody Map<String, Object> fields) {
        return Mono.empty();
    }

    // DELETE- deleteStaff(Integer staffId)
    @DeleteMapping
    public Mono<ResponseEntity<?>> deleteStaff(@RequestParam(name = "id") Integer staffId) {
        return Mono.empty();
    }
}
