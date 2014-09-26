package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static java.lang.Math.min;

//Answer :
public class Task_150 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_150());
        Logger.close();
    }

    private static final long MOD = 1<<20;
    private static final long TWO_POW_19 = 1<<19;

    int n = 1000;
    long tr[][] = new long[n][n];
    long sums[][] = new long[n][n];

    public void solving() {
        long t = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                t = (615949*t + 797807) % MOD;
                tr[i][j] = t - TWO_POW_19;
                sums[i][j] = tr[i][j] + ((j==0) ? 0 : sums[i][j-1]);
            }
        }

        long best = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            System.out.println("Processing Row: " + i);
            for (int j = 0; j <= i; ++j) {
                long res = tr[i][j];
                long r = res;
                for (int k = i + 1; k < n; ++k) {
                    r += sums[k][j+k-i] - sums[k][j] + tr[k][j];
                    res = min(res, r);
                }
                if (res < best) {
                    best = res;
                    System.out.println("  Better in: " + i + ", " + j);
                }
            }
        }

        System.out.println( best );
    }
}
