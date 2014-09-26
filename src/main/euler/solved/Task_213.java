package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Arrays;

//Answer : 330.721154
public class Task_213 implements ITask {

    int s = 30;
    int s2 = s * s;
    double p[][][][] = new double[51][s2][s][s];
    int di[] = {-1, +1, 0, 0};
    int dj[] = {0, 0, -1, +1};

    public void solving() {
        int bi = 0;
        for (int i = 0; i < s; ++i) {
            for (int j = 0; j < s; ++j) {
                p[0][bi++][i][j] = 1.0;
            }
        }

        for (int k = 1; k <= 50; ++k) {
            for (int n = 0; n < s2; ++n) {
                for (int i = 0; i < s; ++i) {
                    for (int j = 0; j < s; ++j) {
                        double np = p[k - 1][n][i][j] / neiboursCnt(i, j);

                        for (int d = 0; d < 4; ++d) {
                            int ni = i + di[d];
                            int nj = j + dj[d];

                            if (ni < 0 || ni >= s || nj < 0 || nj >= s) {
                                continue;
                            }

                            p[k][n][ni][nj] += np;
                        }
                    }
                }
            }
        }

        double ps[] = new double[s2];
        Arrays.fill(ps, 1.0);
        for (int n = 0; n < s2; ++n) {
            for (int i = 0; i < s; ++i) {
                for (int j = 0; j < s; ++j) {
                    ps[i*s + j] *= 1 - p[50][n][i][j];
                }
            }
        }

        double res = 0;
        for (double pi : ps) {
            res += pi;
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_213());
    }

    private double neiboursCnt(int i, int j) {
        if ((i == 0 && j == 0) ||
                (i == 0 && j == s-1) ||
                (i == s-1 && j == 0) ||
                (i == s-1 && j == s-1)) {
            return 2;
        }

        if (i == 0 || i == s-1 || j == 0 || j == s-1) {
            return 3;
        }

        return 4;
    }
}
