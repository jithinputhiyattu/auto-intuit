package com.dreamblitz.autointuit.adapter.controller;

import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import com.dreamblitz.autointuit.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/car/model")
public class CarModelController {

    @Autowired
    CarModelService carModelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Mono<ResponseEntity<CarModelEntity>> getCarModel(String vehicleId1 ) {
        return carModelService.catCarModelById(vehicleId1 ).map( object -> new ResponseEntity<>(object , HttpStatus.OK));
    }
}
