package tasks;

import utils.log.Logger;

import java.util.PriorityQueue;

//Answer :
public class Task_535 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_535());
        Logger.close();
    }

    public long LIM = 33;
//    public long LIM = 1000000;
//    public long LIM = 1000000000;
//    public long LIM = 10000000000L;
//    public long LIM = 1000000000000000000L;

    PriorityQueue<Element> q = new PriorityQueue<>();

    public void solving() {
        long res = 0;
        long count = 0;
        long index = 1;
        long next = 2;
        long nextIndex;
        long maxPushed = 0;

//        System.out.println("1 1 2 1 3 2 4 1 5 3 6 2 7 8 4 9 1 10 11 5");
        q.add(new Element(1, 1));
        while (!q.isEmpty()) {
            Element e = q.poll();

            while (index < e.index && index<=LIM) {
                res += next;
                System.out.println(next + ": " + res);

                count += Math.sqrt(next);
                nextIndex = count + index;
                if (nextIndex <= LIM) {
                    q.add(new Element(next, nextIndex));
                }
                maxPushed = max(maxPushed, next);
                ++index;
                ++next;
            }

            if (index == LIM+1) {
                break;
            }


            count += Math.sqrt(e.value);
            res += e.value;
            System.out.println(e.value + ": " + res);

            nextIndex = count + index;
            if (nextIndex <= LIM) {
                q.add(new Element(e.value, nextIndex));
            }
            ++index;
        }
        System.out.println(res);
    }

    static class Element implements Comparable<Element> {
        long value;
        long index;

        public Element(long value, long index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(index, o.index);
        }

        public String toString() {
            return "{" + value + ": " + index + "}";
        }
    }
}
