package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.OtherUtils.deepFillLong;

//Answer : 6273134
public class Task_217 implements ITask {

    long MOD = 14348907;
    int n = 47;

    public void solving() {
        long tens[] = new long[n];
        tens[0] = 1;
        for (int i = 1; i < tens.length; ++i) {
            tens[i] = (tens[i - 1] * 10)% MOD;
        }

        deepFillLong(ds, -2);
        deepFillLong(dc, -2);

        long r = 0;
        for (int k = 1; k <= n; ++k) {
            int k2 = k / 2;

            for (int si = 0; si <= k2 * 9; ++si) {
                fill(0, k2, si);
                fill(1, k2, si);

                long s1 = ds[0][k2][si];
                long c1 = dc[0][k2][si];
                long s2 = ds[1][k2][si];
                long c2 = dc[1][k2][si];
                if (s1 < 0 || s2 < 0) {
                    continue;
                }

                long sm;
                if (k % 2 == 0) {
                    sm = (c2 * s1 % MOD * tens[k2] + c1*s2)% MOD;
                } else {
                    sm = (c2 * (s1 * 100 + c1*45) % MOD * tens[k2] + 10*c1*s2)% MOD;
                }
                r = (r + sm)% MOD;
            }
//            System.out.println("T(" + k + "):" + r);
        }

        System.out.println(r);
    }
    long ds[][][] = new long[2][n][n * 9];
    long dc[][][] = new long[2][n][n * 9];
    private void fill(int zeroAllowed, int i, int s) {
        if (ds[zeroAllowed][i][s] != -2) {
            return;
        }

        if (i == 0) {
            ds[zeroAllowed][i][s] = s == 0 ? 0 : -1;
            dc[zeroAllowed][i][s] = s == 0 ? 1 : -1;
            return;
        }

        if (s > i * 9) {
            ds[zeroAllowed][i][s] = dc[zeroAllowed][i][s] = -1;
            return;
        }
        
        if (i == 1) {
            if (s > 9 || (s == 0 && zeroAllowed == 0)) {
                ds[zeroAllowed][i][s] = dc[zeroAllowed][i][s] = -1;
                return;
            }
            ds[zeroAllowed][i][s] = s;
            dc[zeroAllowed][i][s] = 1;
            return;
        }

        long rs = -1;
        long rc = -1;
        for (int j = 0; j < 10 && s-j>=0; ++j) {
            fill(zeroAllowed, i - 1, s - j);
            long ns = ds[zeroAllowed][i - 1][s - j];
            long nc = dc[zeroAllowed][i - 1][s - j];
            if (ns != -1) {
                if (rs == -1) {
                    rs = 0;
                    rc = 0;
                }
                rs = (rs + nc*j + ns * 10)% MOD;
                rc = (rc + nc)% MOD;
            }
        }

        ds[zeroAllowed][i][s] = rs;
        dc[zeroAllowed][i][s] = rc;

        return;
    }

    public static void main(String[] args) {
        Tester.test(new Task_217());
    }
}
