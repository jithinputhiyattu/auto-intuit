package com.dreamblitz.autointuit.service;


import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import com.dreamblitz.autointuit.repository.ICarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CarModelDomainService {

    @Autowired
    ICarModelRepository iCarModelRepository;


    public Mono<CarModelEntity> getCarModelById(String id) {
        return iCarModelRepository.getCarById(id);
    }
}
