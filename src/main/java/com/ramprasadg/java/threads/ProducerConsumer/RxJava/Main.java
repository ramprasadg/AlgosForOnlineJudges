package com.ramprasadg.java.threads.ProducerConsumer.RxJava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Create the producer
        Producer producer = new Producer();

        // Get the Flowable from the producer
        Flowable<Integer> producerFlowable = producer.getProducerFlowable();

        // Create the consumer
        Consumer consumer = new Consumer();

        // Convert the Flowable to an Observable for the consumer
        Observable<Integer> consumerObservable = producerFlowable.toObservable();

        // Start consuming
        consumer.consume(consumerObservable);

        // Keep the main thread alive to see the output
        Thread.sleep(15_000);

        // Dispose the consumer
        consumer.dispose();

        System.out.println("Main thread completed");
    }
}
