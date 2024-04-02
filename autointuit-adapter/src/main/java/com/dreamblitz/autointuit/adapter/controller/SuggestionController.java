package com.dreamblitz.autointuit.adapter.controller;

import com.dreamblitz.autointuit.common.exception.CarLimitExceededException;
import com.dreamblitz.autointuit.domain.entity.CarMetadataEntity;
import com.dreamblitz.autointuit.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/api/car/suggest")
public class SuggestionController {

    @Autowired
    SuggestionService suggestionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Mono<ResponseEntity<List<CarMetadataEntity>>> suggestCar(String vehicleId ) throws CarLimitExceededException {
        return suggestionService.suggestCar(vehicleId ).map( object -> new ResponseEntity<>(object , HttpStatus.OK));
    }
}
