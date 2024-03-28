package com.dreamblitz.webflux17.common.test.utils;

import com.dreamblitz.webflux17.common.utils.InfiniteStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfiniteStreamTest {

    @InjectMocks
    InfiniteStream infiniteStream;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnSumOf10RandomNumbers(){


      int value = infiniteStream.infiniteIntStream().limit(10).reduce(0, (a, b)-> a+b);
      Assertions.assertEquals(true, value > 1000);
    }
}
