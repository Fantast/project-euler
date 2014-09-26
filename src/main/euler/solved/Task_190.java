package solved;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.pow;

//Answer : 371048281
public class Task_190 implements ITask {
    public void solving() {
        long res = 0;
        for (int m = 2; m <= 15; ++m) {
            res += solve(m);
        }
        System.out.println(res);
    }

    private long solve(int m) {
        int n = m-1;
        
        double a[][] = new double[n][n];
        double b[] = new double[n];

        for (int i = 0; i < n; ++i) {
            b[i] = m;
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    a[i][j] = (i+3)/(double)(i+2);
                } else {
                    a[i][j] = 1;
                }
            }
        }

        RealMatrix A = MatrixUtils.createRealMatrix(a);
        RealMatrix B = MatrixUtils.createColumnRealMatrix(b);

        double c[] = A.solve(B).getColumn(0);

        double x1 = m;
        double res = 1;
        for (int i = 0; i < n; ++i) {
            res *= pow(c[i], i+2);
            x1 -= c[i];
        }
        res *= x1;
        
        return (long) res;
    }

    public static void main(String[] args) {
        Tester.test(new Task_190());
    }
}
