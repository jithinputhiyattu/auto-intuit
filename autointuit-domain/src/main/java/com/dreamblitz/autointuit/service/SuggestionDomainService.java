package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import com.dreamblitz.autointuit.repository.ICarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionDomainService {
    @Autowired
    ICarModelRepository iCarModelRepository;


    public  Mono<List<CarModelEntity>> getSimilarCars(String modelId, int limit ) {
        return iCarModelRepository.getCarById(modelId).flatMap(carModel ->
            iCarModelRepository.findAll().collectList().flatMap(models ->
                Mono.just(models.stream().filter(i -> !i.getId().equals(carModel.getId()))
                   .map(item -> item.createScore(carModel)).sorted()
                       .limit(limit).collect(Collectors.toList()))));
    }

}
