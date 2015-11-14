package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//Answer : 64564225042
public class Task_219 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_219());
        Logger.close();
    }

    int n = 1000000000;

    TreeMap<Long, Nodes> nodes = new TreeMap<>();

    public void solving() {

        Nodes first = new Nodes(0L, 1);
        nodes.put(0L, first);

        for (int i = 1; i < n; ++i) {
            progress10000000(i);

            Iterator<Map.Entry<Long, Nodes>> iterator = nodes.entrySet().iterator();
            Map.Entry<Long, Nodes> e = iterator.next();

            long value = e.getKey();
            Nodes n = e.getValue();

            if (n.count == 1) {
                iterator.remove();
            } else {
                n.count--;
            }

            put(value + 1);
            put(value + 4);
        }

        long cost = 0;
        for (Nodes n : nodes.values()) {
            cost += n.value * n.count;
        }
        System.out.println(cost);
    }

    private void put(long value) {
        Nodes n = nodes.get(value);
        if (n != null) {
            n.count++;
        } else {
            nodes.put(value, new Nodes(value, 1));
        }
    }

    static class Nodes implements Comparable<Nodes> {
        int count;
        long value;

        public Nodes(long value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Nodes o) {
            return Long.compare(value, o.value);
        }
    }
}
