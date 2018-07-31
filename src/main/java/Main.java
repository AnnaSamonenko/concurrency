import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

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
            queue.put(r.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            if (r.nextInt(10) == 0) {
                Thread.sleep(100);
                System.out.println("Value: " + queue.take() + ", size:" + queue.size());
            }
        }
    }

}
