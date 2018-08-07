package producer_consumer.monitor;

import java.util.LinkedList;
import java.util.List;

public class Monitor {

    private List<Integer> list = new LinkedList<>();

    public void add(int value) {
        if (list.size() < 10)
            synchronized (list) {
                System.out.println("ADD: Value: " + list.add(value) + ", size:" + list.size());
            }

    }

    public void remove() {
        if (list.size() > 1)
            synchronized (list) {
                System.out.println("REMOVE: Value: " + list.remove(list.size() - 1) + ", size:" + list.size());
            }

    }

}
