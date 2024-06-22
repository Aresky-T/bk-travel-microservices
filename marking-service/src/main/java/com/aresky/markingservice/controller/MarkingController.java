package com.aresky.markingservice.controller;

import com.aresky.markingservice.dto.request.MarkedTourRequest;
import com.aresky.markingservice.service.marking.IMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/marking")
public class MarkingController {
    @Autowired
    private IMarkingService markingService;

    @GetMapping("/check")
    public Mono<ResponseEntity<Boolean>> checkMarkedTour(
            @RequestParam Integer accountId,
            @RequestParam Integer subTourId
    ){
        return markingService.checkMarkedTour(accountId, subTourId).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<?>> markTour(@RequestBody MarkedTourRequest request){
        return markingService.markTour(request).thenReturn(ResponseEntity.ok("success"));
    }

    @DeleteMapping
    public Mono<ResponseEntity<?>> unmarkTour(@RequestParam Integer markedTourId){
        return markingService.unmarkTour(markedTourId).thenReturn(ResponseEntity.ok("success"));
    }
}
