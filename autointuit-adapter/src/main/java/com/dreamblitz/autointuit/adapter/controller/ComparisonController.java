package com.dreamblitz.autointuit.adapter.controller;

import com.dreamblitz.autointuit.service.ComparisonServices;
import com.dreamblitz.autointuit.common.exception.AutoIntuitUnhandledException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/auto-comparison")
public class ComparisonController {
    @Autowired
    private ComparisonServices comparisonServices;

    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    @JsonIgnore
    public Mono<ResponseEntity<Map<String, LinkedHashMap>>> compareCars(String vehicleId1, String vehicleId2, Boolean hideCommon ) throws AutoIntuitUnhandledException {
        return comparisonServices.compareCars(vehicleId1,vehicleId2,hideCommon ).map( object -> new ResponseEntity<>(object , HttpStatus.OK));
    }
}
