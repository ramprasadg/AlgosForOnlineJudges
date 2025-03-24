package com.ramprasadg.java.threads.ProducerConsumer.RxJava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Consumer {

    private Disposable disposable;

    public void consume(Observable<Integer> observable) {
        disposable = observable
                .subscribeOn(Schedulers.newThread()) // Consume on a separate thread
                .subscribe(
                        item -> {
                            System.out.println("Consumed: " + item);
                            try {
                                Thread.sleep(1000); // Simulate consumption time
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        },
                        Throwable::printStackTrace,
                        () -> System.out.println("Consumer completed")
                );
    }

    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
