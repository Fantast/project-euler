package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 61190912
public class Task_194 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_194());
        Logger.close();
    }

    int a = 25;
    int b = 75;
    long c = 1984;

    long d1 = (c-2)*(c-1) + (c-2)*(c-3)*(c-2);
    long d2 = (c-3)*(c-1) + (c-3)*(c-4)*(c-2) + ((c-2)*2 - 1)*(c-2);
    long d3 = d2;
    long d4 = (c-3)*(c-1) + (c-3)*(c-4)*(c-2) + (c-1 + 2*(c-2) - 2)*(c-2);
    long d5 = (c-4)*(c-1) + (c-4)*(c-5)*(c-2) + (c-2)*(c-3)*4;

    long na = d1 + (c-2)*d2 + (c-2)*d3 + (c-2)*(c-3)*d5;
    long nb = d1 + (c-2)*d2 + (c-2)*d3 + (c-2)*d4 + (c-2)*(c-3)*d5;

    long MOD = 100000000;

    public void solving() {
        na %= MOD;
        nb %= MOD;

        long r[][] = new long[a+1][b+1];

        r[0][0] = c*(c-1);
        for (int i = 0; i <= a; ++i) {
            for (int j = 0; j <= b; ++j) {
                if (i > 0) {
                    r[i][j] += r[i-1][j] * na;
                }

                if (j > 0) {
                    r[i][j] += r[i][j-1] * nb;
                }

                r[i][j] %= MOD;
            }
        }

        System.out.println(r[a][b]);
    }
}
