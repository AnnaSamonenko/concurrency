package producer_consumer.monitor;

import java.util.Random;

public class Main {

    private static Monitor monitor = new Monitor();

    public static void main(String[] args) throws InterruptedException {
        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                producer();
            }
        });

        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                consumer();
            }
        });
        threadProducer.start();
        threadConsumer.start();

        threadProducer.join();
        threadConsumer.join();

    }

    private static void producer() {
        Random r = new Random();
        while (true) {
            monitor.add(r.nextInt(100));
        }
    }

    private static void consumer() {
        while (true) {
            monitor.remove();
        }
    }

}
