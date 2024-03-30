package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CarModelService {

    @Autowired
    CarModelDomainService carModelDomainService;

    public Mono<CarModelEntity> catCarModelById(String id) {
        return carModelDomainService.getCarModelById(id);
    }
}
