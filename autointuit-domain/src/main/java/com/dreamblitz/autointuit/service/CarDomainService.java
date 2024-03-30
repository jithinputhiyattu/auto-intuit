package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarVariant;
import com.dreamblitz.autointuit.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarDomainService {

   @Autowired
   private ICarRepository iCarRepository;

    public Mono<CarVariant> getCarVariantById(String id) {
     return iCarRepository.getCarById(id);
    }

    public Flux<CarVariant> getCarVariantById(String[] vehicleId) {
        return iCarRepository.getCarsById(vehicleId);
    }

}
