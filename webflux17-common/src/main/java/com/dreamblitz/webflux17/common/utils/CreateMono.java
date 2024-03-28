package com.dreamblitz.webflux17.common.utils;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CreateMono {

    AtomicInteger value = new AtomicInteger(0);


    public void resetIntegerValue() {
        value.set(0);
    }

    public Mono<Integer> createEmptyMono() {
        return Mono.empty();
    }


    public Mono<Integer> createMonoUsingCreate() {
        return Mono.create((monoSink) -> {
            monoSink.success(value.incrementAndGet());
        });
    }

    public Mono<Integer> createMonoUsingDefer() {
        return Mono.defer(()-> Mono.just(value.incrementAndGet()));
    }


    public  Mono<Integer> createMonoUsingJust() {
        return Mono.just(value.incrementAndGet());
    }


    public Mono<Integer> createMonoUsingSupplier() {
        return Mono.fromSupplier(() -> {
            return  value.incrementAndGet();
        });
    }
}
