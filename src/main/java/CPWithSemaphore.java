import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class CPWithSemaphore {
    static final int MAX_SIZE = 9;
    private static List list = new LinkedList();
    private static Semaphore semaphoreConsumer = new Semaphore(0);
    private static Semaphore semaphoreProducer = new Semaphore(1);

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
            if (list.size() == MAX_SIZE || list.size() == r.nextInt(10)) {
                semaphoreProducer.acquire();
                semaphoreConsumer.release(1);
            }
            else System.out.println("Added " + list.add(r.nextInt(10)));
        }
    }

    private static void consumer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            if (list.isEmpty() || list.size() == r.nextInt(10)) {
                semaphoreConsumer.acquire();
                semaphoreProducer.release(1);
            }
            else System.out.println("Value: " + list.remove(list.size() - 1) + ", size: " + list.size());
        }
    }

}
