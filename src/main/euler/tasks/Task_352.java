package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import java.util.Locale;

import static java.lang.Math.min;

//Answer :
public class Task_352 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_352());
        Logger.close();
    }

    int N = 25;
    double p;
    double healthyprob[] = new double[N + 1];
    double fs[] = new double[N + 1];
    double ei[] = new double[N + 1];
    double eik[] = new double[N + 1];
    int om[] = new int[N + 1];
    int bdi[] = new int[N + 1];

    public void solving() {

        double res = 0;
        for (int i = 1; i <= 50; ++i) {
            p = i * 0.01;
            OtherUtils.deepFillDouble(fs, -1);
            OtherUtils.deepFillDouble(ei, -1);
            OtherUtils.deepFillDouble(eik, -1);
            OtherUtils.deepFillInt(om, -1);
            OtherUtils.deepFillInt(bdi, -1);

            healthyprob[0] = 1;
            for (int j = 1; j < healthyprob.length; ++j) {
                healthyprob[j] = healthyprob[j - 1] * (1 - p);
            }

            System.out.println(String.format(Locale.ENGLISH, "%.2f : %f", p, expectedMoveCnt(N)));
            res += expectedMoveCnt(N);
        }

        System.out.println(res);
    }

    private double expectedMoveCnt(int n) {
        if (fs[n] != -1) {
            return fs[n];
        }

        if (n == 1) {
            bdi[n] = 0;
            fs[n] = 1;
            return 1;
        }

        int bestDivideIndex = 0;
        double res = healthyprob[n] + (1 - healthyprob[n]) * (1 + expectedMoveCntWhenKnowInfected(n));
        for (int i = 1; i < n; ++i) {
            double r = expectedMoveCnt(i) + expectedMoveCnt(n - i);
            if (r < res) {
                res = r;
                bestDivideIndex = i;
            }
        }

        bdi[n] = bestDivideIndex;
        fs[n] = res;

        return res;
    }


    private int optimalMoveCntWhenHealthy(int n) {
        if (om[n] != -1) {
            return om[n];
        }

        int bi = getBestDivideIndex(n);
        return om[n] = bi == 0
                       ? 1 //measure all the group, so reveal that all are healthy
                       : optimalMoveCntWhenHealthy(bi) + optimalMoveCntWhenHealthy(n - bi);
    }

    private int getBestDivideIndex(int n) {
        expectedMoveCnt(n);
        return bdi[n];
    }

    private double expectedMoveCntWhenInfected(int n) {
        if (ei[n] != -1) {
            return ei[n];
        }

        double res = 0;
        int bi = getBestDivideIndex(n);
        if (bi == 0) {
            res = 1 + expectedMoveCntWhenKnowInfected(n);
        } else {
            res = expectedMoveCntWhenInfected(bi) + expectedMoveCntWhenInfected( n - bi);
        }

        return ei[n] = res;
    }

    private double expectedMoveCntWhenKnowInfected(int n) {
        if (n == 1) {
            return 0;
        }
        if (eik[n] != -1) {
            return eik[n];
        }

        double res = n;
        for (int j = 1; j < n; ++j) {
            double r = healthyprob[j] * (optimalMoveCntWhenHealthy(j) + expectedMoveCntWhenKnowInfected(n - j))
                       + (1 - healthyprob[j]) * (expectedMoveCntWhenInfected(j) + expectedMoveCnt(n - j));
            res = min(res, r);
        }

        return eik[n] = res;
    }
}
