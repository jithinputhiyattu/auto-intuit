package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarVariant;
import com.dreamblitz.autointuit.domain.entity.Hideable;
import com.dreamblitz.autointuit.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CarVariantDomainService {

   @Autowired
   private ICarRepository iCarRepository;

    public Mono<CarVariant> getCarVariantById(String id) {
     return iCarRepository.getCarById(id);
    }

    public Flux<CarVariant> getCar3VariantById(String[] vehicleId,  Boolean hideCommon) {
        return iCarRepository.getCarsById(vehicleId).collectList().flatMap(list -> {
            if(hideCommon && list.size() > 1) {
                list.get(0).hideCommonFromList(list.toArray());
            }
            return list.size() > 0 ? Mono.just(list) : Mono.empty();
        }).flatMapMany(Flux::fromIterable);
    }

    public Flux<CarVariant> getCarVariantById(String[] vehicleId) {
        return iCarRepository.getCarsById(vehicleId);
    }

}
