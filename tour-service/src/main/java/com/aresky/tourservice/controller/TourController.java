package com.aresky.tourservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.tourservice.dto.request.SubTourCreateForm;
import com.aresky.tourservice.dto.request.TourCreateForm;
import com.aresky.tourservice.dto.request.TourFilter;
import com.aresky.tourservice.dto.response.SubTourAdminResponse;
import com.aresky.tourservice.dto.response.SubTourDetails;
import com.aresky.tourservice.dto.response.SubTourResponse;
import com.aresky.tourservice.dto.response.TourDetails;
import com.aresky.tourservice.service.ITourService;

@RestController
@RequestMapping("/api/v1/tours")
public class TourController {

    @Autowired
    private ITourService tourService;

    // POST - createTour - TourCreateForm
    @PostMapping
    public ResponseEntity<?> createTour(@ModelAttribute TourCreateForm form) {
        tourService.createTour(form);
        return ResponseEntity.ok("success");
    }

    // POST - createSubTour - SubTourCreateForm
    @PostMapping("/sub-tour")
    public ResponseEntity<?> createSubTour(
            @RequestParam Integer tourId,
            @RequestBody SubTourCreateForm form) {
        tourService.createSubTour(tourId, form);
        return ResponseEntity.ok("success");
    }

    // GET - getAllTours - Pageable
    @GetMapping
    public ResponseEntity<?> getAllTours(Pageable pageable) {
        return ResponseEntity.ok(tourService.findAllTourResponses(pageable));
    }

    // GET "/filter" - getAllTours - Pageable + TourFilter
    @GetMapping("/filter")
    public ResponseEntity<?> getAllTours(
            Pageable pageable,
            TourFilter filter) {
        return ResponseEntity.ok(tourService.findAllTourResponses(pageable, filter));
    }

    // GET "/sub-tour" - getAllSubTours - Pageable
    @GetMapping("/sub-tour")
    public ResponseEntity<Page<SubTourResponse>> getAllSubTours(Pageable pageable) {
        return ResponseEntity.ok(tourService.findAllSubTourResponses(pageable));
    }

    // GET "/sub-tour" - getAllSubTours - Pageable + Filter
    @GetMapping("/sub-tour/filter")
    public ResponseEntity<Page<SubTourResponse>> getAllSubTours(Pageable pageable, TourFilter filter) {
        return ResponseEntity.ok(tourService.findAllSubTourResponses(pageable, filter));
    }

    // GET "/id/{id}" - getTourById - id
    @GetMapping("/id/{id}")
    public ResponseEntity<TourDetails> getTourById(@PathVariable(name = "id") Integer tourId) {
        return ResponseEntity.ok(tourService.findTourDetailsById(tourId));
    }

    // GET "/sub-tour/id/{id}" - getSubTourById - subTourId
    @GetMapping("/sub-tour/id/{id}")
    public ResponseEntity<SubTourDetails> getSubTourById(@PathVariable(name = "id") Integer subTourId) {
        return ResponseEntity.ok(tourService.findSubTourDetailsById(subTourId));
    }

    @GetMapping("/sub-tour/tour-id/{id}")
    public ResponseEntity<List<SubTourAdminResponse>> getAllSubToursByTourId(
            @PathVariable(name = "id") Integer tourId) {
        return ResponseEntity.ok(tourService.findAllSubTourResponses(tourId));
    }

    // GET "/tour-code/{tourCode}" - getTourByTourCode - tourCode
    @GetMapping("/sub-tour/tour-code/{tourCode}")
    public ResponseEntity<SubTourDetails> getTourByTourCode(@PathVariable String tourCode) {
        return ResponseEntity.ok(tourService.findSubTourDetailsByTourCode(tourCode));
    }

    // GET "/sub-tour/latest" - getLatestSubTours - count
    @GetMapping("/sub-tour/latest")
    public ResponseEntity<?> getLatestSubTours(@RequestParam(name = "count") Integer count) {
        return ResponseEntity.ok(tourService.findLatestSubTours(count));
    }

    // PUT - updateTour - TourUpdateForm
    // @PutMapping
    // public ResponseEntity<?> updateTour(@RequestBody TourUpdateForm form) {
    // tourService.updateTourWithSubTour(form);
    // return (ResponseEntity.ok("success"));
    // }

    // PATCH - updateTour - id
    @PatchMapping
    public ResponseEntity<?> updateTour(
            @RequestParam(name = "tourId") Integer tourId,
            @RequestBody Map<String, Object> fields) {

        tourService.updateTour(tourId, fields);
        return (ResponseEntity.ok("success"));
    }

    // PATCH - updateSubTour - id
    @PatchMapping("/sub-tour")
    public ResponseEntity<?> updateSubTour(
            @RequestParam(name = "subTourId") Integer subTourId,
            @RequestBody Map<String, Object> fields) {
        tourService.updateSubTour(subTourId, fields);
        return ResponseEntity.ok("success");
    }

    // PATCH "/tour-id/{id}" - updateTour - id
    // @PatchMapping("/tour-with-sub-tour")
    // public ResponseEntity<?> updateTourWithSubTour(
    // @RequestParam(name = "tourId") Integer tourId,
    // @RequestParam(name = "subTourId") Integer subTourId,
    // @RequestBody Map<String, Object> fields) {

    // tourService.updateTourWithSubTour(tourId, subTourId, fields);
    // return ResponseEntity.ok("success");
    // }

    // DELETE - deleteTour - id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTour(
            @PathVariable(name = "id") Integer tourId) {
        tourService.deleteTourById(tourId);
        return ResponseEntity.ok("success");
    }

    // DELETE - deleteSubTour - id
    @DeleteMapping("/sub-tour/{id}")
    public ResponseEntity<?> deleteSubTour(
            @PathVariable(name = "id") Integer subTourId) {
        tourService.deleteSubTourById(subTourId);
        return ResponseEntity.ok("success");
    }
}
