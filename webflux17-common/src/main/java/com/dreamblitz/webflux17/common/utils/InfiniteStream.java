package com.dreamblitz.webflux17.common.utils;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class InfiniteStream {

    public IntStream infiniteIntStream() {
        Random  random = new Random(1000000L);
        return random.ints();
    }
}
