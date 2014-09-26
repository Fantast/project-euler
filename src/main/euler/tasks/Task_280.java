package tasks;

import utils.OtherUtils;
import utils.log.Logger;

//Answer : 430.088247
public class Task_280 implements ITask {

    double s1[] = new double[5];
    double s4[] = new double[5];
    double fa[][] = new double[5][5];
    double sa[][] = new double[5][5];

    double s12[] = new double[5];
    double s42[] = new double[5];
    double fa2[][] = new double[5][5];
    double sa2[][] = new double[5][5];

    public void solving() {
        for (int i = 0; i < 5; ++i) {
            s1[i] = 0;
            s4[i] = 1;
            for (int j = 0; j < 5; ++j) {
                fa[i][j] = 0;
                sa[i][j] = 0;
            }
        }
        fa[2][2] = 1;

        double prevp = 0;
        double res = 0;
        for (int k = 1; k < 100000; ++k) {
            for (int i = 0; i < 5; ++i) {
                s12[i] = s1[i] + (1 - s1[i]) * seedmove(1, i);
                s42[i] = s4[i] * (1 - freemove(4, i));

//                s12[i] *= (1 - prevp);

                for (int j = 0; j < 5; ++j) {
                    fa2[i][j] = freemove(i, j) * ( i == 4 ? 1 - s4[j] : 1);
                    sa2[i][j] = seedmove(i, j) * ( i == 0 ? s1[i] : 1) +
                                (i == 4 ? freemove(i, j) * s4[j] : 0);

//                    fa2[i][j] *= (1 - prevp);
//                    sa2[i][j] *= (1 - prevp);
                }
            }

            OtherUtils.deepCopy(s12, s1);
            OtherUtils.deepCopy(s42, s4);
            OtherUtils.deepCopy(sa2, sa);
            OtherUtils.deepCopy(fa2, fa);

            double p = 1;
            for (double sp : s1) {
                p *= sp;
            }

            res += p * k * (1 - prevp);
            prevp = p;
        }

        System.out.println(OtherUtils.formatDouble(res, 6));
    }

    private double freemove(int i, int j) {
        return getMoveProb(i, j, fa);
    }

    private double seedmove(int i, int j) {
        return getMoveProb(i, j, sa);
    }

    private double getMoveProb(int i, int j, double[][] a) {
        double res = 0;
        if (i > 0) res += a[i-1][j] / nCnt(i - 1, j);
        if (i < 4) res += a[i+1][j] / nCnt(i + 1, j);
        if (j > 0) res += a[i][j-1] / nCnt(i, j - 1);
        if (j < 4) res += a[i][j+1] / nCnt(i, j + 1);
        return res;
    }

    private double nCnt(int i, int j) {
        if (i == 0 || i == 4) {
            return j == 0 || j == 4 ? 2 : 3;
        }

        return j == 0 || j==4 ? 3 : 4;
    }

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_280());
        Logger.close();
    }
}
