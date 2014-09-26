package solved;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import tasks.ITask;
import tasks.Tester;

//Answer : 37076114526
public class Task_101 implements ITask {
    public void solving() {
        long res = 0;
        for (int i = 1; i <= 10 ; ++i) {
            long cfs[] = getOp(i);

            for (int n = 1; ;++n) {
                long fit = polF(cfs, n);
                if (fit != f(n)) {
                    res += fit;
                    break;
                }
            }
        }

        System.out.println(res);
    }

    long[] fcf = {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
    private long f(int n) {
        return polF(fcf, n);
    }

    private long polF(long[] cfs, int n) {
        long res = 0;
        long np = 1;
        for (int i = 0; i < cfs.length; ++i) {
            res += cfs[i]*np;

            np *= n;
        }
        return res;
    }

    private long[] getOp(int k) {
        double a[][] = new double[k][k];
        double b[] = new double[k];

        for (int i = 0; i < k; ++i) {
            b[i] = f(i+1);
            double np = 1;
            for (int j = 0; j < k; ++j) {
                a[i][j] = np;
                np *= (i+1);
            }
        }

        RealMatrix A = MatrixUtils.createRealMatrix(a);
        RealMatrix B = MatrixUtils.createColumnRealMatrix(b);
        double c[] = A.solve(B).getColumn(0);

        long r[] = new long[k];
        for (int i = 0; i < k; ++i) {
            r[i] = (long) c[i];
        }
        return r;
    }

    public static void main(String[] args) {
        Tester.test(new Task_101());
    }
}
