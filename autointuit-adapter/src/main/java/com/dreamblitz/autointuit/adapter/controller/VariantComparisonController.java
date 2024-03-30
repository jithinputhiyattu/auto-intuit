package com.dreamblitz.autointuit.adapter.controller;

import com.dreamblitz.autointuit.domain.entity.CarVariant;
import com.dreamblitz.autointuit.service.CarService;
import com.dreamblitz.autointuit.service.VariantComparisonServices;
import com.dreamblitz.autointuit.common.exception.AutoIntuitUnhandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/car/variant")
public class VariantComparisonController {
    @Autowired
    private VariantComparisonServices comparisonServices;

    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public Mono<ResponseEntity<Map<String, LinkedHashMap>>> compareNCars(String[] vehicleId, Boolean hideCommon ) throws AutoIntuitUnhandledException {
        return comparisonServices.compareCars(vehicleId,hideCommon ).map( object -> new ResponseEntity<>(object , HttpStatus.OK));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Mono<ResponseEntity<CarVariant>>  getVariant(String vehicleId ) throws AutoIntuitUnhandledException {
        return comparisonServices.getCarById(vehicleId).map( text -> new ResponseEntity<>(text, HttpStatus.OK));
    }

}
