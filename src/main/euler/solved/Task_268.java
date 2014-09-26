package solved;

import tasks.ITask;
import tasks.Tester;
import utils.Combinatoric;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static utils.MyMath.getPrimesBetween;

//Answer : 785478606870985
public class Task_268 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_268());
        Logger.close();
    }

    BigInteger LIM = valueOf(10000000000000000L);

    long primes[];
    int n;

    BigInteger c[][];
    BigInteger cfs[];

    BigInteger res = ZERO;

    public void solving() {
        primes = getPrimesBetween(0, 100);

        n = primes.length;
        c = new BigInteger[n + 1][n + 1];
        c[n][n] = Combinatoric.C(n, n);
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                c[j][i] = Combinatoric.C(i, j);
            }
        }

        cfs = new BigInteger[n + 1];
        cfs[4] = ONE;
        for (int i = 5; i <= n; ++i) {
            BigInteger cf = ZERO;
            for (int j = 4; j < i; ++j) {
                cf = cf.add(c[j][i].multiply(cfs[j]));
            }
            cfs[i] = ONE.subtract(cf);
        }


        find(0, 0, ONE, false);

        System.out.println(res);
    }

    private void find(int ind, int cnt, BigInteger p, boolean add) {
        if (cnt >= 4 && add) {
            res = res.add(LIM.divide(p).multiply(cfs[cnt]));
        }

        if (ind >= n || p.compareTo(LIM) > 0) {
            return;
        }

        find(ind + 1, cnt, p, false);
        find(ind + 1, cnt + 1, p.multiply(valueOf(primes[ind])), true);
    }
}
