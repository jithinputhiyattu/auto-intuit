package com.dreamblitz.autointuit.infra.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.apache.commons.io.IOUtils;

@Component
public class InfraUtils {

    public <T> T getFromJson(String jsonFileName, Class<T> valueType) {
        ObjectMapper jackson = new ObjectMapper();
        try {
            String json = IOUtils.toString(InfraUtils.class.getResource(jsonFileName), "UTF-8");
            return jackson.readValue(json, valueType);
        } catch (Exception ioExp) {
          // Log error
            System.out.println(ioExp.toString());
        }
        return null;
    }
}
