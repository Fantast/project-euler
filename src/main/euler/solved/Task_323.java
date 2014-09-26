package solved;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

//Answer : 6.3551758451
public class Task_323 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_323());
        Logger.close();
    }

    int n = 32;

    public void solving() {
        double p[] = new double[n+1];
        double pn[] = new double[n+1];
        for (int i = 0; i < n; ++i) {
            p[i] = 0;
        }
        p[n] = 1;

        double res = 0;
        for (int k = 1; k < 1000; ++k) {
            for (int i = 0; i <= n; ++i) {
                pn[i] = 0;
                for (int j = i==0?1:i; j <= n; ++j) {
                    pn[i] += p[j] * numberCount(n - i, j - i) * p2(n-j) / p2(n);
                }
            }

            System.arraycopy(pn, 0, p, 0, n+1);

            res += p[0] * k;
        }

        System.out.println(OtherUtils.formatDouble(res, 12));
    }

    private double p2(long k) {
        return 1L << k;
    }

    long c[][] = new long[n+1][n+1];
    private long numberCount(int bitCount, int zeroCount) {
        if (zeroCount == 0) {
            return 1;
        }

        if (c[bitCount][zeroCount] != 0) {
            return c[bitCount][zeroCount];
        }

        long res = 0;
        for (int i = 0; i <= bitCount - zeroCount; ++i) {
            res += numberCount(bitCount - i - 1, zeroCount - 1);
        }

        return c[bitCount][zeroCount] = res;
    }
}
