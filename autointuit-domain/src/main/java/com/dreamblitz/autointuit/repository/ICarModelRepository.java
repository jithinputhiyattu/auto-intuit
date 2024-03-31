package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarModelRepository {
    Mono<CarModelEntity> getCarById(String id);
    Flux<CarModelEntity> findAll();
}
