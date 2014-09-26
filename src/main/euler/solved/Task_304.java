package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.LongPair;

import java.util.HashSet;

import static utils.MyMath.bi;
import static utils.MyMath.lcm;

//Answer : 283988410192
public class Task_304 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_304());
        Logger.close();
    }

    long beg = 100000000000030L;
    long n = 100000;

    long MOD = 1234567891011L;
    long mfacts[] = new long[] {3, 7, 13, 67, 107, 630803};

    public void solving() {
        long period = 1;
        for (long fact : mfacts) {
            period = lcm(period, getPisanoPeriod(fact));
        }
        System.out.println("Pisano period: " + period);

        long ind = beg % period;
        System.out.println("Fibonacci index: " + ind);

        long f1 = 1;
        long f2 = 1;
        for (long i = 3; i <= ind; ++i) {
            long f3 = (f1 + f2) % MOD;
            f1 = f2;
            f2 = f3;
        }

        System.out.println("Fibonacci " + beg + "th " + "mod " + MOD + " = " + f2);

        long res = 0;
        int cnt = 0;
        for (long p = beg + 1; cnt < n; ++p) {
            long f3 = (f1 + f2) % MOD;
            f1 = f2;
            f2 = f3;

            if (bi(p).isProbablePrime(3)) {
                ++cnt;
                if (cnt % 10000 == 0) {
                    System.out.println(cnt);
                }

                res = (res + f2) % MOD;
            }
        }
        System.out.println(res);
    }

    private long getPisanoPeriod(long n) {
        HashSet<LongPair> mods = new HashSet<LongPair>();
        long f1 = 0;
        long f2 = 1;

        long res = 0;

        LongPair p = new LongPair(f1, f2);
        while (!mods.contains(p)) {
            mods.add(p);

            long f3 = (f1 + f2) % n;
            f1 = f2;
            f2 = f3;

            p = new LongPair(f1, f2);
            ++res;
        }

        return res;
    }
}
