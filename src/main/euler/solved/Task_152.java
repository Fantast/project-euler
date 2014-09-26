package solved;

import tasks.ITask;
import tasks.Tester;
import utils.BigFraction;

import java.math.BigInteger;

//Answer : 301
//runs in ~9 min, can be optimized though...
public class Task_152 implements ITask {
    private static final int MAX_NUM = 80;
    private static final double halfd = 1.0/2.0;
    private static final double EPS = 1e-11;

    long vp[] = {2, 3, 5, 7, 13};
    int n;

    int p[] = new int[MAX_NUM];
    double usq[] = new double[MAX_NUM];
    double sm[] = new double[MAX_NUM];

    private static int res = 0;

    public void solving() {
//        primes = MyMath.getPrimesBetween(0, n);
//        pn = primes.length;
//
//        for (int i = 3; i < pn; ++i) {
//            long p = primes[i];
//            BigInteger p2 = valueOf(p * p);
//            int c = (int) (n / p);
//
//            System.out.println(p);
//
//            for (int m = 1; m < (1<<c); ++m) {
//                BigFraction val = BigFraction.ZERO;
//                for (int j = 1; j <= c; ++j) {
//                    if (isBitSet(m, j - 1)) {
//                        val = val.add(new BigFraction(1, j * j));
//                    }
//                }
//
//                if (val.numer.mod(p2).equals(ZERO)) {
//                    System.out.print("    ");
//                    for (int j = 1; j <= c; ++j) {
//                        if (isBitSet(m, j-1)) {
//                            System.out.print(j*p + " ");
//                        }
//                    }
//                    System.out.println();
//                }
//            }
//        }
//  ==> vp[] = {2, 3, 5, 7, 13}

        n = 0;
        for (int i = 2; i <= MAX_NUM; ++i) {
            int k = i;
            for (long p : vp) {
                while (k%p == 0) {
                    k /= p;
                }
            }

            if (k==1) {
                p[n] = i;
                usq[n++] = 1.0/(i*i);
            }
        }

        sm[0] = usq[0];
        for (int i = 1; i < n; ++i) {
            sm[i] = sm[i-1] + usq[i];

        }

        find(0, 0, 0);
        System.out.println(res);
    }

    private int path[] = new int[MAX_NUM+1];
    private void find(int ind, int beg, double sum) {
        if (equal(sum, halfd) && check(ind)) {
            res++;
            for (int i = 0; i < ind; ++i) {
                System.out.print(path[i] + " ");
            }

            System.out.println();

            return;
        }

        for (int i = beg; i < n; ++i) {
            double s2 = sum + usq[i];
            if (more(s2, halfd)) {
                continue;
            }

            if (more(halfd, s2 + sm[n - 1] - sm[i])) {
                break;
            }

            path[ind] = p[i];
            find(ind+1, i+1, s2);
        }
    }

    BigInteger ONE = BigInteger.valueOf(1);
    BigInteger TWO = BigInteger.valueOf(2);
    private boolean check(int ind) {
        BigFraction f = BigFraction.ZERO;
        for (int i = 0; i < ind; ++i) {
            f = f.add(new BigFraction(1, path[i]*path[i]));
        }
        return f.numer.equals(ONE) && f.denom.equals(TWO);
    }

    private boolean equal(double a, double b) {
        return Math.abs(a - b) <= EPS;
    }

    private boolean more(double a, double b) {
        return a - b > EPS;
    }

    public static void main(String[] args) {
        Tester.test(new Task_152());
    }
}