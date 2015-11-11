package tasks;

import utils.LongFraction;
import utils.log.Logger;
import utils.pairs.Pair;

import java.util.*;

import static utils.MyMath.gcd;

//Answer :
// see: https://www.wikiwand.com/en/Stern%E2%80%93Brocot_tree
public class Task_198 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_198());
        Logger.close();
    }

    public long LIM = 1000;

//    For 10^3, 2285
//    For 10^5, < 1/100, I got 50271 = (321 + 49950) correct
//    For 10^6, < 1/100, I got 509718 = (9768 + 499950) miss some

    public long n1[] = new long[400000];
    public long d1[] = new long[400000];

    public long n2[] = new long[400000];
    public long d2[] = new long[400000];
    public long t[];

    public int c1;
    public int c2;

    Map<LongFraction, Set<From>> res = new HashMap<>();
//    Set<LongFraction> res = new HashSet<>();

    public void solving() {

        n1[0] = 0;
        d1[0] = 1;
        n1[1] = 1;
        d1[1] = 1;
        c1 = 2;

        for (long i = 2; i <= LIM; ++i) {
            progress100(i);
//            if (progress1000(i)) {
//                System.out.println(c1);
//                System.out.println(res.size());
//            }

            long num1, den1, num2, den2, num, den, g;
            num1 = n1[0];
            den1 = d1[0];

            n2[0] = num1;
            d2[0] = den1;
            c2 = 1;

            for (int j = 1; j < c1; ++j) {
                num2 = n1[j];
                den2 = d1[j];

                reg(num1, den1, num2, den2);

                num = num1 + num2;
                den = den1 + den2;

                g = gcd(num, den);
                den /= g;

                if (den == i) {
                    n2[c2] = num / g;
                    d2[c2++] = den;
                }

                n2[c2] = num2;
                d2[c2++] = den2;

                num1 = num2;
                den1 = den2;
            }

//            if (100L*n2[c2-2] >= d2[c2-2]) {
//                --c2;
//            }

            t = n1; n1 = n2; n2 = t;
            t = d1; d1 = d2; d2 = t;
            c1 = c2;

//            for (int j = 0; j < c1; ++j) {
//                System.out.print(n1[j] + "/" + d1[j] + ", ");
//            }
//            System.out.println();
//            System.out.println();
        }

        System.out.println(res.size());
        for (Map.Entry<LongFraction, Set<From>> e : new TreeMap<>(res).entrySet()) {
            if (e.getValue().size() > 1) {
                System.out.println(e.getKey() + ": " + e.getValue());
            }
        }
//        System.out.println(new TreeMap<>(res));
    }

    public void reg(long n1, long d1, long n2, long d2) {
        long n = n1*d2 + n2*d1;
        long d = d1*d2*2;
//        if (n*100 >= d) {
//            return;
//        }

        long g = gcd(n, d);
        d /= g;

        if (d <= LIM) {
            n /= g;
            LongFraction key = new LongFraction(n, d);
//            res.add(key);

            Set<From> from = res.get(key);
            if (from == null) {
                if (!(n2 == 1 && d2 == 1) && !(n1 == 0)) {
                    System.out.println(key + " : " + new From(n1, d1, n2, d2));
                }
                res.put(key, from = new TreeSet<From>());
            }
            from.add(new From(n1, d1, n2, d2));
        }
    }

    static class From implements Comparable<From>{
        final LongFraction f1;
        final LongFraction f2;

        public From(long n1, long d1, long n2, long d2) {
            f1 = new LongFraction(n1, d1);
            f2 = new LongFraction(n2, d2);
        }

        @Override
        public int compareTo(From o) {
            int cmp = f1.compareTo(o.f1);
            return cmp != 0 ? cmp : f2.compareTo(f2);
        }

        public String toString() {
            return "(" + f1 + " + " + f2 + ")";
        }
    }
}
