package com.dreamblitz.autointuit.infra.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class InfraUtils {

    public static <T> T getFromJson(String jsonFileName, Class<T> valueType) {
        ObjectMapper jackson = new ObjectMapper();
        try {
            //String json = IOUtils.toString(InfraUtils.class.getResource(jsonFileName), "UTF-8");
            return null;//jackson.readValue(json, valueType);
        } catch (Exception ioExp) {
          // Log error
        }
        return null;
    }
}