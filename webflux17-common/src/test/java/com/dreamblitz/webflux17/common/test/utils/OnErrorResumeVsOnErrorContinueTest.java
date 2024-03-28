package com.dreamblitz.webflux17.common.test.utils;

import com.dreamblitz.webflux17.common.utils.FluxAndBackPressure;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OnErrorResumeVsOnErrorContinueTest {

    @InjectMocks
    FluxAndBackPressure fluxAndBackPressure;

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    void onErrorResumeTest(){
        StepVerifier.create(
        fluxAndBackPressure.get100Ints().map(i ->{
            if(i == 11) {
                throw new RuntimeException("test");
            }
            return i;
        }).onErrorResume(i-> Mono.empty())
         .reduce(0,(a,b)->a+b))
                .assertNext(sum -> Assertions.assertEquals(55,sum)).verifyComplete();

    }

    @Test
    void onErrorContinueTest(){
        StepVerifier.create(fluxAndBackPressure.get100Ints()
            .map(i -> {
                if(i == 11) {
                  throw new RuntimeException("test");
                }
                return i;
            }).onErrorContinue((err,i)->{}).reduce(0,(a,b)->a+b))
                .assertNext(sum -> Assertions.assertEquals(5050 - 11,sum)).verifyComplete();

    }

    @Test
    void onErrorContinueTest2(){
        StepVerifier.create(fluxAndBackPressure.get100Ints()
                        .map(i -> {
                            if(i != 111) {
                                throw new RuntimeException("test");
                            }
                            return i;
                        }).onErrorContinue((err,i)->{}).reduce(0,(a,b)->a+b))
                .assertNext(sum -> Assertions.assertEquals(0,sum)).verifyComplete();

    }



}
