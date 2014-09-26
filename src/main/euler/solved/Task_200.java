package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.ArrayList;
import java.util.TreeSet;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

//Answer : 229161792008
public class Task_200 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_200());
        Logger.close();
    }

//    long MAX = Long.MAX_VALUE;
    long MAX = 700000000000L;
    long LIM1 = (long) pow(MAX, 1.0/3.0);

    public void solving() {
        MyMath.setMaxPrimesToCache(2000000);
        long primes[] = MyMath.getCachedPrimesInternal();
        int pn = primes.length;

        System.out.println(pn);

        TreeSet<Long> all = new TreeSet<>();
        for (long p : primes) {
            if (p > LIM1)break;
            long n = p * p * p;

            long LIM2 = (long) sqrt(MAX / n);

            for (long p2 : primes) {
                if (p2 > LIM2)break;

                long m = p2 * p2 * n;

                if (isok(m)) {
                    all.add(m);
                    System.out.println(all.size() + ": " + m);
                }
            }
        }
        System.out.println(new ArrayList<>(all).get(199));
    }

    int ds[] = new int[30];
    long p10[] = MyMath.pow10;
    int dc = 0;
    private boolean isok(long n) {
        long m = n;
        dc = 0;
        boolean is1 = false;
        boolean is2 = false;
        boolean has = false;
        while (m != 0) {
            int d = (int) (m % 10);
            ds[dc++] = d;
            m /= 10;

            has |= is2 && d == 2;
            is2 = is1 && d == 0;
            is1 = d == 0;
        }

        if (!has) {
            return false;
        }

        for (int i = 0; i < dc; ++i) {
            long n0 = n - ds[i] * p10[i];
            int fd = i == dc-1 ? 1 : 0;
            for (int d = fd; d <= 9; ++d) {
                long p = n0 + d * p10[i];
                if (!isProof(p)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isProof(long p) {
        return !MyMath.isPrime(p);
    }
}
