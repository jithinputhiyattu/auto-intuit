package com.dreamblitz.webflux17.common.test.utils;

import com.dreamblitz.webflux17.common.utils.Custom9KSubscriber;
import com.dreamblitz.webflux17.common.utils.CustomSubscriber;
import com.dreamblitz.webflux17.common.utils.FluxAndBackPressure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FluxAndBackPressureTest {

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
    public void shouldReturnOnly5(){
        fluxAndBackPressure.get100Ints().subscribe(CustomSubscriber.getSubSubscriber());
    }

    // @Test
    public void shouldReturnOnly900Blocks(){

        Custom9KSubscriber custom9KSubscriber = new Custom9KSubscriber();

        Scheduler x = Schedulers.single();

        fluxAndBackPressure.get100Ints().subscribeOn(x)
                .parallel()
                .subscribe(System.out::println,
                err -> err.printStackTrace(),
                custom9KSubscriber,
                custom9KSubscriber
        );
    }

    boolean ifMatch(Map<Character, Integer> map , Map<Character, Integer> w) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Deque<String> deque = new ArrayDeque<String>();

        for( Map.Entry<Character, Integer> x: w.entrySet()) {
            if(map.get(x.getKey()) != x.getValue()){
                return false;
            }
        }
        return true;
    }


}
