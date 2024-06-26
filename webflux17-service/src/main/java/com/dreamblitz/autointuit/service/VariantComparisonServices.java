package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.common.exception.CarLimitExceededException;
import com.dreamblitz.autointuit.common.exception.NoSuchCarException;
import com.dreamblitz.autointuit.domain.entity.CarVariant;
import com.dreamblitz.autointuit.service.mapper.CarMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class VariantComparisonServices {

    @Autowired
    CarVariantDomainService carDomainService;

    @Autowired
    CarMapper carMapper;

    /*
    public Mono<Map<String, LinkedHashMap>> compareCars(String[] array, Boolean hideCommon) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return carDomainService.getCarVariantById(array).flatMap(entity -> {
            try {
                String jsonString = mapper.writeValueAsString(entity);
                System.out.println(jsonString);
                return Mono.just(carMapper.convertToJavaObject(jsonString));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Mono.error(e);
            }
        }).collectMap((item) -> ((LinkedHashMap) item).get("id").toString(), item -> (LinkedHashMap)item)
                .map(map ->  hideCommon ?  carMapper.hideCommon(map) : map).onErrorResume(this::errorHandler);
    }*/


    public Mono<Map<String, LinkedHashMap>> compare3Cars(String[] array, Boolean hideCommon) {

        if(array.length > 3) { // should read from configuration
            return Mono.error(new CarLimitExceededException(array.length));
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return carDomainService.getCar3VariantById(array, hideCommon).flatMap(entity -> {
            try {
                String jsonString = mapper.writeValueAsString(entity);
                System.out.println(jsonString);
                return Mono.just(carMapper.convertToJavaObject(jsonString));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Mono.error(e);
            }
        }).collectMap((item) -> ((LinkedHashMap) item).get("id").toString(), item -> (LinkedHashMap)item)
           .switchIfEmpty(Mono.defer(() -> Mono.error(new NoSuchCarException(array))))
               .onErrorResume(this::errorHandler);
    }

    public Mono<CarVariant> getCarById(String id) {
        return carDomainService.getCarVariantById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NoSuchCarException(id))));

    }

    private Mono<Map<String, LinkedHashMap>> errorHandler(Throwable throwable) {
        return Mono.error(throwable);
    }
}
