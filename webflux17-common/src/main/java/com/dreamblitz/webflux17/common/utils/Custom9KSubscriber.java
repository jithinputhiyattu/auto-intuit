package com.dreamblitz.webflux17.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@Getter
@Setter
public class Custom9KSubscriber implements Consumer<Subscription>, Runnable {

    int bufferSize = 10; // Should be CCM driven
    int blockNo = 1;

    AtomicBoolean elementExist = new AtomicBoolean(true);

    @Override
    public void accept(Subscription subscription) {
        while (elementExist.get()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Processing block number: " + blockNo);
            subscription.request(bufferSize);
            blockNo++;
        }
        System.out.println("Subscriber processed all the available values ");
    }

    @Override
    public void run() {
        System.out.println("Stop");
        elementExist.set(false);
    }
}
