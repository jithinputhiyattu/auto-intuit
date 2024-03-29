package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.domain.entity.CarEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarRepository {

    Mono< CarEntity> getCarById(String id);

    Flux<CarEntity> getCarsById(String[] vehicleId);
}
