package com.ramprasadg.java.threads.ProducerConsumer.RxJava;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Random;

public class Producer {

    private final Flowable<Integer> producerFlowable;
    Random random = new Random();

    public Producer() {
        producerFlowable = Flowable.create(
                emitter -> {
                    produce(emitter);
                },
                BackpressureStrategy.BUFFER);
        producerFlowable.subscribeOn(Schedulers.newThread()); // Run production on a separate thread
    }

    private void produce(FlowableEmitter<Integer> emitter) {
        long currentTime = System.currentTimeMillis();
        while (!emitter.isCancelled()) {
            int item = random.nextInt(100);
            System.out.println("Produced: " + item);
            emitter.onNext(item);
            try {
                Thread.sleep(500); // Simulate production time
            } catch (InterruptedException e) {
                emitter.onError(e);
                return;
            }
            if (System.currentTimeMillis() - currentTime > 10_000) {
                emitter.onComplete();
            }
        }
    }

    public Flowable<Integer> getProducerFlowable() {
        return producerFlowable;
    }
}
