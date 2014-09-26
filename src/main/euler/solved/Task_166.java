package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 7130034
public class Task_166 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_166());
        Logger.close();
    }

    int d1, d2;
    int r[] = new int[4];
    int c[] = new int[4];
    int sum;
    long count;

    public void solving() {
        for (sum = 0; sum <= 36; ++sum) {
            find(0, 0);
        }
        System.out.println(count);
    }

    private void find(int i, int j) {
        if (j == 4) {
            find(i + 1, 0);
            return;
        }
        if (i == 4) {
            count++;
            return;
        }

        int mxd = 9;
        if (i == j) {
            mxd = Math.min(mxd, sum - d1);
        }
        if (i == 3 - j) {
            mxd = Math.min(mxd, sum - d2);
        }

        mxd = mmin(mxd, sum - r[i], sum - c[j]);
        for (int d = 0; d <= mxd; ++d) {
            if (j == 3 && d != sum - r[i]) {
                continue;
            }
            if (i == 3 && d != sum - c[j]) {
                continue;
            }
            if (i == 3 && j == 3 && d != sum - d1) {
                continue;
            }
            if (i == 3 && j == 0 && d != sum - d2) {
                continue;
            }

            set(i, j, d);
            find(i, j + 1);
            unset(i, j, d);
        }
    }

    private void unset(int i, int j, int d) {
        r[i] -= d;
        c[j] -= d;
        if (i == j) {
            d1 -= d;
        }
        if (i == 3 - j) {
            d2 -= d;
        }
    }

    private void set(int i, int j, int d) {
        r[i] += d;
        c[j] += d;
        if (i == j) {
            d1 += d;
        }
        if (i == 3 - j) {
            d2 += d;
        }
    }

    private int mmin(int... ns) {
        int res = ns[0];
        for (int n : ns) {
            res = Math.min(res, n);
        }
        return res;
    }
}
