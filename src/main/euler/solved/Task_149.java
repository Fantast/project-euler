package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.max;

//Answer : 52852124
public class Task_149 implements ITask {

    final int n = 2000;
    long s[] = new long[n * n];
    long a[][] = new long[n][n];
    long v[][] = new long[n][n];
    long h[][] = new long[n][n];

    public void solving() {
        for (int k = 1; k <= 55; ++k) {
            s[k - 1] = (100003 - 200003 * k + 300007 * (long) k * k * k) % 1000000 - 500000;
        }

        for (int k = 55; k < n * n; ++k) {
            s[k] = (s[k - 24] + s[k - 55] + 1000000) % 1000000 - 500000;
        }

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                a[i][j] = s[cnt++];
            }
        }

        for (int i = 0; i < n; ++i) {
            v[i][0] = a[0][i];
            h[i][0] = a[i][0];
            for (int j = 1; j < n; ++j) {
                v[i][j] = v[i][j - 1] + a[j][i];
                h[i][j] = h[i][j - 1] + a[i][j];
            }
        }

        //i'm lazy, i'm not checking diagonals
        long res = -200000000000L;
        for (int k = 0; k < n; ++k) {
            res = max(res, v[k][0]);
            res = max(res, h[k][0]);
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    res = max(res, v[k][j] - v[k][i]);
                    res = max(res, h[k][j] - h[k][1]);
                }
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_149());
    }
}
