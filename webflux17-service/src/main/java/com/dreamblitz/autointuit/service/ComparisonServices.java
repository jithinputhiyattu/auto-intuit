package com.dreamblitz.autointuit.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ComparisonServices {
    public Mono<String> sampleResponse() {
        return Mono.just("Hello");
    }
}
