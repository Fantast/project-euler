package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.getPrimesBetween;

//Answer : 271204031455541309
//@see: http://www.math.niu.edu/~rusin/known-math/93_back/2squares
public class Task_233 implements ITask {

    final long LIM = 100000000000L;

    int cnt1 = 0;
    int cnt32 = 0;
    long pt1[] = new long[200000];
    long pt32[] = new long[200000];

    boolean isSutableMultiplier[] = new boolean[300000];
    long suitablesSum[] = new long[300000];

    public void solving() {
        pt32[cnt32++] = 2;
        for (long p : getPrimesBetween(3, 4733728)) {
            if (p % 4 == 1) {
                pt1[cnt1++] = p;
            } else {
                pt32[cnt32++] = p;
            }
        }

        fillSuitableMultipliers(1, 0);
        for (int i = 1; i < suitablesSum.length; ++i) {
            suitablesSum[i] = suitablesSum[i - 1] + (isSutableMultiplier[i] ? i : 0);
        }

        long res = sumWith123factors() + sumWith73factors() + sumWith102factors();

        System.out.println(res);
    }

    private long sumWith123factors() {
        long res = 0;
        for (int i = 0; i < cnt1; ++i) {
            long p1 = pt1[i];
            long n = p1 * p1 * p1;

            if (n > LIM) {
                break;
            }

            for (int j = 0; j < cnt1; ++j) {
                if (i == j) {
                    continue;
                }

                long p2 = pt1[j];
                long n2 = n * p2 * p2;

                if (n2 > LIM) {
                    break;
                }

                for (int k = 0; k < cnt1; ++k) {
                    if (i == k || j == k) {
                        continue;
                    }

                    long p3 = pt1[k];
                    long n3 = n2 * p3;

                    if (n3 > LIM) {
                        break;
                    }

                    res += suitablesSum[(int) (LIM / n3)] * n3;
                }
            }
        }
        return res;
    }

    private long sumWith73factors() {
        long res = 0;
        for (int i = 0; i < cnt1; ++i) {
            long p1 = pt1[i];
            long n = p1 * p1 * p1 * p1 * p1 * p1 * p1;

            if (n > LIM) {
                break;
            }

            for (int j = 0; j < cnt1; ++j) {
                if (i == j) {
                    continue;
                }

                long p2 = pt1[j];
                long n2 = n * p2 * p2 * p2;

                if (n2 > LIM) {
                    break;
                }

                res += suitablesSum[(int) (LIM / n2)] * n2;
            }
        }
        return res;
    }

    private long sumWith102factors() {
        long res = 0;
        for (int i = 0; i < cnt1; ++i) {
            long p1 = pt1[i];
            long n = p1 * p1 * p1 * p1 * p1 * p1 * p1 * p1 * p1 * p1;

            if (n > LIM) {
                break;
            }

            for (int j = 0; j < cnt1; ++j) {
                if (i == j) {
                    continue;
                }

                long p2 = pt1[j];
                long n2 = n * p2 * p2;

                if (n2 > LIM) {
                    break;
                }

                res += suitablesSum[(int) (LIM / n2)] * n2;
            }
        }
        return res;
    }

    private boolean fillSuitableMultipliers(long n, int ind) {
        if (n >= isSutableMultiplier.length) {
            return false;
        }

        isSutableMultiplier[(int) n] = true;

        for (int i = ind; i < cnt32; ++i) {
            if (!fillSuitableMultipliers(n * pt32[i], i)) {
                break;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Tester.test(new Task_233());
    }
}
