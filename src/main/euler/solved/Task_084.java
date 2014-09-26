package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 101524
public class Task_084 implements ITask {
    int JAIL = 10;
    int G2J = 30;
    int GO = 0;

    int C1 = 11;
    int E3 = 24;
    int H2 = 39;
    int R1 = 5;

    int CC[] = {2, 17, 33};
    int CH[] = {7, 22, 36};

    int Rs[] = {5, 15, 25, 35};
    int Us[] = {12, 28};

    int n = 4;
    int n2 = 2*n;
    double pm[] = new double[n2+1];
    public void solving() {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                pm[i+j] += 1;
            }
        }

        double p[] = new double[40];
        double p2[] = new double[40];
        p[0] = 1;
        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 40; ++j) {
                for (int k = 1; k <= n2; ++k) {
                    double dp = pm[k] * p[j];

                    int j2 = (j + k)%40;

                    if (j2 == G2J) {
                        j2 = JAIL;
                    }

                    if (isCC(j2)) {
                        p2[GO] += 1.0 * dp;
                        p2[JAIL] += 1.0 * dp;
                        p2[j2] += 14.0 * dp;
                    } else if (isCH(j2)) {
                        p2[GO] += 1.0 * dp;
                        p2[JAIL] += 1.0 * dp;
                        p2[C1] += 1.0 * dp;
                        p2[E3] += 1.0 * dp;
                        p2[H2] += 1.0 * dp;
                        p2[R1] += 1.0 * dp;

                        p2[getNextR(j2)] += 2.0 * dp;
                        p2[getNextU(j2)] += 1.0 * dp;

                        int j3 = (j2 + 40 - 3)%40;
                        if (isCC(j3)) {
                            p2[GO] += 1.0/16.0 * dp;
                            p2[JAIL] += 1.0/16.0 * dp;
                            p2[j3] += 14.0/16.0 * dp;
                        } else {
                            p2[ j3 ] += 1.0 * dp;
                        }

                        p2[j2] += 6.0 * dp;
                    } else {
                        p2[j2] += 16.0 * dp;
                    }

                }
            }
            
            for (int j = 0; j < 40; ++j) {
                p[j] = p2[j]/16.0/(double)(n*n);
                p2[j] = 0;
            }
        }

        System.out.println("");
        for (int k = 0; k < 3; ++k) {
            int imx = 0;
            for (int i = 0; i < 40; ++i) {
                if (p[i] > p[imx]) {
                    imx = i;
                }
            }

            System.out.print(imx);
            p[imx] = 0;
        }
        
    }

    public static void main(String[] args) {
        Tester.test(new Task_084());
    }

    private boolean isCC(int j) {
        for (int nj : CC) {
            if (nj == j) {
                return true;
            }
        }
        return false;
    }

    private boolean isCH(int j) {
        for (int nj : CH) {
            if (nj == j) {
                return true;
            }
        }
        return false;
    }

    private int getNextR(int j) {
        for (int i = 0; i < Rs.length; ++i) {
            if (Rs[i] > j) {
                return Rs[i];
            }
        }
        return Rs[0];
    }

    private int getNextU(int j) {
        for (int i = 0; i < Us.length; ++i) {
            if (Us[i] > j) {
                return Us[i];
            }
        }
        return Us[0];
    }
}
