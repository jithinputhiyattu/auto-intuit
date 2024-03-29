package com.dreamblitz.autointuit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ComparisonServices {

    @Autowired
    CarDomainService carDomainService;

    public Mono<String> sampleResponse() {
        return Mono.just("Hello");
    }
}
