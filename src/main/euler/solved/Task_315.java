package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Arrays;

import static utils.MyMath.*;

//Answer : 13625242
public class Task_315 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_315());
        Logger.close();
    }

    int bars[][] = new int[][]{
            new int[]{0, 1, 2, 4, 5, 6},
            new int[]{2, 5},
            new int[]{0, 2, 3, 4, 6},
            new int[]{0, 2, 3, 5, 6},
            new int[]{1, 2, 3, 5},
            new int[]{0, 1, 3, 5, 6},
            new int[]{0, 1, 3, 4, 5, 6},
            new int[]{0, 1, 2, 5},
            new int[]{0, 1, 2, 3, 4, 5, 6},
            new int[]{0, 1, 2, 3, 5, 6}
    };

    int dd[][] = new int[10][10];

    public final int A = 10000000;
    public final int B = 20000000;

    public void solving() {
        for (int d1 = 0; d1 < 10; ++d1) {
            for (int d2 = 0; d2 < 10; ++d2) {
                int r = 0;
                for (int i = 0; i < bars[d1].length; ++i) {
                    boolean found = false;
                    for (int j = 0; j < bars[d2].length; ++j) {
                        if (bars[d2][j] == bars[d1][i]) {
                            found = true;
                            break;
                        }
                    }
                    if (found) ++r;
                }
                dd[d1][d2] = r*2;
            }
        }

        Arrays.fill(diff, -1);
        long[] pr = getPrimesBetween(A, B);
        long res = 0;
        for (long p : pr) {
            res += getCostDiff((int) p);
        }

        System.out.println(res);
    }

    public long diff[] = new long[3200];

    private long getCostDiff(int p) {
        if (p < 10) return 0;
        if (p < diff.length && diff[p] != -1) {
            return diff[p];
        }

        int pn = sumOfDigits(p);
        long res = getSingleDiff(p, pn) + getCostDiff(pn);

        if (p < diff.length) {
            diff[p] = res;
        }

        return res;
    }

    private long getSingleDiff(int n1, int n2) {
        n1 %= pow10[numberOfDigits(n2)];

        int res = 0;
        while (n2 != 0) {
            res += dd[n1 % 10][n2 % 10];

            n1 /= 10;
            n2 /= 10;
        }

        return res;
    }
}
