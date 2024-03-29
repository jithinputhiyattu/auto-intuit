package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.domain.entity.CarEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class ComparisonServices {

    @Autowired
    CarDomainService carDomainService;

    private static Object convertToJavaObject(String jsonString) {
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert JSON string to Java object
            Object javaObject = objectMapper.readValue(jsonString, Object.class);
            return javaObject;
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }

    public Mono<Map<String, Object>>  compareCars(String vehicleId1, String vehicleId2, Boolean hideCommon) {

       ObjectMapper mapper = new ObjectMapper();
       mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

       String [] array = {vehicleId1, vehicleId2};

       return carDomainService.getCarById( array, hideCommon).flatMap(entity -> {
            try {
                String jsonString = mapper.writeValueAsString(entity);
                System.out.println(jsonString);
                return Mono.just(convertToJavaObject(jsonString));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Mono.error(e);
            }
        }).collectMap((item) -> {
           LinkedHashMap x = ((LinkedHashMap) item);
           return x.get("id").toString();
       } , item -> item);

    }
}
