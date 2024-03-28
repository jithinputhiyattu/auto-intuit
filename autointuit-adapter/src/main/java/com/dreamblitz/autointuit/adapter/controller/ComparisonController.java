package com.dreamblitz.autointuit.adapter.controller;

import com.dreamblitz.autointuit.service.ComparisonServices;
import com.dreamblitz.autointuit.common.exception.AutoIntuitUnhandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/auto-comparison")
public class ComparisonController {
    @Autowired
    private ComparisonServices comparisonServices;

    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public Mono<ResponseEntity<String>> compareCars(String vehicleId1, String vehicleId2 ) throws AutoIntuitUnhandledException {
        return comparisonServices.sampleResponse().map( text -> new ResponseEntity<>(text + " Jai", HttpStatus.OK));
    }
}
