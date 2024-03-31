package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.common.exception.NoSuchCarException;
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

    public Mono<CarModelEntity> catCarModelById(String vehicleId) {
        return this.updatePopularity(vehicleId)
            .flatMap(item -> carModelDomainService.getCarModelById(item.getCarId()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NoSuchCarException(vehicleId))));
    }

    private Mono<CarMetadataEntity> updatePopularity(String id) {
       return suggestionDomainService.updatePopularity(id);
    }
}
