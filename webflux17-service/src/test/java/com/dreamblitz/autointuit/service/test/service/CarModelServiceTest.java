package com.dreamblitz.autointuit.service.test.service;

import com.dreamblitz.autointuit.common.exception.NoSuchCarException;
import com.dreamblitz.autointuit.domain.entity.CarMetadataEntity;
import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import com.dreamblitz.autointuit.service.CarModelDomainService;
import com.dreamblitz.autointuit.service.CarModelService;
import com.dreamblitz.autointuit.service.SuggestionDomainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarModelServiceTest {

    @InjectMocks
    CarModelService carModelService;

    @Mock
    CarModelDomainService carModelDomainService;

    @Mock
    SuggestionDomainService suggestionDomainService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnNoSuchCarException() {

        CarMetadataEntity  metadataEntity =  new CarMetadataEntity();
        metadataEntity.setCarId("invalid");
        Mockito.when(suggestionDomainService.updatePopularity(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(metadataEntity));
        Mockito.when(carModelDomainService.getCarModelById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());
        StepVerifier.create(carModelService.catCarModelById("invalid"))
                .expectError(NoSuchCarException.class).verify();
    }

    @Test
    public void shouldReturnValidCarEntity() {

        CarMetadataEntity  metadataEntity =  new CarMetadataEntity();
        metadataEntity.setCarId("validId");
        Mockito.when(suggestionDomainService.updatePopularity(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(metadataEntity));
        Mockito.when(carModelDomainService.getCarModelById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(new CarModelEntity()));
        StepVerifier.create(carModelService.catCarModelById("validId"))
                .assertNext( i -> Assertions.assertTrue(i instanceof CarModelEntity))
                .verifyComplete();
    }
}
