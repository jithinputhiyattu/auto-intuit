package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.domain.entity.CarMetadataEntity;
import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarMetadataRepository {

    Mono<CarMetadataEntity> getCarById(String id);
    Flux<CarMetadataEntity> findAll();
}
