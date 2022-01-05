package src.main.java.com.ramprasadg.interview;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Manikandan on 10/29/2016.
 */
public class MostRecentlyInsertedBlockingQueue<T> extends AbstractQueue<T> implements BlockingQueue<T> {

    private Node dummy = new Node(null);
    private Node head;
    private Node tail;
    private final int capacity;
    private AtomicInteger size;
    private final ReentrantLock offerLock;
    private final ReentrantLock pollLock;

    private class Node {
        private T value;
        private Node next;

        Node(T value) {
            this.value = value;
            next = null;
        }
    }

    public MostRecentlyInsertedBlockingQueue(int capacity) {
        this.capacity = capacity;
        head = dummy;
        tail = dummy;
        size = new AtomicInteger(0);
        offerLock = new ReentrantLock();
        pollLock = new ReentrantLock();
    }

    @Override
    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException("Null element is not allowed.");
        }
        int oldSize = -1;
        offerLock.lock();
        try {
            if (remainingCapacity() == 0) {
                pollLock.lock();
                try {
                    // check again for free space
                    if (remainingCapacity() == 0) {
                        poll();
                    }
                } finally {
                    pollLock.unlock();
                }
            }
            tail = tail.next = new Node(t);
            oldSize = size.getAndIncrement();

        } finally {
            offerLock.unlock();
        }

        return oldSize >= 0;
    }

    public T poll() {
        T t = null;
        int oldSize = -1;
        pollLock.lock();
        try {
            if (size.get() > 0) {
                Node tempHead = head;
                Node first = tempHead.next;
                tempHead.next = tempHead;
                head = first;
                t = first.value;
                first.value = null;
                size.getAndDecrement();
            }
        } finally {
            pollLock.unlock();
        }

        return t;
    }

    public T peek() {
        if (size.get() == 0) {
            return null;
        }

        pollLock.lock();
        try {
            if (size.get() == 0) {
                return null;
            }

            Node tempHead = head.next;
            if (tempHead == null)
                return null;
            else
                return tempHead.value;
        } finally {
            pollLock.unlock();
        }
    }

    public Iterator<T> iterator() {
        return new MostRecentlyInsertedQueueIterator();
    }

    public void put(T t) throws InterruptedException {
        offer(t);
    }

    public boolean offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return offer(t);
    }

    public T take() throws InterruptedException {
        return poll();
    }

    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        return poll();
    }

    public int remainingCapacity() {
        return capacity - size();
    }

    public int size() {
        return size.get();
    }

    public boolean isEmpty() {
        return size() != 0;
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public int drainTo(Collection<? super T> c) {
        int elementsDrained = 0;
        pollLock.lock();
        elementsDrained = size();
        c.addAll(this);
        pollLock.unlock();
        return elementsDrained;
    }

    public int drainTo(Collection<? super T> c, int maxElements) {
        int elementsDrained = 0;
        pollLock.lock();
        if (size() <= maxElements)
            elementsDrained = drainTo(c);
        else {
            elementsDrained = maxElements;
            Iterator<T> itr = this.iterator();
            for (int i = 1; itr.hasNext() && i <= maxElements; i++) {
                c.add(itr.next());
            }
        }
        pollLock.unlock();
        return elementsDrained;
    }

    private class MostRecentlyInsertedQueueIterator implements Iterator<T> {

        private Node current;
        private T currentItem;

        MostRecentlyInsertedQueueIterator() {
            offerLock.lock();
            pollLock.lock();
            try {
                current = head.next;
                if (current != null) {
                    currentItem = current.value;
                }
            } finally {
                offerLock.unlock();
                pollLock.unlock();
            }

        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            offerLock.lock();
            pollLock.lock();
            try {
                if (current == null)
                    throw new NoSuchElementException();
                T t = currentItem;
                current = current.next;
                currentItem = (current == null) ? null : current.value;
                return t;
            } finally {
                offerLock.unlock();
                pollLock.unlock();
            }
        }
    }

    public void iterate() {
        Iterator<T> itemIterator = iterator();
        System.out.println("In iterator::" + Thread.currentThread().getName());
        while (itemIterator.hasNext()) {
            System.out.print(itemIterator.next() + "\t");
        }
        System.out.println();
    }

    private void offerAndPrint(T t) {
        offerLock.lock();
        offer(t);
        System.out.println("Thread=" + Thread.currentThread().getName() + " Added=" + t + " Size=" + size());
        offerLock.unlock();
    }

    private T pollAndPrint() {
        pollLock.lock();
        T t = poll();
        System.out.println("Thread=" + Thread.currentThread().getName() + " Polled=" + t + " Size=" + size());
        pollLock.unlock();
        return t;
    }

    private T peekAndPrint() {
        pollLock.lock();
        T t = peek();
        System.out.println("Thread=" + Thread.currentThread().getName() + " Peeked=" + t + " Size=" + size());
        pollLock.unlock();
        return t;
    }

    private static class Runnable1 implements Runnable {

        private MostRecentlyInsertedBlockingQueue<Integer> queue;

        public Runnable1(MostRecentlyInsertedBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
            queue.offerAndPrint(1);
            queue.peekAndPrint();
            queue.offerAndPrint(2);
            queue.offerAndPrint(3);
            queue.pollAndPrint();
            queue.pollAndPrint();
            queue.pollAndPrint();
        }
    }

    private static class Runnable2 implements Runnable {

        private MostRecentlyInsertedBlockingQueue<Integer> queue;

        public Runnable2(MostRecentlyInsertedBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
             queue.offerAndPrint(4);
             queue.offerAndPrint(5);
             queue.pollAndPrint();
             queue.offerAndPrint(6);
             queue.offerAndPrint(7);
             queue.pollAndPrint();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MostRecentlyInsertedBlockingQueue<Integer> queue = new MostRecentlyInsertedBlockingQueue<Integer>(3);

        Thread t1 = new Thread(new Runnable1(queue));
        Thread t2 = new Thread(new Runnable2(queue));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        queue.iterate();
    }
}
