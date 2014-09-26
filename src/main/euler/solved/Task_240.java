package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;

import static java.math.BigInteger.*;

//Answer : 7448717393364181966
public class Task_240 implements ITask {

    int mx = 12;
    int n = 20;
    int m = 10;
    int sm = 70;
    BigInteger f[] = new BigInteger[n + 1];

    public void solving() {
        f[0] = ONE;
        for (int i = 1; i < f.length; ++i) {
            f[i] = f[i - 1].multiply(valueOf(i));
        }
        System.out.println(count(1, n, sm, f[n]));
    }

    public static void main(String[] args) {
        Tester.test(new Task_240());
    }

    private BigInteger count(int k, int dcnt, int sm, BigInteger perms) {
        if (sm == 0) {
            return dcnt == 0 ? perms : ZERO;
        }
        if (k == mx + 1) {
            return ZERO;
        }

        BigInteger r = ZERO;
        for (int cnt = 0; cnt <= dcnt; ++cnt) {
            int sm2 = sm;

            if (dcnt <= m) {
                sm2 -= cnt * k;
            } else if (dcnt - cnt < m) {
                sm2 -= (cnt - (dcnt - m)) * k;
            }

            if (sm2 < 0) {
                break;
            }

            r = r.add(count(k + 1, dcnt - cnt, sm2, perms.divide(f[cnt])));
        }

        return r;
    }
}
