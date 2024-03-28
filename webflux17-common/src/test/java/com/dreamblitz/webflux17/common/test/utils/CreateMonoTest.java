package com.dreamblitz.webflux17.common.test.utils;

import com.dreamblitz.webflux17.common.utils.CreateMono;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateMonoTest {

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void beforeEach() {
        createMono.resetIntegerValue();
    }

    @InjectMocks
    CreateMono createMono;

    @Test
    public void shouldReturn1() {

        StepVerifier.create(createMono.createMonoUsingJust())
                .assertNext(intVal -> {
                    Assertions.assertEquals(1, intVal);
                }).verifyComplete();
    }

    @Test
    public void shouldReturn2() {
        createMono.createMonoUsingJust(); // 1
        StepVerifier.create(createMono.createMonoUsingCreate())
                .assertNext(intVal -> Assertions.assertEquals(2, intVal)).verifyComplete();
    }

    @Test
    public void shouldReturn1SinceCreateIsLazy() {
        createMono.createMonoUsingCreate(); // 1
        StepVerifier.create(createMono.createMonoUsingCreate())
                .assertNext(intVal -> Assertions.assertEquals(1, intVal)).verifyComplete();
    }

    @Test
    public void shouldReturn1SinceDeferIsLazy() {
        createMono.createMonoUsingDefer(); // 1
        StepVerifier.create(createMono.createMonoUsingDefer())
                .assertNext(intVal -> Assertions.assertEquals(1, intVal)).verifyComplete();
    }

    @Test
    public void doOnNextTest() {
        createMono.createMonoUsingDefer(); // 1
        StepVerifier.create(createMono.createMonoUsingDefer()
            .doOnNext(i -> Assertions.assertEquals(1, i)))
                .assertNext(intVal -> Assertions.assertEquals(1, intVal)).verifyComplete();
    }

    @Test
    public void shouldReturn1SinceFromSupplierIsLazy() {
        createMono.createMonoUsingSupplier(); // 1
        StepVerifier.create(createMono.createMonoUsingSupplier())
                .assertNext(intVal -> Assertions.assertEquals(1, intVal)).verifyComplete();
    }

    @Test
    public void doOnNextTest2() {

        Flux<Integer> flux = Flux.range(1, 5);
        flux = flux.doOnComplete(()-> {
            System.out.println("completed");
        });

        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);

    }
}
