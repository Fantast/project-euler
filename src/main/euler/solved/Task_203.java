package solved;

import tasks.ITask;

import java.util.TreeSet;

import static java.lang.Math.sqrt;
import static utils.MyMath.getPrimesBetween;

//Answer : 34029210557338
public class Task_203 implements ITask {
    long _chs[][] = new long[200][200];

    long choose(int n, int k) {
        if (n < 0 || k < 0 || k > n) return 0;
        if (k == 0 || k == n) return _chs[n][k] = 1;
        if (_chs[n][k] != 0) return _chs[n][k];
        return _chs[n][k] = choose(n - 1, k - 1) + choose(n - 1, k);
    }

    final static int NEED = 50;
    long ps[];

    public void solving() {
        long max = 0;
        for (int i = 0; i < NEED; ++i) {
            for (int j = 0; j <= i; ++j) {
                max = Math.max(choose(i, j), max);
            }
        }

        ps = getPrimesBetween(1, (long) sqrt(max) + 1);

        for (int i = 0; i < ps.length; ++i) {
            ps[i] = ps[i] * ps[i];
        }

        TreeSet<Long> all = new TreeSet<Long>();
        for (int i = 0; i < NEED; ++i) {
            for (int j = 0; j <= i; ++j) {
                long n = choose(i, j);
                if (isSquareFree(n)) {
                    all.add(n);
                }
            }
        }

        long res = 0;
        for (long n : all) {
            res += n;
        }

        System.out.println(res);
    }

    private boolean isSquareFree(long n) {
        for (long psn : ps) {
            if (n % psn == 0) return false;
        }
        return true;
    }
}