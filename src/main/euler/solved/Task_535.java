package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.PriorityQueue;

//Answer : 611778217
public class Task_535 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_535());
        Logger.close();
    }

//    public long LIM = 20;
//    public long LIM = 1000;
//    public long LIM = 10000;
//    public long LIM = 10000000;
//    public long LIM = 1000000000;
//    public long LIM = 100000000000000000L;
    public long LIM = 1000000000000000000L;

    public long MOD = 1000000000;
    public BigInteger bMOD = bi(MOD);

    PriorityQueue<Element> q = new PriorityQueue<>();

    public void solving() {
        long res = 0;
        long count = 0;
        long count2 = 0;
        long next = 2;
        long nextIndex;
        long nextOverride = -1;
        long index = 1;

        q.add(new Element(1, 1));
        TOP:
        while (!q.isEmpty()) {
            long value = nextOverride == -1 ? q.poll().value : nextOverride;
//            long index = e.index;

            long sqn = (long) Math.sqrt(value);
            long sqn2min = (long) Math.sqrt(count + 1);
            long sqn2max = (long) Math.sqrt(count + sqn);

            long last = count + 1;
            long index2 = index - 1 + count;
            long dc2;
            long di2;
            for (long sqn2 = sqn2min; sqn2 <= sqn2max; ++sqn2) {
                long tick = min((sqn2+1)*(sqn2+1), count + sqn + 1);
                dc2 = (tick - last)*sqn2;
                di2 = (tick - last);
                if (count2 + index2 + dc2 + di2 > LIM) {
                    long k = (LIM - count2 - index2 + 1 + sqn2 - 1)/sqn2; //ceil((lim - (c2+i2) + 1)/(sqn2-1))
                    dc2 = k*sqn2;
                    di2 = k;

                    count2 += dc2;
                    index2 += di2;

                    long n = LIM - (index2 - 1);
//                    res += (n + 1) * n / 2 + (last - 1 + k) * (last - 1 + k - 1) / 2;

                    res = inc(res, sumn(n));
                    res = inc(res, sumn(last - 1 + k - 1));

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
//                res += (n + 1) * n / 2 + (count + 1) * count / 2;

                res = inc(res, sumn(n));
                res = inc(res, sumn(count));

                break TOP;
            }
            if (nextIndex <= LIM) {
//                res += value;
                res = inc(res, value);
                q.add(new Element(value, nextIndex));
            }

            index++;

            Element nextE = q.peek();
            if (index != nextE.index) {
                nextOverride = next++;
            } else {
                nextOverride = -1;
            }
        }
        System.out.println(res);
    }

    public long sumn(long n) {
//        if (n % 2 == 0) {
//            return (n + 1) * (n / 2) % MOD;
//        } else {
//            return ((n + 1) / 2) * n % MOD;
//        }
        return bi(n).multiply(bi(n+1)).divide(bi(2)).mod(bMOD).longValue();
    }

    public long inc(long n, long dn) {
        return (n + dn) % MOD;
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
