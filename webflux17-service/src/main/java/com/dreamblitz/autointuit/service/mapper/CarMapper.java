package com.dreamblitz.autointuit.service.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CarMapper {

    ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
    }

    public Object convertToJavaObject(String jsonString) {
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

    public Map<String, LinkedHashMap> hideCommon(Map<String, LinkedHashMap>  carMap) {
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
