package com.dreamblitz.autointuit.adapter.controller;


import com.dreamblitz.autointuit.common.exception.AutoIntuitUnhandledException;
import com.dreamblitz.autointuit.domain.entity.CarEntity;
import com.dreamblitz.autointuit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/auto-car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/by-id", method = RequestMethod.GET)
    public Mono<ResponseEntity<CarEntity>> getDistributionsStatus(String id) throws AutoIntuitUnhandledException {
       return carService.getCarById(id).map( text -> new ResponseEntity<>(text, HttpStatus.OK));
    }

}
