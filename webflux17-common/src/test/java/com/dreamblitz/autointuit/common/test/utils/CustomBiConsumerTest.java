package com.dreamblitz.autointuit.common.test.utils;

import com.dreamblitz.autointuit.common.utils.CustomBiConsumer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;

import java.util.LinkedHashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomBiConsumerTest {


    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    void myTest() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Jithin");
        map.put(2, "Puthiyattu");
        map.put(3, "India");
        map.forEach(CustomBiConsumer.getInstance());
    }

}
