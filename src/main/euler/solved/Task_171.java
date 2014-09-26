package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.isExactSquare;

//Answer : 142989277
public class Task_171 implements ITask {
    int sq[] = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225, 256, 289, 324, 361, 400, 441, 484, 529, 576, 625, 676, 729, 784, 841, 900, 961, 1024, 1089, 1156, 1225, 1296, 1369, 1444, 1521, 1600};

    long f[] = new long[12];
    long cnt[] = new long[1700];

    long cntSqSubS[] = new long[1700];

    private static final long MOD = 1000000000;

    public void solving() {
        f[0] = 1;
        for (int i = 1; i < f.length; ++i) {
            f[i] = f[i - 1] * i;
        }

        find(0, 11, 0, f[11]);

        for (int s = 1; s <= 9*9*20; ++s) {
            cntSqSubS[s] = 0;

            int mini = isExactSquare(s) ?  (int) Math.sqrt(s) :  1 + (int) Math.sqrt(s);
            for (int i = mini; i <= 40; ++i) {
                cntSqSubS[s] = (cntSqSubS[s] + cnt[sq[i] - s]) % MOD;
            }
        }

        long res = 0;

        for (int d1 = 0; d1 < 10; ++d1) {
            System.out.println("Progress: " + d1);
            for (int d2 = 0; d2 < 10; ++d2) {
                for (int d3 = 0; d3 < 10; ++d3) {
                    for (int d4 = 0; d4 < 10; ++d4) {
                        for (int d5 = 0; d5 < 10; ++d5) {
                            for (int d6 = 0; d6 < 10; ++d6) {
                                for (int d7 = 0; d7 < 10; ++d7) {
                                    for (int d8 = 0; d8 < 10; ++d8) {
                                        for (int d9 = 0; d9 < 10; ++d9) {
                                            int s = d1*d1 + d2*d2 + d3*d3 + d4*d4 + d5*d5 + d6*d6 + d7*d7 + d8*d8 + d9*d9;
                                            long n = (((((((d1*10 + d2)*10 + d3)*10 + d4)*10 + d5)*10 + d6)*10 + d7)*10 + d8)*10 + d9;

                                            res = (res + cntSqSubS[s] * n) % MOD;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(res);
    }

    private void find(int d, int mxCnt, int s, long c) {
        if (d == 10) {
            if (mxCnt == 0) {
                cnt[s] = (cnt[s] + c) % MOD;
            }
            return;
        }

        for (int i = 0; i <= mxCnt; ++i) {
            find(d + 1, mxCnt - i, s + i * d * d, c / f[i]);
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_171());
    }
}