package com.ramprasadg.java.threads.ProducerConsumer.WaitNotify;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {

    final int MAX_SIZE = 3;
    Queue<Integer> queue;

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } ;
        Random random = new Random();
        long currentTime = System.currentTimeMillis();
        while (true) {
            synchronized (queue) {
                while (queue.size() >= MAX_SIZE) {
                    try {
                        System.out.println("Queue is full, Producer is waiting...");
                        queue.wait(); // Wait if the queue is full
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return; // Exit if interrupted
                    }
                }

                int i = random.nextInt(100);
                queue.offer(i);
                queue.notifyAll(); // Notify the consumer
                System.out.println("Produced: " + i);
            }

            if (System.currentTimeMillis() - currentTime > 10_000) {
                break;
            }
        }
    }
}
