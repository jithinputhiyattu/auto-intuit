package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarEntity;
import com.dreamblitz.autointuit.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarDomainService {

   @Autowired
   private ICarRepository iCarRepository;

    public Mono<CarEntity> getCarById(String id) {
     return iCarRepository.getCarById(id);
    }

    public Flux<CarEntity> getCarById(String[] vehicleId) {

        return iCarRepository.getCarsById(vehicleId);
    }
}
