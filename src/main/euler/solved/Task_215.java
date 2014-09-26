package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.OtherUtils.deepFillLong;

//Answer : 806844323190414
public class Task_215 implements ITask {
    int n = 10;
    int m = 32;

    boolean p[][] = new boolean[n+1][m+1];

    int path[][] = new int[n+1][16];

    public void solving() {
        deepFillLong(d, -1);
        
        for (int i = 0; i <= n; ++i ) {
            for (int j = 0; j <= m; ++j) {
                p[i][j] = true;
            }
        }

        System.out.println(count(1, 0, 0));
    }

    long d[][] = new long[n+1][1<<18];
    private long count(int ind, int beg, int pathInd) {
        if (beg == m) {
            if (ind == n) {
                return 1;
            }
            p[ind][m] = true;

            int h = hash(path[ind], pathInd);
            if (d[ind][h] == -1) {
                d[ind][h] = count(ind+1, 0, 0);
            }

            return d[ind][h];
        }

        long r = 0;
        beg += 2;
        if (beg <= m && p[ind-1][beg]) {
            p[ind][beg] = false;
            path[ind][pathInd] = 0;
            r += count(ind, beg, pathInd + 1);
            p[ind][beg] = true;
        }

        beg += 1;
        if (beg <= m && p[ind-1][beg]) {
            p[ind][beg] = false;
            path[ind][pathInd] = 1;
            r += count(ind, beg, pathInd + 1);
            p[ind][beg] = true;
        }

        return r;
    }

    private int hash(int path[], int n) {
        int h = 0;
        int p2 = 1;
        for (int i = 0; i < n; ++i) {
            h += p2 * path[i];

            p2 *= 2;
        }
        
        return h;
    }

    public static void main(String[] args) {
        Tester.test(new Task_215());
    }
}
