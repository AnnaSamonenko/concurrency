package producer_consumer.monitor;

import java.util.ArrayList;
import java.util.List;

public class Monitor {

    private List<Integer> list = new ArrayList<>();

    public synchronized void add(int value) {
        if (list.size() < 10)
            System.out.println("ADD: Value: " + list.add(value) + ", size:" + list.size());
    }

    public synchronized void remove() {
        if (list.size() > 1)
            System.out.println("REMOVE: Value: " + list.remove(list.size() - 1) + ", size:" + list.size());
    }

}
