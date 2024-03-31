package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarMetadataEntity;
import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CarModelService {

    @Autowired
    CarModelDomainService carModelDomainService;

    @Autowired
    SuggestionDomainService suggestionDomainService;

    public Mono<CarModelEntity> catCarModelById(String id) {
        return this.updatePopularity(id).flatMap(item -> carModelDomainService.getCarModelById(item.getCarId()));
    }

    private Mono<CarMetadataEntity> updatePopularity(String id) {
       return suggestionDomainService.updatePopularity(id);
    }
}
