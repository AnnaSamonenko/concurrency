package producer_consumer.semaphore;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class PCSemaphore {
    static final int MAX_SIZE = 9;
    private static List<Integer> list = new LinkedList<>();
    private static Semaphore semaphore = new Semaphore(1, true);

    public static void main(String[] args) throws InterruptedException {
        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }

            }
        });

        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }

            }
        });
        threadProducer.start();
        threadConsumer.start();

        threadProducer.join();
        threadConsumer.join();
    }

    private static void producer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            semaphore.acquire();
            if (list.size() < MAX_SIZE) {
                System.out.println("Added " + list.add(r.nextInt(10)) + "|size:" + list.size());
            }
            semaphore.release();
        }
    }

    private static void consumer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            semaphore.acquire();
            if (!list.isEmpty()) {
                System.out.println("Value: " + list.remove(list.size() - 1) + "|size: " + list.size());
            }
            semaphore.release();
        }
    }

}
