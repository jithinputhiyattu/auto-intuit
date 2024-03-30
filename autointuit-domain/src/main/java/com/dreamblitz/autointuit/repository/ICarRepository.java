package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.domain.entity.CarVariant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarRepository {

    Mono<CarVariant> getCarById(String id);

    Flux<CarVariant> getCarsById(String[] vehicleId);
}
