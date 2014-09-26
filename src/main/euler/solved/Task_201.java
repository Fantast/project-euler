package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 115039000
public class Task_201 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_201());
        Logger.close();
    }

    int mx;
    int n = 100;
    int need = 50;
    int cnt[][];
    int cnt2[][];

    public void solving() {
        mx = 0;
        for (int i = n - need + 1; i <= n; ++i) {
            mx += i * i;
        }

        cnt = new int[need + 1][mx + 1];
        cnt2 = new int[need + 1][mx + 1];

        cnt[0][0] = 1;
        for (int a = 1; a <= n; ++a) {
            int a2 = a*a;
            for (int i = 0; i <= need; ++i) {
                for (int j = 0; j <= mx; ++j) {
                    cnt2[i][j] = cnt[i][j];
                    if (i > 0 && j >= a2) {
                        cnt2[i][j] += cnt[i - 1][j - a2];
                    }
                }
            }
            int[][] t = cnt;
            cnt = cnt2;
            cnt2 = t;
        }

        int res = 0;
        for (int n = 0; n <= mx; ++n) {
            if (cnt[50][n] == 1) {
                res += n;
            }
        }
        System.out.println(res);
    }
}
