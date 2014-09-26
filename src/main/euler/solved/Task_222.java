package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Arrays;

import static java.lang.Math.*;
import static utils.MyMath.isBitSet;
import static utils.MyMath.unSetBit;

//Answer : 1590933
public class Task_222 implements ITask {
    private final int n = 21;
    private final double R = 50;
    boolean used[] = new boolean[n];
    double r[] = new double[n];

    public void solving() {
        for (int i = 0; i < n; ++i) {
            r[i] = 30 + i;
            Arrays.fill(p[i], -1);
        }

        double res = 1e12;
        for (int i = 0; i < n; ++i) {
            System.out.println("Progress: " + i);
            res = min(res, get(i, unSetBit((1<<n) - 1, i)));
        }
        System.out.println(round(res * 1000));
    }

    double p[][] = new double[n][1<<n];
    private double get(int first, int msk) {
        if (msk == 0) {
            return 2*r[first];
        }
        if (p[first][msk] != -1) {
            return p[first][msk];
        }

        double res = 1e12;
        for (int i = 0; i < n; ++i) {
            if (isBitSet(msk, i)) {
                double d = dist(r[first], r[i]);
                res = min(res, get(i, unSetBit(msk, i)) + d - r[i]);
            }
        }

        return p[first][msk] = res + r[first];
    }

    private double dist(double r1, double r2) {
        return sqrt(4 * (r1 + r2) * R - 4 * R * R);
    }

    public static void main(String[] args) {
        Tester.test(new Task_222());
    }
}
