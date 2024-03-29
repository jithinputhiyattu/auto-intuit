package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.domain.entity.CarEntity;
import reactor.core.publisher.Mono;

public interface ICarRepository {

    public Mono< CarEntity> getCarById(String id);
}
