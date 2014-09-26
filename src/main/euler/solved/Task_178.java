package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 126461847755
public class Task_178 implements ITask {

    int n = 40;
    long c[][][] = new long[n][12][1 << 10];

    public void solving() {
        for (int d = 2; d < 11; ++d) {
            c[0][d][1 << (d-1)] = 1;
        }

        for (int i = 1; i < n; ++i) {
            for (int d = 1; d < 11; ++d) {
                for (int m = 1; m < (1 << 10); ++m) {
                    int m2 = m | (1 << (d-1));
                    c[i][d][m2] += c[i - 1][d - 1][m] + c[i - 1][d + 1][m];
                }
            }
        }

        long res = 0;
        for (int i = 9; i < n; ++i) {
            for (int d = 1; d < 11; ++d) {
                res += c[i][d][(1 << 10) - 1];
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_178());
    }
}
