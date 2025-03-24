package com.ramprasadg.java.threads.ProducerConsumer.WaitNotify;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList();

        Producer producer1 = new Producer(queue);
        // Producer producer2 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        Thread producer1Thread = new Thread(producer1);
        // Thread producer2Thread = new Thread(producer2);
        Thread consumer1Thread = new Thread(consumer1);
        Thread consumer2Thread = new Thread(consumer2);

        producer1Thread.start();
        // producer2Thread.start();
        consumer1Thread.start();
        consumer2Thread.start();

        try {
            producer1Thread.join();
            // producer2Thread.join();
            consumer1Thread.join();
            consumer2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
