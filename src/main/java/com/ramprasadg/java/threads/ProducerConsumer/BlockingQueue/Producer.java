package com.ramprasadg.java.threads.ProducerConsumer.BlockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        long currentTime = System.currentTimeMillis();
        while(true) {
            int i = random.nextInt(100);
            try {
                // queue.put blocks if the queue is full.
                // queuue.add throws an exception if the queue is full.
                queue.put(i);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Produced: " + i);
            if(System.currentTimeMillis() - currentTime > 10_000) {
                break;
            }
        }
    }
}