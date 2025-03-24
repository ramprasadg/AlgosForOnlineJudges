package com.ramprasadg.java.threads;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class CounterThreadSafety {

    static interface ICounter {
        void increment();

        int getCount();
    }

    static class CounterUnsafe implements ICounter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    static class CounterSynchronizedOnlyWriteMethod implements ICounter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    static class CounterSynchronizedMethod implements ICounter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    static class CounterVolatileSynchronizedMethod implements ICounter {
        private volatile int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    static class CounterSynchronizedBlock implements ICounter {
        private int count = 0;

        public void increment() {
            synchronized (this) {
                count++;
            }
        }

        public int getCount() {
            synchronized (this) {
                return count;
            }
        }
    }

    static class CounterSynchronizedBlockClassLevel implements ICounter {
        private int count = 0;

        public void increment() {
            synchronized (CounterSynchronizedBlockClassLevel.class) {
                count++;
            }
        }

        public int getCount() {
            synchronized (CounterSynchronizedBlockClassLevel.class) {
                return count;
            }
        }
    }

    static class CounterSynchronizedBlockSeparateLock implements ICounter {
        private int count = 0;
        private final Object lock = new Object();

        public void increment() {
            synchronized (lock) {
                count++;
            }
        }

        public int getCount() {
            synchronized (lock) {
                return count;
            }
        }
    }

    static class CounterReentrantLock implements ICounter {
        private int count = 0;
        private final Lock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        public int getCount() {
            lock.lock();
            try {
                return count;
            } finally {
                lock.unlock();
            }
        }
    }

    static class CounterReadWriteLock implements ICounter {
        private int count = 0;
        private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

        public void increment() {
            rwLock.writeLock().lock();
            try {
                count++;
            } finally {
                rwLock.writeLock().unlock();
            }
        }

        public int getCount() {
            rwLock.readLock().lock();
            try {
                return count;
            } finally {
                rwLock.readLock().unlock();
            }
        }
    }

    /**
     * https://medium.com/@apusingh1967/low-latency-programming-stampedlock-is-the-champion-a8b07f8c95be
     * Downside: It is not reentrant. A thread that acquires StampedLock, cannot reacquire it. So a
     * thread can deadlock itself. Coding is harder as compared to other locks like ReentrantLock
     * and ReadWriteLock.
     */
    static class CounterStampedLock implements ICounter {
        private int count = 0;
        private final StampedLock stampedLock = new StampedLock();

        public void increment() {
            long stamp = stampedLock.writeLock();
            try {
                count++;
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        }

        public int getCount() {
            long stamp = stampedLock.tryOptimisticRead();
            int currentCount = count;
            if (!stampedLock.validate(stamp)) {
                stamp = stampedLock.readLock();
                try {
                    currentCount = count;
                } finally {
                    stampedLock.unlockRead(stamp);
                }
            }
            return currentCount;
        }
    }

    static class CounterSemaphore implements ICounter {
        private int count = 0;
        private final Semaphore semaphore = new Semaphore(1);

        public void increment() {
            try {
                semaphore.acquire();
                count++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        }

        public int getCount() {
            try {
                semaphore.acquire();
                return count;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return -1; // Handle error
            } finally {
                semaphore.release();
            }
        }
    }

    static class CounterPhaser implements ICounter {
        private int count = 0;
        private final Phaser phaser = new Phaser(1);

        public void increment() {
            phaser.arriveAndAwaitAdvance();
            try {
                count++;
            } finally {
                phaser.arrive();
            }
        }

        public int getCount() {
            phaser.arriveAndAwaitAdvance();
            try {
                return count;
            } finally {
                phaser.arrive();
            }
        }
    }

    static class CounterVarHandle implements ICounter {
        private int count = 0;
        private static final VarHandle COUNT_HANDLE;

        static {
            try {
                COUNT_HANDLE = MethodHandles.lookup().findVarHandle(CounterVarHandle.class, "count",
                        int.class);
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        public void increment() {
            COUNT_HANDLE.getAndAdd(this, 1);
        }

        public int getCount() {
            return (int) COUNT_HANDLE.getVolatile(this);
        }
    }

    // Optimized for high write contention (many threads updating the counter).
    // uses "striping": distributes updates across an array of cells to reduce contention.
    // Higher memory usage due to the internal array of cells.
    static class CounterLongAdder implements ICounter {
        private final LongAdder count = new LongAdder();

        public void increment() {
            count.increment();
        }

        public int getCount() {
            return (int) count.sum(); // or intValue() for exact snapshot
        }
    }

    static class CounterAtomicIntegerFieldUpdater implements ICounter {
        private volatile int count = 0;
        private static final AtomicIntegerFieldUpdater<CounterAtomicIntegerFieldUpdater> UPDATER = AtomicIntegerFieldUpdater
                .newUpdater(CounterAtomicIntegerFieldUpdater.class, "count");

        public void increment() {
            UPDATER.incrementAndGet(this);
        }

        public int getCount() {
            return count;
        }
    }

    static class CounterAtomicInteger implements ICounter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }

    static class CounterRunnable implements Runnable {
        private List<ICounter> counters;

        public CounterRunnable(List<ICounter> counters) {
            this.counters = counters;
        }

        public void run() {
            for (int i = 0; i < 10_00_000; i++) {
                for (ICounter counter : counters) {
                    counter.increment();
                }
            }

            printCounters(counters);
        }
    }

    private static void printCounters(List<ICounter> counters) {
        StringBuilder sb = new StringBuilder(
                String.format("Thread level count: %s", Thread.currentThread().getName()));
        for (ICounter counter : counters) {
            sb.append(
                    String.format("\n\t %s: %s, ", counter.getClass().getSimpleName(),
                            counter.getCount()));
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws InterruptedException {

        ICounter counterUnsafe = new CounterUnsafe();
        ICounter counterSynchronizedOnlyWriteMethod = new CounterSynchronizedOnlyWriteMethod();
        ICounter counterSynchronizedMethod = new CounterSynchronizedMethod();
        ICounter counterSynchronizedBlock = new CounterSynchronizedBlock();
        ICounter counterSynchronizedBlockClassLevel = new CounterSynchronizedBlockClassLevel();
        ICounter counterSynchronizedBlockSeparateLock = new CounterSynchronizedBlockSeparateLock();
        ICounter counterReentrantLock = new CounterReentrantLock();
        ICounter counterReadWriteLock = new CounterReadWriteLock();
        ICounter counterStampedLock = new CounterStampedLock();
        ICounter counterSemaphore = new CounterSemaphore();
        ICounter counterPhaser = new CounterPhaser();
        ICounter counterVarHandle = new CounterVarHandle();
        ICounter counterLongAdder = new CounterLongAdder();
        ICounter counterAtomicInteger = new CounterAtomicInteger();

        List<ICounter> counters = List.of(
                counterUnsafe,
                counterSynchronizedOnlyWriteMethod,
                counterSynchronizedMethod,
                counterSynchronizedBlock,
                counterSynchronizedBlockClassLevel,
                counterSynchronizedBlockSeparateLock,
                counterReentrantLock,
                counterReadWriteLock,
                counterStampedLock,
                counterSemaphore,
                counterPhaser,
                counterVarHandle,
                counterLongAdder,
                counterAtomicInteger);

        CounterRunnable counterRunnable = new CounterRunnable(counters);

        Thread thread1 = new Thread(counterRunnable);
        Thread thread2 = new Thread(counterRunnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        printCounters(counters);
    }
}
