package producer_consumer.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCSLock {
    private static final int MAX_SIZE = 9;
    private static List<Integer> list = new LinkedList<>();
    private static Lock lock = new ReentrantLock();
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Thread threadProducer = new Thread(PCSLock::producer);
        Thread threadConsumer = new Thread(PCSLock::consumer);

        threadProducer.start();
        threadConsumer.start();

        threadProducer.join();
        threadConsumer.join();
    }

    private static void producer() {
        while (true) {
            lock.lock();
            try {
                if (list.size() < MAX_SIZE) {
                    System.out.println("Added " + list.add(random.nextInt(10)) + "|size:" + list.size());
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static void consumer() {
        while (true) {
            lock.lock();
            try {
                if (!list.isEmpty()) {
                    System.out.println("Value: " + list.remove(list.size() - 1) + "|size: " + list.size());
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
