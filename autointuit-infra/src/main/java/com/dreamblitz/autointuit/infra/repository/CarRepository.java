package com.dreamblitz.autointuit.infra.repository;

import com.dreamblitz.autointuit.domain.entity.CarEntity;
import com.dreamblitz.autointuit.repository.ICarRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Getter
public class CarRepository implements ICarRepository {

    @Override
    public Mono<CarEntity> getCarById(String id) {
        return null;
    }
}
