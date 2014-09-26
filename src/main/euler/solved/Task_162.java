package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

//Answer : 3D58725572C62302
public class Task_162 implements ITask {

    int n = 16;
    BigInteger c[][][] = new BigInteger[n][16][1 << 3];

    public void solving() {
        for (int i = 0; i < n; ++i) {
            for (int d = 0; d < 16; ++d) {
                for (int m = 0; m < (1 << 3); ++m) {
                    c[i][d][m] = ZERO;
                }
            }
        }


        for (int d = 3; d < 16; ++d) {
            c[0][d][0] = ONE;
        }
        c[0][1][1 << 1] = ONE;
        c[0][2][1 << 2] = ONE;

        for (int i = 1; i < n; ++i) {
            for (int d = 0; d < 16; ++d) {
                for (int m = 0; m < (1 << 3); ++m) {
                    int m2 = (d > 2) ? m : (m | (1 << d));

                    for (int dn = 0; dn < 16; ++dn) {
                        c[i][d][m2] = c[i][d][m2].add(c[i - 1][dn][m]);
                    }
                }
            }
        }

        BigInteger res = ZERO;
        for (int i = 0; i < n; ++i) {
            for (int d = 0; d < 16; ++d) {
                res = res.add(c[i][d][(1 << 3) - 1]);
            }
        }

        System.out.println(res.toString(16).toUpperCase());
    }

    public static void main(String[] args) {
        Tester.test(new Task_162());
    }
}
