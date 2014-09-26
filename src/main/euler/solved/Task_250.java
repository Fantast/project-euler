package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;

//Answer : 1425480602091519
public class Task_250 implements ITask {
    final int n = 250250;
    int ps[] = new int[n+1];
    long d[][] = new long[n+1][250];

    long MOD     = 100000000000000000L;
    long ANS_MOD = 10000000000000000L;

    public void solving() {
        for (int i = 1; i <= n; ++i) {
            BigInteger bi = BigInteger.valueOf(i);
            ps[i] = bi.modPow(bi, BigInteger.valueOf(250)).intValue();
        }

        for (int i = 1; i <= n; ++i) {
            int curr = ps[i];
            d[i][curr] = 1;
            for (int j = 0; j < 250; ++j) {
                //all previous counts
                d[i][j] = (d[i][j] + d[i-1][j]) % MOD;
                d[i][ (j + curr)%250  ] = (d[i][ (j + curr)%250  ] + d[i-1][j]) % MOD;
            }
        }

        System.out.println(d[n][0] % ANS_MOD);
    }

    public static void main(String[] args) {
        Tester.test(new Task_250());
    }
}
