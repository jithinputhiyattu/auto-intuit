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

    private Object convertToJavaObject(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object javaObject = objectMapper.readValue(jsonString, Object.class);
            return javaObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LinkedHashMap hideNull(LinkedHashMap object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String jsonString = objectMapper.writeValueAsString(object);
            return objectMapper.readValue(jsonString, LinkedHashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Mono<Map<String, LinkedHashMap>>  compareCars(String vehicleId1, String vehicleId2, Boolean hideCommon) {

       ObjectMapper mapper = new ObjectMapper();
       mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

       String [] array = {vehicleId1, vehicleId2};

       return carDomainService.getCarById( array).flatMap(entity -> {
            try {
                String jsonString = mapper.writeValueAsString(entity);
                System.out.println(jsonString);
                return Mono.just(convertToJavaObject(jsonString));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Mono.error(e);
            }
        }).collectMap((item) -> ((LinkedHashMap) item).get("id").toString(), item -> (LinkedHashMap)item)
               .map(map ->  hideCommon ?  this.hideCommon(map) : map);

    }

    private Map<String, LinkedHashMap>  hideCommon(Map<String, LinkedHashMap>  carMap) {

        int size = carMap.size();
        if( size >= 2 ) {
            LinkedHashMap thisMap = carMap.values().stream().findFirst().get();
            for(Object key:  thisMap.keySet()) {
                hideCommon(carMap, thisMap , key, size);
            }
           for(Map.Entry<String, LinkedHashMap> item : carMap.entrySet()) {
               carMap.put(item.getKey(), hideNull(item.getValue()));
           }
        }


        return carMap;
    }

    private void hideCommon(Map<String, LinkedHashMap> carMap,LinkedHashMap thatMap, Object key, int size) {

        int count = 0;
        for(Map.Entry<String, LinkedHashMap> item : carMap.entrySet()) {
            LinkedHashMap thisMap =  item.getValue();
            if(thisMap.get(key).equals(thatMap.get(key))) {
                count++;
            }
        }

        if(count == size) {
            for(Map.Entry<String, LinkedHashMap> item : carMap.entrySet()) {
                LinkedHashMap thisMap = item.getValue();
                thisMap.put(key, null);
            }
        }
    }



}
