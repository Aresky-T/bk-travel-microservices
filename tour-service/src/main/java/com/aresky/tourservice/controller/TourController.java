package com.aresky.tourservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.aresky.tourservice.dto.request.SubTourCreateForm;
import com.aresky.tourservice.dto.request.TourCreateForm;
import com.aresky.tourservice.dto.request.TourFilter;
import com.aresky.tourservice.dto.request.TourUpdateForm;
import com.aresky.tourservice.service.ITourService;

import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/tours")
public class TourController {

    @Autowired
    private ITourService tourService;

    // POST - createTour - TourCreateForm
    @PostMapping
    public Mono<ResponseEntity<?>> createTour(@RequestBody TourCreateForm form) {
        return tourService.createTour(form).thenReturn(ResponseEntity.ok("success"));
    }

    // POST - createSubTour - SubTourCreateForm
    @PostMapping("/sub-tour")
    public Mono<ResponseEntity<?>> createSubTour(@RequestBody SubTourCreateForm form) {
        return tourService.createSubTour(form).thenReturn(ResponseEntity.ok("success"));
    }

    // GET - getAllTours - Pageable
    @GetMapping
    public Mono<ResponseEntity<?>> getAllTours(Pageable pageable) {
        return tourService.findAllTours(pageable).map(ResponseEntity::ok);
    }

    // GET "/filter" - getAllTours - Pageable + TourFilter
    @GetMapping("/filter")
    public Mono<ResponseEntity<?>> getAllTours(
            Pageable pageable,
            TourFilter filter) {
        return tourService.findAllTours(pageable, filter).map(ResponseEntity::ok);
    }

    // GET "/sub-tour" - getAllSubTours - Pageable
    @GetMapping("/sub-tour")
    public Mono<ResponseEntity<?>> getAllSubTours(Pageable pageable) {
        return tourService.findAllSubTours(pageable).map(ResponseEntity::ok);
    }

    // GET "/sub-tour" - getAllSubTours - Pageable + Filter
    @GetMapping("/sub-tour/filter")
    public Mono<ResponseEntity<?>> getAllSubTours(Pageable pageable, TourFilter filter) {
        return tourService.findAllSubTours(pageable, filter).map(ResponseEntity::ok);
    }

    // GET "/id/{id}" - getTourById - id
    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<?>> getTourById(@PathVariable(name = "id") Integer tourId) {
        return tourService.findTourById(tourId)
                .map(ResponseEntity::ok);
    }

    // GET "/sub-tour/id/{id}" - getSubTourById - subTourId
    @GetMapping("/sub-tour/id/{id}")
    public Mono<ResponseEntity<?>> getSubTourById(@PathVariable(name = "id") Integer subTourId) {
        return tourService.findSubTourById(subTourId).map(ResponseEntity::ok);
    }

    // GET "/tour-code/{tourCode}" - getTourByTourCode - tourCode
    @GetMapping("/sub-tour/tour-code/{tourCode}")
    public Mono<ResponseEntity<?>> getTourByTourCode(@PathVariable String tourCode) {
        return tourService.findSubTourByTourCode(tourCode).map(ResponseEntity::ok);
    }

    // GET "/get-latest-tours/{count}" - getLatestTours - count
    @GetMapping("/latest")
    public Mono<ResponseEntity<?>> getLatestTours(@RequestParam(name = "count") Integer count) {
        return tourService.findLatestTours(count).map(ResponseEntity::ok);
    }

    // GET "/sub-tour/latest" - getLatestSubTours - count
    @GetMapping("/sub-tour/latest")
    public Mono<ResponseEntity<?>> getLatestSubTours(@RequestParam(name = "count") Integer count) {
        return tourService.findLatestSubTours(count).map(ResponseEntity::ok);
    }

    // PUT - updateTour - TourUpdateForm
    @PutMapping
    public Mono<ResponseEntity<?>> updateTour(@RequestBody TourUpdateForm form) {
        return tourService.updateTourWithSubTour(form).thenReturn(ResponseEntity.ok("success"));
    }

    // PATCH - updateTour - id
    @PatchMapping
    public Mono<ResponseEntity<?>> updateTour(
            @RequestParam(name = "tourId") Integer tourId,
            @RequestBody Map<String, Object> fields) {

        return tourService.updateOnlyTour(tourId, fields)
                .thenReturn(ResponseEntity.ok("success"));
    }

    // PATCH - updateSubTour - id
    @PatchMapping("/sub-tour")
    public Mono<ResponseEntity<?>> updateSubTour(
            @RequestParam(name = "subTourId") Integer subTourId,
            @RequestBody Map<String, Object> fields) {

        return tourService.updateOnlySubTour(subTourId, fields)
                .thenReturn(ResponseEntity.ok("success"));
    }

    // PATCH "/tour-id/{id}" - updateTour - id
    @PatchMapping("/tour-with-sub-tour")
    public Mono<ResponseEntity<?>> updateTourWithSubTour(
            @RequestParam(name = "tourId") Integer tourId,
            @RequestParam(name = "subTourId") Integer subTourId,
            @RequestBody Map<String, Object> fields) {

        return tourService.updateTourWithSubTour(tourId, subTourId, fields)
                .thenReturn(ResponseEntity.ok("success"));
    }

    // DELETE - deleteTour - id
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deleteTour(
            @PathVariable(name = "id") Integer tourId) {
        return tourService.deleteTourById(tourId).thenReturn(ResponseEntity.ok("success"));
    }

    // DELETE - deleteSubTour - id
    @DeleteMapping("/sub-tour/{id}")
    public Mono<ResponseEntity<?>> deleteSubTour(
            @PathVariable(name = "id") Integer subTourId) {
        return tourService.deleteSubTourById(subTourId).thenReturn(ResponseEntity.ok("success"));
    }
}
