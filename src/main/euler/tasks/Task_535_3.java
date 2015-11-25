package tasks;

import utils.log.Logger;

import java.util.PriorityQueue;

//Answer :
public class Task_535_3 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_535_3());
        Logger.close();
    }

//        public long LIM = 20;
//    public long LIM = 1000;
//    public long LIM = 10000;
//    public long LIM = 10000000;
    public long LIM = 1000000000;
//    public long LIM = 10000000000000000L;
//    public long LIM = 1000000000000000000L;

    public long MOD = 1000000000;

    PriorityQueue<Element> q = new PriorityQueue<>();

    public void solving() {
        long res = 0;
        long count = 0;
        long count2 = 0;
        long next = 2;
        long nextIndex;

        q.add(new Element(1, 1));
        TOP:
        while (!q.isEmpty()) {
            Element e = q.poll();
            long index = e.index;

            long sqn = (long) Math.sqrt(e.value);
            long sqn2min = (long) Math.sqrt(count + 1);
            long sqn2max = (long) Math.sqrt(count + sqn);

            long last = count + 1;
            long index2 = index - 1 + count;
            long dc2;
            long di2;
            for (long sqn2 = sqn2min+1; sqn2 <= sqn2max+1; ++sqn2) {
                long tick = min(sqn2*sqn2, count + sqn + 1);
                dc2 = (tick - last)*(sqn2 - 1);
                di2 = (tick - last);
                if (count2 + index2 + dc2 + di2 > LIM) {
                    long k = (LIM - count2 - index2 + 1 + sqn2 - 1)/(sqn2 - 1); //ceil((lim - (c2+i2) + 1)/(sqn2-1))
                    dc2 = k*(sqn2 - 1);
                    di2 = k;

                    count2 += dc2;
                    index2 += di2;

                    long n = LIM - (index2 - 1);
                    res += (n + 1) * n / 2 + (last - 1 + k) * (last - 1 + k - 1) / 2;
                    break TOP;
                }

                count2 += dc2;
                index2 += di2;

                last = tick;
            }

            count += sqn;
            nextIndex = count + index;

            count2 += sqn;
            index2 = nextIndex;
            if (count2 + index2 > LIM) {
                long n = LIM - (index2 - 1);
                res += (n + 1) * n / 2 + (count + 1) * count / 2;
                break TOP;
            }
            if (nextIndex <= LIM) {
                res += e.value;
                q.add(new Element(e.value, nextIndex));
            }

            Element nextE = q.peek();
            index++;
            while (index < nextE.index) {
                q.add(new Element(next++, index++));
            }
        }
        System.out.println(res%MOD);
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
