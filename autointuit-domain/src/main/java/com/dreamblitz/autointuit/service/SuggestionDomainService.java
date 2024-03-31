package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarMetadataEntity;
import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import com.dreamblitz.autointuit.repository.ICarMetadataRepository;
import com.dreamblitz.autointuit.repository.ICarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionDomainService {

    @Autowired
    ICarMetadataRepository iCarMetadataRepository;


    public  Mono<List<CarMetadataEntity>> getSimilarCars(String modelId, int limit ) {
        return iCarMetadataRepository.getCarById(modelId).flatMap(carModel ->
                iCarMetadataRepository.findAll().collectList().flatMap(models ->
                // First filter via segment
                Mono.just(models.stream().filter(i-> i.getSegment().equals(carModel.getSegment()))
                    .filter(i -> !i.getCarId().equals(carModel.getCarId()))
                     // Then sort by popularity
                    .sorted().limit(limit).collect(Collectors.toList()))));
    }


    public Mono<CarMetadataEntity>  updatePopularity(String modelId) {
        return iCarMetadataRepository.getCarById(modelId).map(CarMetadataEntity::updatePopularity);
    }
}
