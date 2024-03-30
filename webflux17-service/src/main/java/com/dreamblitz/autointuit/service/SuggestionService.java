package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {

    @Autowired
    SuggestionDomainService suggestionDomainService;

    public Mono<List<CarModelEntity>> suggestCar(String vehicleId) {
        return suggestionDomainService.getSimilarCars(vehicleId,10);
    }
}
