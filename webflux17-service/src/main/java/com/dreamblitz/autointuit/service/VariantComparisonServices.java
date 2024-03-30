package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.common.exception.AutoIntuitUnhandledException;
import com.dreamblitz.autointuit.domain.entity.CarVariant;
import com.dreamblitz.autointuit.service.mapper.CarMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class VariantComparisonServices {

    @Autowired
    CarDomainService carDomainService;

    @Autowired
    CarMapper carMapper;

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
    }

    public Mono<CarVariant> getCarById(String id) throws AutoIntuitUnhandledException {
        try {
            return carDomainService.getCarVariantById(id);
        } catch (Exception exception) {
            throw  AutoIntuitUnhandledException.getInstance(exception);
        }
    }

    private Mono<Map<String, LinkedHashMap>> errorHandler(Throwable throwable) {
        return Mono.error(throwable);
    }
}
