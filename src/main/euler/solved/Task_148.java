package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 2129970655314432
public class Task_148 implements ITask {
    private static final int LIM = 1000000000;

    long cnt[] = new long[13];
    long p7[] = new long[13];
    public void solving() {
        cnt[0] = 1;
        p7[0] = 1;
        for (int i = 1; i < cnt.length; ++i) {
            p7[i] = 7 * p7[i-1];
            cnt[i] = (1+7)*7/2*cnt[i-1];
        }

        System.out.println(count(cnt.length - 1, LIM));
    }

    private long count(int ind, long rows) {
        if (rows == 0) return 0;

        long res = 0;

        long c7pred = cnt[ind-1];
        long p7pred = p7[ind-1];

        long r = 0;
        int rw = 1;
        for (; r + p7pred<=rows; ++rw, r += p7pred) {
            res += rw * c7pred;
        }

        res += rw*count(ind - 1, rows - r);

        return res;
    }

    public static void main(String[] args) {
        Tester.test(new Task_148());
    }
}