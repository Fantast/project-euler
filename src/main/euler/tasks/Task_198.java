package tasks;

import utils.LongFraction;
import utils.log.Logger;

import java.util.HashMap;
import java.util.Map;

import static utils.MyMath.gcd;

//Answer :
// see: https://www.wikiwand.com/en/Stern%E2%80%93Brocot_tree
public class Task_198 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_198());
        Logger.close();
    }

    public long LIM = 100000;

//    For 10^3, 2285
//    For 10^5, < 1/100, I got 50271 = (321 + 49950) correct
//    For 10^6, < 1/100, I got 509718 = (9768 + 499950) miss some

    Map<LongFraction, From> res = new HashMap<>();

    public void solving() {
        for (int i = 1; i <= LIM; ++i) {
            progress1000(i);
            farey(i);
        }
        System.out.println(res.size());



//        for (Map.Entry<LongFraction, From> e : new TreeMap<>(res).entrySet()) {
//            From f = e.getValue();
//            boolean print = !(f.f2.numer == 1 && f.f2.denom == 1) && f.f1.numer != 0;
//            print &= lcm(f.f1.denom, f.f2.denom)*2 != e.getKey().denom;
//            if (print) {
//                System.out.println(e.getKey() + " -> " + e.getValue());
//            }
//        }
    }

    private void farey(long n) {
        long a = 0;
        long b = 1;
        long c = 1;
        long d = n;

//        System.out.println(a + "/" + b);
        while (c <= n) {
            long k = (n + b) / d;
            long aprev = a;
            long bprev = b;

            a = c;
            b = d;
            c = k * c - aprev;
            d = k * d - bprev;

            if (!reg(aprev, bprev, a, b, n)) {
                break;
            }
        }
    }

    public boolean reg(long a1, long b1, long a2, long b2, long n) {
        if (b1 != n && b2 != n) {
            return true;
        }
        long a = a1 * b2 + a2 * b1;
        long b = b1 * b2 * 2;

        if (a*100 >= b) {
            return false;
        }

        long g = gcd(a, b);
        b /= g;

        if (b <= LIM) {
            a /= g;

            From f = new From(a1, b1, a2, b2);
            LongFraction key = new LongFraction(a, b);

            boolean print = !(a2 == 1 && b2 == 1) && a1 != 0;
//            print &= lcm(b1, b2)*2 != b;
//            print &= gcd(b1, b2) != 1;
            if (print) {
//                Logger.out.println(key + " -> " + f);
                System.out.println(key + " -> " + f);
                res.put(key, f);
            }

//            res.put(key, f);
        }

        return true;
    }

    static class From implements Comparable<From> {
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
