package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.max;
import static utils.OtherUtils.deepFillLong;

//Answer : 409511334375
public class Task_158 implements ITask {
    private int C = 26;
    public void solving() {
        deepFillLong(d, -1);

        long res = 0;
        for (int n = 3; n <= 26; ++n) {
            long cnt = 0;
            for (int i = 1; i < n; ++i) {
                for (int s = 1; s <= C; ++s) {
                    for (int morec = 0; morec < i; ++morec) {
                        int lessc = i - morec;
                        cnt += count(morec, C-s)*count(lessc, s-1)*count(n-i-1, s-1 - lessc);
                    }
                }
            }
            res = max(res, cnt);
        }
        System.out.println(res);
    }

    long d[][] = new long[C+1][C+1];
    private long count(int k, int n) {
        if (k > n) return 0;
        if (n < 0) return 0;
        if (k == 0 || k==n) return 1;

        if (d[k][n] != -1) return d[k][n];

        long r = 0;

        for (int j = n; j >= k; --j) {
            r += count(k-1, j-1);
        }

        return d[k][n] = r;
    }

    public static void main(String[] args) {
        Tester.test(new Task_158());
    }
}
