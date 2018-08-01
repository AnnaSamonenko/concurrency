package producer_consumer.monitor;

import java.util.Random;

public class Main {

    static Monitor monitor = new Monitor();

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
            monitor.add(r.nextInt(100));
            Thread.sleep(5);
        }
    }

    private static void consumer() throws InterruptedException {
        while (true) {
             monitor.remove();
             Thread.sleep(10);
        }
    }

}
