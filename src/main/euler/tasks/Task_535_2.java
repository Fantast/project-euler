package tasks;

import utils.log.Logger;

import java.util.PriorityQueue;

//Answer :
public class Task_535_2 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_535_2());
        Logger.close();
    }

    public long LIM = 20;
//    public long LIM = 1000;
//    public long LIM = 10000;
//    public long LIM = 10000000;
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

        q.add(new Element(1, 1));
        TOP: while (!q.isEmpty()) {
            Element e = q.poll();

            while (index < e.index && index<=LIM) {
                if (index == 1325207) {
                    System.out.println();
                }
                count += Math.sqrt(next);
                nextIndex = count + index;
                 if (nextIndex <= LIM) {
                    res += next;
                    q.add(new Element(next, nextIndex));
                } else {
                    long n = LIM - (index-1);
                    res += (n+1)*n/2;
                    break TOP;
                }
                ++index;
                ++next;
            }

            if (index == LIM+1) {
                break;
            }

            count += Math.sqrt(e.value);

            nextIndex = count + index;
            if (nextIndex <= LIM) {
                res += e.value;
                q.add(new Element(e.value, nextIndex));
            } else {
                long n = LIM - (index-1);
                res += (n+1)*n/2;
                break;
            }
            ++index;
        }
        System.out.println(res);
        System.out.println(next);
        System.out.println(index);
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
