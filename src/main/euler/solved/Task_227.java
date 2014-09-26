package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.OtherUtils.deepFillDouble;

//Answer : 3780.618622
public class Task_227 implements ITask {

    int STEPS = 100000;
//    int STEPS = 10;
    int n = 100;
    double p1[][] = new double[n][n];
    double p2[][] = new double[n][n];

    public void solving() {
        p1[0][n / 2] = 1;

        double res = 0;
        for (double k = 0; k < STEPS; ++k) {
            if (k % (STEPS / 100) == 0) {
                System.out.println("Progress: " + k);
            }
            double pc[][] = (k % 2 == 0) ? p1 : p2;
            double pn[][] = (k % 2 == 1) ? p1 : p2;

            deepFillDouble(pn, 0);

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i == j) {
                        continue;
                    }
                    for (int t1 = -1; t1 <= +1; ++t1) {
                        for (int t2 = -1; t2 <= +1; ++t2) {
                            double m1 = (t1 == 0) ? 4 : 1;
                            double m2 = (t2 == 0) ? 4 : 1;
                            pn[(i + n + t1) % n][(j + n + t2) % n] += m1 * m2 * pc[i][j] / 36.0;
                        }
                    }
                }
            }

            double pyes = 0;
            for (int i = 0; i < n; ++i) {
                pyes += pn[i][i];
            }
            System.out.println(k + 1 + ": " + pyes);
            res += (k + 1) * pyes;
        }

        System.out.println(res);
        System.out.println(String.format("%.6f", res));
    }

    public static void main(String[] args) {
        Tester.test(new Task_227());
    }
}
