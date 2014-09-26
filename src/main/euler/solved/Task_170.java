package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.max;
import static utils.MyMath.*;

//Answer : 9857164023
public class Task_170 implements ITask {
    private static final long all = (1<<10)-1;

    public void solving() {
        find(0, 0);
        System.out.println(res);
    }

    long res = 0;
    long n = 0;
    int nd[] = new int[10];
    private void find(int i, int msk) {
        if (i == 10) {
            check();
            return;
        }
        int ed = (i==0)?1:0;
        for (int d = 9; d >= ed; --d) {
            if (!isBitSet(msk, d)) {
                n = n*10 + d;
                nd[i] = d;
                find(i+1, setBit(msk, d));
                n /= 10;
            }
        }
    }
    
    private void check() {
        long a = 0;
        for (int i = 0; i < 8; ++i) {
            a = a*10 + nd[i];
            if (nd[i] == 0 || nd[i+1]==0) continue;

            long b = 0;
            for (int j = i+1; j < 9; ++j) {
                b = b*10 + nd[j];
                if (b == 0 || nd[j+1]==0) continue;

                long msk = checkPandigital(a*b, 0);
                if (msk == -1) continue;

                long c = n % pow10[9-j];

                msk = checkPandigital(a*c, msk);
                if (msk == all) {
                    res = max(res, Long.parseLong(a*b + "" + a*c));
                    return;
                }
            }
        }
    }

    private long checkPandigital(long n, long used) {
        while (n != 0) {
            int d = (int) (n % 10);
            if (isBitSet(used, d)) return -1;

            used = setBit(used, d);

            n /= 10;
        }
        return used;
    }

    public static void main(String[] args) {
        Tester.test(new Task_170());
    }
}
