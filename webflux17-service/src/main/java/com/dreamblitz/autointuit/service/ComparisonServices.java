package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.repository.CarRepository;
import com.dreamblitz.autointuit.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ComparisonServices {

    @Autowired
    ICarRepository carComparisonService;

    public Mono<String> sampleResponse() {
        return Mono.just("Hello");
    }
}
