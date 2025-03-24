package com.ramprasadg.java.threads.ProducerConsumer.BlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        while (true) {
            try {
                // queue.take blocks if the queue is empty.
                // queue.poll returns null if the queue is empty.
                System.out.println("Consumed: " + queue.take());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(System.currentTimeMillis() - currentTime > 10_000 && queue.isEmpty()) {
                break;
            }
        }
    }    
}