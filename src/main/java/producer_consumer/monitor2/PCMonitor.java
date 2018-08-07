package producer_consumer.monitor2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PCMonitor {
    static final int MAX_SIZE = 9;
    private static List<Integer> list = new LinkedList<>();

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
            synchronized (list) {
                if (list.size() != MAX_SIZE) {
                    System.out.println("ADDED: " + list.add(r.nextInt(10)) + "|size:" + list.size());
                }
                list.notify();
                list.wait();
            }
        }
    }

    private static void consumer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            synchronized (list) {
                if (!list.isEmpty()) {
                    System.out.println("REMOVE: " + list.remove(list.size() - 1) + "|size: " + list.size());
                }
                list.notify();
                list.wait();
            }
        }
    }
}
