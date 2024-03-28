package com.dreamblitz.webflux17.common.utils;

import java.util.function.BiConsumer;

public class CustomBiConsumer implements BiConsumer<Integer, String> {

    @Override
    public void accept(Integer i, String j) {
        System.out.println(i + " : " + j);
    }

    public static BiConsumer<Integer, String> getInstance() {
        return new CustomBiConsumer();
    }
}
