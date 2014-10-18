package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static utils.MyMath.decomposePrimeAsTwoSquares;
import static utils.MyMath.getCachedPrimesInternal;

//Answer : 4137330
public class Task_224 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_224());
        Logger.close();
    }

    int LIM = 75000000;
    int maxPrimes = 4500000;

    int LIMC = LIM / 2 + 1;

    Map<Long, Gaussian> p = new HashMap<>();

    long[] primes;
    int pc;

    Map<Long, Factorization> all = new TreeMap<>();

    public void solving() {
        System.out.println("Caching primes");
        MyMath.setMaxPrimesToCache(maxPrimes);

        primes = getCachedPrimesInternal();
        pc = primes.length;

        System.out.println("Decompose primes");
        for (long prime : primes) {
            if (prime % 4 != 3) {
                long d[] = decomposePrimeAsTwoSquares(prime);
                p.put(prime, new Gaussian(min(d[0], d[1]), max(d[0], d[1])));
            }
        }

        System.out.println("Factorization");

        //genereate http://oeis.org/A001481 "Numbers that are the sum of 2 squares."
        generate(0, 1, 0, 1);

        System.out.println("Counting");

        //generate http://oeis.org/A050795 "Numbers n such that n^2 - 1 is a sum of two nonzero squares"
        long prev = -1;
        long res = 0;
        for (Map.Entry<Long, Factorization> e : all.entrySet()) {
            long curr = e.getKey();

            if (curr == prev + 1) {
                long c = 2 * curr - 1;
                if (c > LIMC) {
                    break;
                }
                res += count(c);
            }

            prev = curr;
        }

        System.out.println(res);
    }

    long LIMAB;
    long c;
    long cmult;
    long cfacts[] = new long[20];
    int cpows[] = new int[20];
    int cfc;
    Set<Long> counted = new HashSet<>();

    private long count(long c) {
        Factorization f1 = all.get(c - 1);
        Factorization f2 = all.get(c + 1);

        //merge factorizations

        this.c = c;
        LIMAB = LIM - c;
        cmult = f1.multiple * f2.multiple;
        counted.clear();

        int[] pows1 = f1.pows;
        int[] pows2 = f2.pows;
        long[] facts1 = f1.facts;
        long[] facts2 = f2.facts;
        int c1 = facts1.length;
        int c2 = facts2.length;

        int i = 0;
        int j = 0;
        cfc = 0;
        if (c1 != 0 && c2 != 0 && facts1[0] == 2 && facts2[0] == 2) {
            cmult *= 2;
            i = 1;
            j = 1;
        }

        for (; i < c1 || j < c2; ) {
            while (i < c1 && (j == c2 || facts1[i] < facts2[j])) {
                cfacts[cfc] = facts1[i];
                cpows[cfc] = pows1[i];
                ++cfc;
                ++i;
            }

            while (j < c2 && (i == c1 || facts2[j] < facts1[i])) {
                cfacts[cfc] = facts2[j];
                cpows[cfc] = pows2[j];
                ++cfc;
                ++j;
            }

            while (i < c1 && j < c2 && facts1[i] == facts2[j]) {
                cfacts[cfc] = facts1[i];
                cpows[cfc] = pows1[i] + pows2[j];
                ++cfc;
                ++i;
                ++j;
            }
        }

        if (cfacts[0] == 2) {
            return cnt(1, cmult, cmult);
        } else {
            return cnt(0, cmult, 0);
        }
    }

    private long cnt(int ind, long a, long b, long a2, long b2) {
        //(a + i*b)*(a2 + i*b2)
        return cnt(ind, a * a2 - b * b2, a * b2 + b * a2);
    }

    private long cnt(int ind, long a, long b) {
        if (ind == cfc) {
            long aa = Math.abs(a);
            long bb = Math.abs(b);
            if (!counted.contains(aa) && !counted.contains(bb)) {
                counted.add(aa);
                if (aa + bb + c < LIM) {
                    return 1;
                }
//                System.out.println(a + ", " + b + ", " + c + ": " + (a * a + b * b) + " <> " + (c * c - 1));
            }
            return 0;
        }

        long prime = cfacts[ind];

        Gaussian pf = p.get(prime);

        long fa = pf.a;
        long fb = pf.b;

        int pow = cpows[ind];
        long aplus[] = new long[pow + 1];
        long bplus[] = new long[pow + 1];

        aplus[0] = 1;
        bplus[0] = 0;
        for (int i = 1; i <= pow; ++i) {
            //(pa + i*pb)*(fa + i*fb)
            aplus[i] = fa * aplus[i - 1] - fb * bplus[i - 1];
            bplus[i] = fa * bplus[i - 1] + fb * aplus[i - 1];
        }

        long res = cnt(ind + 1, a, b, aplus[pow], bplus[pow]);

        int mend = ind == 0 || (ind == 1 && cfacts[0] == 2) ? pow / 2 : pow;
        long pa = 1;
        long pb = 0;
        for (int i = 1; i <= mend; ++i) {
            //(pa + i*pb)*(fa - i*fb)
            long na = pa * fa + pb * fb;
            long nb = pb * fa - pa * fb;

            //(pa + i*pb)*(na + i*nb)
            pa = na * aplus[pow - i] - nb * bplus[pow - i];
            pb = na * bplus[pow - i] + nb * aplus[pow - i];

            res += cnt(ind + 1, a, b, pa, pb);

            pa = na;
            pb = nb;
        }

        return res;
    }

    long facts[] = new long[20];
    int factPows[] = new int[20];

    public void generate(int ind, long multiple, int fc, long n) {
        if (n > LIMC) {
            return;
        }

        all.put(n, new Factorization(multiple, facts, factPows, fc));

        long nl = LIMC / n;

        for (int i = ind; i < pc; ++i) {
            long next = primes[i];
            boolean takeTwice = next % 4 == 3;

            long nn = (takeTwice ? next * next : next);

            if (!takeTwice && (nn < 0 || nn > nl)) {
                break;
            }

            if (takeTwice) {
                generate(i, multiple * nn, fc, n * nn);
            } else {
                if (fc == 0) {
                    facts[fc] = next;
                    factPows[fc] = 1;
                    generate(i, multiple, fc + 1, n * nn);
                } else if (i > ind) {
                    facts[fc] = next;
                    factPows[fc] = 1;
                    generate(i, multiple, fc + 1, n * nn);
                } else {
                    factPows[fc - 1]++;
                    generate(i, multiple, fc, n * nn);
                    factPows[fc - 1]--;
                }
            }
        }
    }

    public static class Factorization {
        long multiple;
        long facts[];
        int pows[];

        public Factorization(long multiple, long facts[], int pows[], int fcount) {
            int length = fcount;
            int start = 0;
            int delta0 = 0;
            if (fcount > 0 && facts[0] == 2) {
                int twoCount = pows[0] / 2 * 2;
                multiple *= (1 << twoCount);

                if (twoCount == pows[0]) {
                    length--;
                    start = 1;
                } else {
                    delta0 = twoCount;
                }
            }

            this.multiple = (long) Math.sqrt(multiple);
            this.facts = new long[length];
            this.pows = new int[length];

            System.arraycopy(facts, start, this.facts, 0, length);
            System.arraycopy(pows, start, this.pows, 0, length);

            if (delta0 > 0) {
                this.pows[0] -= delta0;
            }
        }

        @Override
        public String toString() {
            String res = multiple + " * {";
            for (int i = 0; i < facts.length; ++i) {
                if (i > 0) {
                    res += ", ";
                }
                res += facts[i] + ": " + pows[i];
            }
            res += "}";
            return res;
        }
    }

    public static class Gaussian {
        long a;
        long b;

        public Gaussian(long a, long b) {
            this.a = a;
            this.b = b;
        }
    }
}
