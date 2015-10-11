package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.isBitSet;

//Answer : 916312072618778883
public class Task_24 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_24());
        Logger.close();
    }

    long a[][][] = new long[33][32][32];

    public void solving() {
        for (int f = 0; f < 32; ++f) {
            for (int t = f+1; t < 32; ++t) {
                a[1][f][t] = t-f;
            }
        }

        for (int d = 2; d <= 32; ++d) {
            for (int f = d-1; f < 32; ++f) {
                for (int t = f+1; t < 32; ++t) {
                    a[d][f][t] = a[d][f][t-1] + 1 + a[d-1][d-2][t-1];
                }
            }
        }

        long res = 0;
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            progress10000000(i);
            res += count(i);
        }
        System.out.println(res);
    }

    private long count(int n) {
        long r = 1;
        int o = 0;
        int oi = 0;
        for (int i = 0; i < 32; i++) {
            if (isBitSet(n, i)) {
                if (o != 0) {
                    r += a[o][oi][i - 1];
                }
                o++;
                oi = i;
            }
        }
        if (!isBitSet(n, 31)) {
            r += a[o][oi][31];
        }

        return r;
    }
}
