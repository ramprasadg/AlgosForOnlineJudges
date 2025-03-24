package com.ramprasadg.java.threads.ProducerConsumer.WaitNotify;

import java.util.Queue;

public class Consumer implements Runnable {

    Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        while (true) {
            synchronized (queue) {
                try {
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty, Consumer is waiting...");
                        queue.wait();
                    }
                    System.out.println("Consumed: " + queue.poll());
                    queue.notifyAll();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return; // Exit if interrupted
                }
            }
            if (System.currentTimeMillis() - currentTime > 10_000 && queue.isEmpty()) {
                break;
            }
        }
    }
}
