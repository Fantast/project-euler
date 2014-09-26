package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 2269
public class Task_121 implements ITask {
    int n = 15;
    public void solving() {
        double p[][] = new double[n+1][n+1];
        p[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            double pBlue = 1.0 / (double)(i+1);
            double pRed = i / (double)(i+1);
            for (int j = 0; j <=n; ++j ) {
                p[i][j] = p[i-1][j] * pRed;
                if (j != 0) {
                    p[i][j] += p[i-1][j-1]*pBlue;
                }
            }
        }

        double pWin = 0;
        for (int i = n/2+1; i <= n; ++i) {
            pWin += p[n][i];
        }

        long A = (long) (1 / pWin - 1);
        System.out.println(A+1);
    }

    public static void main(String[] args) {
        Tester.test(new Task_121());
    }
}
