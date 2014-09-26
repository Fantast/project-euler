package solved;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;

//Answer : 38182
public class Task_109 implements ITask {
    private int doubles[] = new int[21];
    private static final int MAX = 100;

    public void solving() {
        for (int i = 1; i <= 20; ++i) {
            doubles[i-1] = 2*i;
        }
        doubles[20] = 50;

        OtherUtils.deepFillLong(d, -1);

        long res = 0;
        for (int total = MAX-1; total > 1; --total) {
            for (int dbl : doubles) {
                int tot = total - dbl;
                if (tot >= 0) {
                    res += count(1, 1, 1, tot);
                } else {
                    break;
                }
            }
        }

        System.out.println(res);
    }

    long d[][][][] = new long[3][22][4][MAX];
    private long count(int cnt, int beg, int begMult, int tot) {
        if (tot == 0) return 1;
        if (cnt == 3) return 0;

        if (d[cnt][beg][begMult][tot] != -1) return d[cnt][beg][begMult][tot];
        long r = 0;
        for (int i = beg; i <= 21; ++i) {
            int ival = i == 21 ? 25 : i;
            int mBeg = (i == beg) ? begMult : 1;
            int mEnd = (i == 21) ? 2 : 3;

            for (int mult  = mBeg; mult <= mEnd; ++mult) {
                int tn = tot - mult*ival;
                if (tn < 0) break;

                r += count(cnt+1, i, mult, tn);
            }
        }
        return d[cnt][beg][begMult][tot] = r;
    }

    public static void main(String[] args) {
        Tester.test(new Task_109());
    }
}