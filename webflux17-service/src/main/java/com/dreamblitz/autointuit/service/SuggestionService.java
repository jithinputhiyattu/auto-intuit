package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.common.exception.AutoIntuitErrorDetails;
import com.dreamblitz.autointuit.common.exception.NoSuchCarException;
import com.dreamblitz.autointuit.domain.entity.CarMetadataEntity;
import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {

    @Autowired
    SuggestionDomainService suggestionDomainService;

    public Mono<List<CarMetadataEntity>> suggestCar(String vehicleId) {
        return suggestionDomainService.getSimilarCars(vehicleId,10)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NoSuchCarException(vehicleId))));
    }
}
