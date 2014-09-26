package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.max;
import static utils.OtherUtils.deepFillDouble;

//Answer : 0.83648556
public class Task_232 implements ITask {
    int mDepth = 50;
    public void solving() {
        deepFillDouble(p, -1);
        double res = getProbability(0, 0, 0);
        System.out.println(res);
        System.out.println( String.format("%.8f", res) );
    }

    double p[][] = new double[102][102];
    private double getProbability(int s1, int s2, int depth) {
        if (depth == mDepth) return 0.5;
        if (s1 >= 100) return 0;
        if (s2 >= 100) return 1;

        if (p[s1][s2] != -1) {
            return p[s1][s2];
        }

        double r1 = 0;
        double r2 = 0;
        for (int t = 1; t==1 || s2 + (1<<(t-2)) < 100; ++t) {
            double den = (double)(1<<t);
            int ns2 = s2 + (1<<(t-1));

            double np2 =(getProbability(s1 + 1, ns2, 0) + (den-1)*getProbability(s1 + 1, s2, 0))/den;
            double np1 =(getProbability(s1    , ns2, 0) + (den-1)*getProbability(s1    , s2, depth+1))/den;

            r1 = max(r1, np1);
            r2 = max(r2, np2);
        }

        return p[s1][s2] = (r1 + r2)/2.0;
    }

    public static void main(String[] args) {
        Tester.test(new Task_232());
    }
}
